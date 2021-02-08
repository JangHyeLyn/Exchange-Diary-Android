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

        /* TODO 오류시 띄울 이미지(기본이미지) 수정 */
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

        binding.tvSignout.setOnClickListener {
            /* TODO 로그아웃 버튼 */
        }

        binding.tvVersion.setOnClickListener {
            /* TODO 버전 정보 버튼 */
        }
    }
}