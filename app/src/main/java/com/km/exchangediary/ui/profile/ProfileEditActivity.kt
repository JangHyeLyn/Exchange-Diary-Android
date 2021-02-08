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
import java.util.jar.Manifest

class ProfileEditActivity : BaseActivity<ActivityProfileEditBinding>() {
    override fun layoutRes(): Int = R.layout.activity_profile_edit
    var photoUri: Uri? = null
    val CONTENTS_PERMISSION_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* TODO 오류시 띄울 이미지(기본이미지) 수정 */
        Glide.with(this).load("https://cdn.idreambank.com/news/photo/202004/81215_78965_4506.jpg")
            .circleCrop().error(R.drawable.sample).into(binding.ivProfileEditPhoto)

        binding.editProfileName.setText(intent.getStringExtra("name"))
        binding.editProfileInfo.setText(intent.getStringExtra("info"))

        infoLengthCount()
        enableEndBtn()

        binding.ivProfileEditPhoto.setOnClickListener {
            checkPermission()
        }

        binding.tvProfileEditEnd.setOnClickListener {
            /* TODO 완료 버튼 : 변경사항 서버로 전달 */
        }

        binding.ivProfileEditBack.setOnClickListener {
            /* TODO 변경사항 저장 알림? */
            finish()
        }
    }

    /* TODO : 사진 변경되었을 경우 버튼 활성화 */
    //변경버튼 활성화여부 판별
    fun enableEndBtn() {
        val intentName = intent.getStringExtra("name")
        val intentInfo = intent.getStringExtra("info")

        binding.editProfileName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != intent.getStringExtra("name")) {
                    if (s.toString().length < 2) {
                        changeBtn(false)
                    } else {
                        changeBtn(true)
                    }
                } else if (s.toString() == intentName && binding.editProfileInfo.text.toString() == intentInfo) {
                    changeBtn(false)
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        binding.editProfileInfo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != intentInfo) {
                    changeBtn(true)
                } else if (s.toString() == intentInfo && binding.editProfileName.text.toString() == intentName) {
                    changeBtn(false)
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    // true:버튼 활성화 / false:버튼 비활성화
    fun changeBtn(result: Boolean) {
        when (result) {
            false -> {
                binding.tvProfileEditEnd.apply {
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
    fun infoLengthCount() {
        binding.tvProfileInfoLength.text = "${binding.editProfileInfo.length()} / 70"

        binding.editProfileInfo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tvProfileInfoLength.text = "${binding.editProfileInfo.text.length} / 70"
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    fun checkPermission() {
        val WRITE_PERMISSION = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        val READ_PERMISSION = android.Manifest.permission.READ_EXTERNAL_STORAGE

        var writePermission = ContextCompat.checkSelfPermission(this, WRITE_PERMISSION)
        var readPermission = ContextCompat.checkSelfPermission(this, READ_PERMISSION)

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

    private val startForSelectPhotoResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                photoUri = result?.data?.data
                cropImage()
            }
        }

    fun selectPhoto() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = MediaStore.Images.Media.CONTENT_TYPE
        startForSelectPhotoResult.launch(photoPickerIntent)
    }

    private val startForCropImageResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            when (result.resultCode) {
                RESULT_OK -> {
                    Glide.with(this).load(photoUri)
                        .circleCrop().error(R.drawable.sample).into(binding.ivProfileEditPhoto)
                }

                RESULT_CANCELED -> {
                    Glide.with(this)
                        .load("https://cdn.idreambank.com/news/photo/202004/81215_78965_4506.jpg")
                        .circleCrop().error(R.drawable.sample).into(binding.ivProfileEditPhoto)
                }
            }
        }

    private fun cropImage() {

        val intent = Intent("com.android.camera.action.CROP")

        intent.setDataAndType(photoUri, "image/*")

        val list: MutableList<ResolveInfo> = packageManager.queryIntentActivities(intent, 0)
        grantUriPermission(
            list.get(0).activityInfo.packageName,
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

            val imageFileName = "exchangeDiary_${SimpleDateFormat("HHmmss").format(Date())}_"
            val storageDir: Array<File> =
                ContextCompat.getExternalFilesDirs(applicationContext, null)

            val croppedFileName: File = File.createTempFile(imageFileName, ".jpg", storageDir[0])

            val folder: Array<File> =
                ContextCompat.getExternalFilesDirs(getApplicationContext(), null)
            val tempFile: File = File(folder[0].toString(), croppedFileName.name)
            photoUri = FileProvider.getUriForFile(
                this,
                "com.km.exchangediary.ui.profile.provider",
                tempFile
            )
            intent.putExtra("return-data", false)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())

            val i = Intent(intent)
            val res: ResolveInfo = list.get(0)
            grantUriPermission(
                res.activityInfo.packageName, photoUri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            i.component = ComponentName(res.activityInfo.packageName, res.activityInfo.name)

            startForCropImageResult.launch(i)
        }
    }
}