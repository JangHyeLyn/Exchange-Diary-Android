package com.km.exchangediary.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import com.bumptech.glide.Glide
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : BaseActivity<ActivityProfileBinding>() {
    override fun layoutRes(): Int = R.layout.activity_profile
    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        val profileDataClass = viewModel.getProfileFromPrefs(applicationContext)

        Glide.with(this).load(Base64.decode(profileDataClass?.profileImage, Base64.DEFAULT)).circleCrop()
            .error(R.drawable.ic_profile_default_image_1).into(binding.ivProfilePhoto)
        binding.tvProfileName.text = profileDataClass?.name
        binding.tvProfileIntroduction.setText(profileDataClass?.introduction)

        clickListeners()
    }

    private fun clickListeners() {
        binding.ivProfileEdit.setOnClickListener {
            val intent = Intent(this, ProfileEditActivity::class.java)
            startActivity(intent)
        }

        binding.ivProfileBack.setOnClickListener {
            finish()
        }

        binding.tvSignout.setOnClickListener {
            /* TODO 로그아웃 */
        }

        binding.tvVersion.setOnClickListener {
            /* TODO 버전 정보 */
        }
    }
}