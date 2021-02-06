package com.km.exchangediary.ui.profile

import android.content.ComponentName
import android.content.Intent
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
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityProfileEditBinding
import kotlinx.android.synthetic.main.activity_profile_edit.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ProfileEditActivity : BaseActivity<ActivityProfileEditBinding>() {
    override fun layoutRes(): Int = R.layout.activity_profile_edit
    var photoUri : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Glide.with(this).load("https://cdn.idreambank.com/news/photo/202004/81215_78965_4506.jpg")
            .circleCrop().error(R.drawable.sample).into(binding.ivProfileEditPhoto)

        binding.editProfileName.setText(intent.getStringExtra("name"))
        binding.editProfileInfo.setText(intent.getStringExtra("info"))

        infoLengthCount()
        enableEndBtn()

        binding.ivProfilePhotoAdd.setOnClickListener {
            selectPhoto()
        }

        binding.btnProfileEditEnd.setOnClickListener {
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
                        disableBtn()
                    } else {
                        enableBtn()
                    }
                } else if (s.toString() == intentName && binding.editProfileInfo.text.toString() == intentInfo) {
                    disableBtn()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        binding.editProfileInfo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != intentInfo) {
                    enableBtn()
                } else if (s.toString() == intentInfo && binding.editProfileName.text.toString() == intentName) {
                    disableBtn()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    //버튼 비활성화
    fun disableBtn(){
        binding.btnProfileEditEnd.apply {
            isEnabled = false
            setTextColor(
                ContextCompat.getColorStateList(applicationContext, R.color.btn_disable_color)
            )
        }
    }

    //버튼 활성화
    fun enableBtn(){
        binding.btnProfileEditEnd.apply {
            isEnabled = true
            setTextColor(
                ContextCompat.getColorStateList(
                    applicationContext,
                    R.color.btn_enable_color
                )
            )
        }
    }

    //tv_profile_info 글자수
    fun infoLengthCount() {
        binding.tvProfileInfoLength.text = edit_profile_info.length().toString() + "/70"

        binding.editProfileInfo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = binding.editProfileInfo.text.toString()
                binding.tvProfileInfoLength.text = input.length.toString() + "/70"
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private val startForSelectPhotoResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
            photoUri = result?.data?.data
            cropImage()
        }

    fun selectPhoto() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = MediaStore.Images.Media.CONTENT_TYPE
        startForSelectPhotoResult.launch(photoPickerIntent)
    }

    private val startForCropImageResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
            Glide.with(this).load(photoUri)
                .circleCrop().error(R.drawable.sample).into(binding.ivProfileEditPhoto)
        }

    private fun cropImage() {

        val intent: Intent = Intent("com.android.camera.action.CROP")

        intent.setDataAndType(photoUri, "image/*")

        val list: MutableList<ResolveInfo>  = packageManager.queryIntentActivities(intent, 0)
        grantUriPermission(
            list.get(0).activityInfo.packageName,
            photoUri,
            Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
        val size = list.size

        if (size == 0) {
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show()
            return
        } else {
            intent.putExtra("crop", "true")
            intent.putExtra("aspectX", 1)
            intent.putExtra("aspectY", 1)
            intent.putExtra("scale", true)

            val croppedFileName: File? = createImageFile()

            val folder: Array<File> =
                ContextCompat.getExternalFilesDirs(getApplicationContext(), null)
            val tempFile: File = File(folder[0].toString(), croppedFileName!!.name)
            photoUri = FileProvider.getUriForFile(
                this,
                "com.km.exchangediary.ui.profile.provider",
                tempFile
            )
            intent.putExtra("return-data", false)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())

            val i: Intent = Intent(intent)
            val res: ResolveInfo = list.get(0)
            grantUriPermission(
                res.activityInfo.packageName, photoUri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            i.component = ComponentName(res.activityInfo.packageName, res.activityInfo.name)

            startForCropImageResult.launch(i)
        }
    }

    private fun createImageFile(): File? {
        val imageFileName = "exchangeDiary_" + SimpleDateFormat("HHmmss").format(Date()) + "_"
        val storageDir: Array<File> = ContextCompat.getExternalFilesDirs(applicationContext, null)

        return File.createTempFile(imageFileName, ".jpg", storageDir[0])
    }
}

