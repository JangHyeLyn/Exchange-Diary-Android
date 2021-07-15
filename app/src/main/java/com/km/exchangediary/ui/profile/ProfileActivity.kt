package com.km.exchangediary.ui.profile

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : BaseActivity<ActivityProfileBinding>() {
    override fun layoutRes(): Int = R.layout.activity_profile
    private val viewModel: ProfileViewModel by viewModel()

    private val startForProfileActivity: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                loadDataFromPrefs()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadDataFromPrefs()
        clickListeners()
    }

    private fun clickListeners() {
        binding.ibProfileEdit.setOnClickListener {
            val intent = Intent(this, ProfileEditActivity::class.java)
            startForProfileActivity.launch(intent)
        }

        binding.ibProfileBack.setOnClickListener {
            finish()
        }

        binding.tvLogout.setOnClickListener {
            /* TODO 로그아웃 */
        }
    }

    private fun loadDataFromPrefs(){
        val profileDataClass = viewModel.getProfileFromPrefs()

        Glide.with(this).load(Base64.decode(profileDataClass.profileImage, Base64.DEFAULT))
            .circleCrop()
            .error(R.drawable.ic_profile_default_image_1).into(binding.ivProfilePhoto)
        binding.tvProfileName.text = profileDataClass.name
        binding.tvProfileIntroduction.setText(profileDataClass.introduction)
    }
}