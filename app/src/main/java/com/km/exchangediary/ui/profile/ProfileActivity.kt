package com.km.exchangediary.ui.profile

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityProfileBinding


class ProfileActivity : BaseActivity<ActivityProfileBinding>() {
    //private val viewModel: ProfileViewModel by viewModel()
    override fun layoutRes(): Int = R.layout.activity_profile

    val pickImageFromAlbum = 0
    var uriPhoto: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding.vm = viewModel

        Glide.with(this).load("https://cdn.idreambank.com/news/photo/202004/81215_78965_4506.jpg")
            .circleCrop().error(
                R.drawable.sample
            ).into(binding.ivProfilePhoto)

        binding.ivProfileEdit.setOnClickListener {
            editProfile()
        }

        binding.btnProfileEditEnd.setOnClickListener {
            endEditProfile()
        }

        binding.ivProfilePhotoAdd.setOnClickListener {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
            var photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, pickImageFromAlbum)
        }
    }

    fun editProfile() {

        val apply = binding.apply {

            editProfileName.isEnabled = true
            tvProfileInfo.visibility = View.GONE
            ivProfileEdit.visibility = View.GONE
            tvProfileInfoLength.visibility = View.VISIBLE
            btnProfileEditEnd.visibility = View.VISIBLE
            ivProfilePhotoAdd.visibility = View.VISIBLE
            editProfileInfo.visibility = View.VISIBLE

            editProfileName.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.grey)
            editProfileInfo.text = tvProfileInfo.text
            tvProfileTitle.text = "프로필편집"
            tvProfileInfoLength.text = tvProfileInfo.length().toString() + "/70"

            editProfileInfo.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    var input = editProfileInfo.text.toString()
                    tvProfileInfoLength.text = input.length.toString() + "/70"
                }

                override fun afterTextChanged(s: Editable) {}
            })
        }
    }

    fun endEditProfile() {
//        var input = binding.editProfileInfo.text + ""

        binding.apply {
            editProfileName.isEnabled = false
            tvProfileInfo.visibility = View.VISIBLE
            ivProfileEdit.visibility = View.VISIBLE
            tvProfileInfoLength.visibility = View.GONE
            btnProfileEditEnd.visibility = View.GONE
            ivProfilePhotoAdd.visibility = View.GONE
            editProfileInfo.visibility = View.GONE

//            tvProfileInfo.setText(editProfileInfo.text + "")

//            tvProfileInfo.text = editProfileInfo.text

            editProfileName.backgroundTintList = ContextCompat.getColorStateList(
                applicationContext,
                R.color.transparent
            )
            tvProfileTitle.text = "프로필"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImageFromAlbum) {
            if (resultCode == Activity.RESULT_OK) {
                uriPhoto = data?.data
                Glide.with(this).load(uriPhoto).circleCrop().error(R.drawable.sample)
                    .into(binding.ivProfilePhoto)
            } else {
                finish()
            }
        }
    }
}