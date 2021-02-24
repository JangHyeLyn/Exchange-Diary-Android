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
import com.km.exchangediary.databinding.ActivityProfileEditBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ProfileEditActivity : BaseActivity<ActivityProfileEditBinding>() {
    override fun layoutRes(): Int = R.layout.activity_profile_edit
    private val CONTENTS_PERMISSION_CODE = 1
    private lateinit var INTENT_NAME: String
    private var INTENT_INFO: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* TODO 오류시 띄울 이미지(기본이미지) 수정 */
        Glide.with(this).load("https://cdn.idreambank.com/news/photo/202004/81215_78965_4506.jpg")
            .circleCrop().error(R.drawable.sample).into(binding.ivProfileEditPhoto)

        INTENT_NAME = intent.getStringExtra("name")
        INTENT_INFO = intent.getStringExtra("info")

        binding.editProfileName.setText(INTENT_NAME)
        binding.editProfileInfo.setText(INTENT_INFO)

        clickListeners()
        infoLengthCount()
        activateEndButton()
    }

    private fun clickListeners() {
        binding.ivProfileEditPhoto.setOnClickListener {
            /* TODO : 기본이미지선택 or 앨범에서 선택 다이얼로그 */
            checkPermission()
        }

        binding.tvProfileEditEnd.setOnClickListener {
            /* TODO 완료 버튼 : 변경사항 서버로 전달 */
        }

        binding.ivProfileEditBack.setOnClickListener {
            /* TODO 변경사항 저장여부 알림 */
            finish()
        }
    }

    /* TODO : 사진 변경되었을 경우 버튼 활성화 */
    //변경버튼 활성화여부 판별
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
                } else if (s.toString() == INTENT_NAME && binding.editProfileInfo.text.toString() == INTENT_INFO) {
                    changeButtonColor(false)
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        binding.editProfileInfo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != INTENT_INFO) {
                    changeButtonColor(true)
                } else if (s.toString() == INTENT_INFO && binding.editProfileName.text.toString() == INTENT_NAME) {
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
                            R.color.btn_disable_color
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
                            R.color.btn_enable_color
                        )
                    )
                }
            }
        }

    }

    //tv_profile_info 글자수
    private fun infoLengthCount() {
        binding.tvProfileInfoLength.text = "${binding.editProfileInfo.length()}/70"

        binding.editProfileInfo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tvProfileInfoLength.text = "${binding.editProfileInfo.text.length}/70"
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun checkPermission() {
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
                    /* TODO 기본 이미지 변경 */
                    Glide.with(this).load(result.data?.data)
                        .circleCrop().error(R.drawable.sample).into(binding.ivProfileEditPhoto)
                }

                RESULT_CANCELED -> {
                    Glide.with(this)
                        .load("https://cdn.idreambank.com/news/photo/202004/81215_78965_4506.jpg")
                        .circleCrop().error(R.drawable.sample).into(binding.ivProfileEditPhoto)
                }
            }
        }
}