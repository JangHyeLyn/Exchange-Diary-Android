package com.km.exchangediary.ui.profile

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityProfileBinding


class ProfileActivity : BaseActivity<ActivityProfileBinding>() {
    override fun layoutRes(): Int = R.layout.activity_profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Glide.with(this).load("https://cdn.idreambank.com/news/photo/202004/81215_78965_4506.jpg")
            .circleCrop().error(R.drawable.sample).into(binding.ivProfilePhoto)

        binding.ivProfileEdit.setOnClickListener {
            val intent = Intent(this, ProfileEditActivity::class.java)
            intent.putExtra("name", binding.tvProfileName.text.toString())
            intent.putExtra("info", binding.tvProfileInfo.text.toString())
            startActivity(intent)
        }

        binding.ivProfileBack.setOnClickListener {
            finish()
        }

        binding.btnSignout.setOnClickListener {
            /* TODO 로그아웃 버튼 */
        }

        binding.btnVersionInfo.setOnClickListener {
            /* TODO 버전 정보 버튼 */
        }
    }
}