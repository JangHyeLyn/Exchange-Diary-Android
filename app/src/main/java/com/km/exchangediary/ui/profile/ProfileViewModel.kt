package com.km.exchangediary.ui.profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.util.Base64
import android.util.Base64OutputStream
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.km.exchangediary.data.remote.datasource.ExchangeDiaryDataSource
import com.km.exchangediary.data.remote.request.ProfileRequestBody
import com.km.exchangediary.data.remote.response.ProfileResponseBody
import com.km.exchangediary.data.repository.ProfileRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class ProfileViewModel(private val repository: ProfileRepository, private val dataSource: ExchangeDiaryDataSource) : ViewModel() {
    private val service = dataSource.getExchangeDiaryService()

    /* ------------------------ ProfileActivity ------------------------ */
    fun getProfileFromPrefs(): ProfileDataClass {
        return repository.getProfileData()
    }

    /* ------------------------ ProfileEditActivity ------------------------ */
    fun patchProfileToServerAndPrefs(
        name: String,
        introduction: String,
        profileImage: Uri?,
        imageFlag: Int,
        defaultImageNumber: Int,
        context: Context
    ) {
        var callPatchResult: Call<ProfileResponseBody>? = null

        val currentProfileData = getProfileFromPrefs()

        when (imageFlag) {
            // 0: 이미지변경 X
            0 -> {
                callPatchResult =
                    service.patchProfile(ProfileRequestBody(name, introduction))

                currentProfileData.name = name
                currentProfileData.introduction = introduction

                repository.setProfileData(currentProfileData)
            }

            // 1: 디폴트이미지로 변경
            1 -> {
                val profileImageFile = getFileFromVectorDrawable(context, defaultImageNumber)
                val profileImageRequestBody =
                    profileImageFile.asRequestBody("image/*".toMediaTypeOrNull())

                callPatchResult =
                    service.patchProfile(
                        name.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                        introduction.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                        MultipartBody.Part.createFormData(
                            "profile_img",
                            profileImageFile.name,
                            profileImageRequestBody
                        )
                    )
                currentProfileData.name = name
                currentProfileData.introduction = introduction
                currentProfileData.profileImage = convertImageFileToBase64(profileImageFile)

                repository.setProfileData(currentProfileData)
            }

            // 2: 사용자 임의 이미지로 변경
            2 -> {
                val profileImageFile = File(profileImage?.path.toString())
                if (!profileImageFile.exists()) {
                    profileImageFile.mkdirs()
                }

                val profileImageRequestBody =
                    profileImageFile.asRequestBody("image/*".toMediaTypeOrNull())

                callPatchResult =
                    service.patchProfile(
                        name.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                        introduction.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                        MultipartBody.Part.createFormData(
                            "profile_img",
                            profileImageFile.name,
                            profileImageRequestBody
                        )
                    )

                currentProfileData.name = name
                currentProfileData.introduction = introduction
                currentProfileData.profileImage = convertImageFileToBase64(profileImageFile)

                repository.setProfileData(currentProfileData)
            }
        }

        callPatchResult?.enqueue(object : Callback<ProfileResponseBody> {
            override fun onResponse(
                call: Call<ProfileResponseBody>,
                response: Response<ProfileResponseBody>
            ) {
                if (response.isSuccessful) {
                    Log.d("patchResult", response.body().toString())
                }
            }

            override fun onFailure(call: Call<ProfileResponseBody>, t: Throwable) {
                Log.d("patchResult", t.localizedMessage.toString())
            }
        })
    }

    private fun getFileFromVectorDrawable(context: Context, drawableId: Int?): File {
        val drawable = ContextCompat.getDrawable(context, drawableId!!)
        val bitmap = Bitmap.createBitmap(
            drawable?.intrinsicWidth ?: 0,
            drawable?.intrinsicHeight ?: 0, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable?.setBounds(0, 0, canvas.width, canvas.height)
        drawable?.draw(canvas)

        val profileImageFile = File(
            context.getExternalFilesDirs(null)[0].absolutePath.toString(),
            "profile_default_image.PNG"
        )
        val outStream = FileOutputStream(profileImageFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream)
        outStream.flush()
        outStream.close()

        return profileImageFile
    }

    private fun convertImageFileToBase64(imageFile: File): String {
        return ByteArrayOutputStream().use { outputStream ->
            Base64OutputStream(outputStream, Base64.DEFAULT).use { base64FilterStream ->
                imageFile.inputStream().use { inputStream ->
                    inputStream.copyTo(base64FilterStream)
                }
            }
            return@use outputStream.toString()
        }
    }
}