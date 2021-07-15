package com.km.exchangediary.ui.profile

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
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
import com.km.exchangediary.databinding.ActivityProfileEditBinding
import com.km.exchangediary.ui.CommonDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ProfileEditActivity : BaseActivity<ActivityProfileEditBinding>() {
    override fun layoutRes(): Int = R.layout.activity_profile_edit
    private val viewModel: ProfileViewModel by viewModel()

    private val CONTENTS_PERMISSION_CODE = 1
    private lateinit var PREVIOUS_NAME: String
    private var PREVIOUS_INTRODUCTION: String? = null
    private var PREVIOUS_PROFILE_IMAGE_URL: String? = null
    private var changeProfileImageUri: Uri? = null
    private var defaultImageNumber: Int? = null
    private var imageFlag: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val profileDataClass = viewModel.getProfileFromPrefs()

        PREVIOUS_NAME = profileDataClass?.name.toString()
        PREVIOUS_INTRODUCTION = profileDataClass?.introduction
        PREVIOUS_PROFILE_IMAGE_URL = profileDataClass?.profileImage

        Glide.with(this).load(Base64.decode(profileDataClass?.profileImage, Base64.DEFAULT))
            .circleCrop().error(R.drawable.ic_profile_default_image_1)
            .into(binding.ivProfileEditPhoto)
        binding.editProfileName.setText(PREVIOUS_NAME)
        binding.editProfileIntroduction.setText(PREVIOUS_INTRODUCTION)

        clickListeners()
        introductionLengthCount()
        activateEndButton()
    }

    override fun onBackPressed() {
        showSaveDialog(binding.tvProfileEditEnd.isEnabled)
    }

    private fun clickListeners() {
        binding.ivProfileEditPhoto.setOnClickListener {
            SelectProfileImageFragment().show(supportFragmentManager, "SampleDialog")
        }

        binding.tvProfileEditEnd.setOnClickListener {
            viewModel.patchProfileToServerAndPrefs(
                binding.editProfileName.text.toString(),
                binding.editProfileIntroduction.text.toString(),
                changeProfileImageUri,
                imageFlag,
                defaultImageNumber?: 0,
                baseContext
            )
            setResult(RESULT_OK)
            finish()
        }

        binding.ibProfileEditBack.setOnClickListener {
            showSaveDialog(binding.tvProfileEditEnd.isEnabled)
        }
    }

    private fun showSaveDialog(isEnable: Boolean) {
        if (isEnable) {
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
                if (s.toString() != PREVIOUS_NAME) {
                    if (s.toString().length < 2) {
                        changeButtonColor(false)
                    } else {
                        changeButtonColor(true)
                    }
                } else if (s.toString() == PREVIOUS_NAME
                    && binding.editProfileIntroduction.text.toString() == PREVIOUS_INTRODUCTION && changeProfileImageUri == null
                ) {
                    changeButtonColor(false)
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        binding.editProfileIntroduction.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != PREVIOUS_INTRODUCTION) {
                    changeButtonColor(true)
                } else if (s.toString() == PREVIOUS_INTRODUCTION
                    && binding.editProfileName.text.toString() == PREVIOUS_NAME && changeProfileImageUri == null
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
        val writePermission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        val readPermission = android.Manifest.permission.READ_EXTERNAL_STORAGE

        if (ContextCompat.checkSelfPermission(this, writePermission) == PackageManager.PERMISSION_DENIED
            || ContextCompat.checkSelfPermission(this, readPermission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(writePermission, readPermission),
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

            val cropImageIntent = Intent(intent)
            cropImageIntent.component = ComponentName(
                getCropImageActivity.activityInfo.packageName,
                getCropImageActivity.activityInfo.name
            )

            grantUriPermission(
                getCropImageActivity.activityInfo.packageName, photoUri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
            )

            startForCropImageResult.launch(cropImageIntent)
        }
    }

    private val startForCropImageResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            when (result.resultCode) {
                RESULT_OK -> {
                    changeProfileImageUri = result.data?.data
                    Glide.with(this).load(result.data?.data)
                        .circleCrop().error(R.drawable.ic_profile_default_image_1)
                        .into(binding.ivProfileEditPhoto)
                    imageFlag = 2
                    changeButtonColor(true)
                }

                RESULT_CANCELED -> {

                }
            }
        }

    fun setDefaultImage() {
        val defaultImagesArray = arrayOf(
            R.drawable.ic_profile_default_image_1,
            R.drawable.ic_profile_default_image_2,
            R.drawable.ic_profile_default_image_3,
            R.drawable.ic_profile_default_image_4,
            R.drawable.ic_profile_default_image_5,
            R.drawable.ic_profile_default_image_6,
            R.drawable.ic_profile_default_image_7,
            R.drawable.ic_profile_default_image_8,
            R.drawable.ic_profile_default_image_9
        )

        var randomNumber = Random().nextInt(9)

        //같은 수 연속 방지
        while (defaultImageNumber == defaultImagesArray[randomNumber]) {
            randomNumber = Random().nextInt(9)
        }

        Glide.with(this).load(defaultImagesArray[randomNumber])
            .circleCrop().error(R.drawable.ic_profile_default_image_8)
            .into(binding.ivProfileEditPhoto)

        defaultImageNumber = defaultImagesArray[randomNumber]
        imageFlag = 1
        changeButtonColor(true)
    }
}