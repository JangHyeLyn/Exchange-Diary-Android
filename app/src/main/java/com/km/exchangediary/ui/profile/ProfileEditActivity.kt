package com.km.exchangediary.ui.profile

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.data.entity.ProfileResult
import com.km.exchangediary.data.remote.service.BASE_URL
import com.km.exchangediary.data.remote.service.ProfileService
import com.km.exchangediary.databinding.ActivityProfileEditBinding
import com.km.exchangediary.ui.CommonDialog
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class ProfileEditActivity : BaseActivity<ActivityProfileEditBinding>() {
    override fun layoutRes(): Int = R.layout.activity_profile_edit
    private val CONTENTS_PERMISSION_CODE = 1
    private lateinit var INTENT_NAME: String
    private var INTENT_INTRODUCTION: String? = null
    private var INTENT_PROFILE_IMAGE_URL: String? = null
    private var CHANGE_PROFILE_IMAGE_URL: Uri? = null
    private var DEFAULT_IMAGE_NUMBER: Int? = null
    private var IMAGE_FLAG: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        INTENT_NAME = intent.getStringExtra("name")
        INTENT_INTRODUCTION = intent.getStringExtra("introduction")
        INTENT_PROFILE_IMAGE_URL = intent.getStringExtra("profileIamgeUrl")

        Glide.with(this).load(INTENT_PROFILE_IMAGE_URL)
            .circleCrop().error(R.drawable.ic_profile_dafault_image_1)
            .into(binding.ivProfileEditPhoto)
        binding.editProfileName.setText(INTENT_NAME)
        binding.editProfileIntroduction.setText(INTENT_INTRODUCTION)

        clickListeners()
        introductionLengthCount()
        activateEndButton()
    }

    override fun onBackPressed() {
        showSaveDialog()
    }

    private fun clickListeners() {
        binding.ivProfileEditPhoto.setOnClickListener {
            SelectProfileImageFragment().show(supportFragmentManager, "SampleDialog")
        }

        binding.tvProfileEditEnd.setOnClickListener {
            patchProfileToServer(
                binding.editProfileName.text.toString(),
                binding.editProfileIntroduction.text.toString(),
                CHANGE_PROFILE_IMAGE_URL,
                IMAGE_FLAG
            )
        }

        binding.ivProfileEditBack.setOnClickListener {
            showSaveDialog()
        }
    }

    private fun showSaveDialog() {
        if (binding.tvProfileEditEnd.isEnabled) {
            CommonDialog(
                titleVisible = false,
                contentText = "해당 페이지를 나가면 변경사항은 저장되지\n않습니다. 정말 나가시겠습니까?",
                cancelText = "계속 편집",
                confirmText = "나가기",
                onSuccess = { finish() }
            ).show(supportFragmentManager, "ProfileDialog")
        } else {
            finish()
        }
    }

    //변경버튼 활성화여부 판별 (default : enable = false)
    private fun activateEndButton() {
        binding.editProfileName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != INTENT_NAME) {
                    if (s.toString().length < 2) {
                        changeButtonColor(false)
                    } else {
                        changeButtonColor(true)
                    }
                } else if (s.toString() == INTENT_NAME
                    && binding.editProfileIntroduction.text.toString() == INTENT_INTRODUCTION && CHANGE_PROFILE_IMAGE_URL == null
                ) {
                    changeButtonColor(false)
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        binding.editProfileIntroduction.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != INTENT_INTRODUCTION) {
                    changeButtonColor(true)
                } else if (s.toString() == INTENT_INTRODUCTION
                    && binding.editProfileName.text.toString() == INTENT_NAME && CHANGE_PROFILE_IMAGE_URL == null
                ) {
                    changeButtonColor(false)
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    // true:버튼 활성화 / false:버튼 비활성화
    private fun changeButtonColor(isActivate: Boolean) {
        when (isActivate) {
            false -> {
                binding.tvProfileEditEnd.apply {
                    isEnabled = false
                    setTextColor(
                        ContextCompat.getColorStateList(
                            applicationContext,
                            R.color.gray_04
                        )
                    )
                }
            }

            true -> {
                binding.tvProfileEditEnd.apply {
                    isEnabled = true
                    setTextColor(
                        ContextCompat.getColorStateList(
                            applicationContext,
                            R.color.sub_blue
                        )
                    )
                }
            }
        }
    }

    //tv_profile_introduction 글자수
    private fun introductionLengthCount() {
        binding.tvProfileIntroductionLength.text = "${binding.editProfileIntroduction.length()}/70"

        binding.editProfileIntroduction.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tvProfileIntroductionLength.text =
                    "${binding.editProfileIntroduction.text.length}/70"
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    fun checkPermission() {
        val WRITE_PERMISSION = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        val READ_PERMISSION = android.Manifest.permission.READ_EXTERNAL_STORAGE

        val writePermission = ContextCompat.checkSelfPermission(this, WRITE_PERMISSION)
        val readPermission = ContextCompat.checkSelfPermission(this, READ_PERMISSION)

        if (writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(WRITE_PERMISSION, READ_PERMISSION),
                CONTENTS_PERMISSION_CODE
            )
        } else {
            selectPhoto()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CONTENTS_PERMISSION_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    selectPhoto()
                } else {
                    Toast.makeText(applicationContext, "접근 권한이 필요합니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun selectPhoto() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK) /* TODO ACTION_GET_CONTENT 로 변경 */
        photoPickerIntent.type = MediaStore.Images.Media.CONTENT_TYPE

        startForSelectPhotoResult.launch(photoPickerIntent)
    }

    private val startForSelectPhotoResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                cropImage(result.data?.data)
            }
        }

    private fun cropImage(photoDirectory: Uri?) {
        var photoUri: Uri? = photoDirectory

        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(photoUri, "image/*")

        val list: MutableList<ResolveInfo> = packageManager.queryIntentActivities(intent, 0)
        val getCropImageActivity: ResolveInfo = list.get(0)

        grantUriPermission(
            getCropImageActivity.activityInfo.packageName,
            photoUri,
            Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
        )

        if (list.size == 0) {
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
            return
        } else {
            intent.putExtra("crop", "true")
            intent.putExtra("aspectX", 1)
            intent.putExtra("aspectY", 1)
            intent.putExtra("scale", true)

            val storageDirectory: Array<File> =
                ContextCompat.getExternalFilesDirs(applicationContext, null)

            val croppedFileName: File = File.createTempFile(
                "exchangeDiary_${SimpleDateFormat("HHmmss").format(Date())}_",
                ".jpg",
                storageDirectory[0]
            )

            val folder: Array<File> =
                ContextCompat.getExternalFilesDirs(applicationContext, null)
            val tempFile: File = File(folder[0].toString(), croppedFileName.name)
            photoUri = FileProvider.getUriForFile(
                this,
                "com.km.exchangediary.ui.profile.provider",
                tempFile
            )
            intent.putExtra("return-data", false)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())

            val cropIamgeIntent = Intent(intent)
            cropIamgeIntent.component = ComponentName(
                getCropImageActivity.activityInfo.packageName,
                getCropImageActivity.activityInfo.name
            )

            grantUriPermission(
                getCropImageActivity.activityInfo.packageName, photoUri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
            )

            startForCropImageResult.launch(cropIamgeIntent)
        }
    }

    private val startForCropImageResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            when (result.resultCode) {
                RESULT_OK -> {
                    CHANGE_PROFILE_IMAGE_URL = result.data?.data
                    Glide.with(this).load(result.data?.data)
                        .circleCrop().error(R.drawable.ic_profile_dafault_image_1)
                        .into(binding.ivProfileEditPhoto)
                    IMAGE_FLAG = 2
                    changeButtonColor(true)
                }

                RESULT_CANCELED -> {

                }
            }
        }

    fun setDefaultImage() {
        val defaultImagesArray = arrayOf(
            R.drawable.ic_profile_dafault_image_1,
            R.drawable.ic_profile_dafault_image_2,
            R.drawable.ic_profile_dafault_image_3,
            R.drawable.ic_profile_dafault_image_4,
            R.drawable.ic_profile_dafault_image_5,
            R.drawable.ic_profile_dafault_image_6,
            R.drawable.ic_profile_dafault_image_7,
            R.drawable.ic_profile_dafault_image_8,
            R.drawable.ic_profile_dafault_image_9
        )

        var randomNumber = Random().nextInt(9)

        //같은 수 연속 방지
        while (DEFAULT_IMAGE_NUMBER == defaultImagesArray[randomNumber]) {
            randomNumber = Random().nextInt(9)
        }

        Glide.with(this).load(defaultImagesArray[randomNumber])
            .circleCrop().error(R.drawable.ic_profile_dafault_image_8)
            .into(binding.ivProfileEditPhoto)

        DEFAULT_IMAGE_NUMBER = defaultImagesArray[randomNumber]
        IMAGE_FLAG = 1
        changeButtonColor(true)
    }

    fun patchProfileToServer(
        name: String,
        introduction: String,
        profileImage: Uri?,
        imageFlag: Int
    ) {
        val retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build() /* TODO 위치수정 */
        val service = retrofit.create(ProfileService::class.java)

        var callPatchResult: Call<ProfileResult>? = null

        when (imageFlag) {
            // 0: 이미지변경 X
            0 -> {
                callPatchResult =
                    service.patchProfileStringItems(
                        name.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                        introduction.toRequestBody("multipart/form-data".toMediaTypeOrNull())
                    )
            }

            // 1: 디폴트이미지로 변경
            1 -> {
                val profileImageFile = getFileFromVectorDrawable(this, DEFAULT_IMAGE_NUMBER)
                val profileImageRequestBody =
                    profileImageFile.asRequestBody("image/*".toMediaTypeOrNull())

                callPatchResult =
                    service.patchProfileWithImage(
                        name.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                        introduction.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                        MultipartBody.Part.createFormData(
                            "profile_img",
                            profileImageFile.name,
                            profileImageRequestBody
                        )
                    )

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
                    service.patchProfileWithImage(
                        name.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                        introduction.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                        MultipartBody.Part.createFormData(
                            "profile_img",
                            profileImageFile.name,
                            profileImageRequestBody
                        )
                    )
            }
        }

        callPatchResult?.enqueue(object : Callback<ProfileResult> {
            override fun onResponse(call: Call<ProfileResult>, response: Response<ProfileResult>) {
                if (response.isSuccessful) {
                    Log.d("patchResult", response.body().toString())
                }
            }

            override fun onFailure(call: Call<ProfileResult>, t: Throwable) {
                Log.d("patchResult", t.localizedMessage.toString())
            }
        })
    }

    fun getFileFromVectorDrawable(context: Context, drawableId: Int?): File {
        val drawable = ContextCompat.getDrawable(context, drawableId!!)
        val bitmap = Bitmap.createBitmap(
            drawable?.intrinsicWidth ?: 0,
            drawable?.intrinsicHeight ?: 0, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable?.setBounds(0, 0, canvas.width, canvas.height)
        drawable?.draw(canvas)

        val profileImageFile = File(
            baseContext.getExternalFilesDirs(null)[0].absolutePath.toString(),
            "profile_default_image.PNG"
        )
        val outStream = FileOutputStream(profileImageFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream)
        outStream.flush()
        outStream.close()

        return profileImageFile
    }
}