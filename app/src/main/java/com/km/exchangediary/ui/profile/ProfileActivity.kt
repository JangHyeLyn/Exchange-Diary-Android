package com.km.exchangediary.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.data.entity.ProfileResult
import com.km.exchangediary.data.remote.service.BASE_URL
import com.km.exchangediary.data.remote.service.ProfileService
import com.km.exchangediary.databinding.ActivityProfileBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.System.load

class ProfileActivity : BaseActivity<ActivityProfileBinding>() {
    override fun layoutRes(): Int = R.layout.activity_profile
    lateinit var profileIamgeUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getProfileFromServer(this)
        clickListeners()
    }

    private fun clickListeners() {
        binding.ivProfileEdit.setOnClickListener {
            val intent = Intent(this, ProfileEditActivity::class.java).apply {
                putExtra("name", binding.tvProfileName.text.toString())
                putExtra("introduction", binding.tvProfileIntroduction.text.toString())
                putExtra("profileIamgeUrl", profileIamgeUrl)
            }
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

    fun getProfileFromServer(activity: ProfileActivity) {
        val retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build() /* TODO 위치수정 */
        val service = retrofit.create(ProfileService::class.java)

        service.getProfile().enqueue(object : Callback<ProfileResult> {
            override fun onResponse(call: Call<ProfileResult>, response: Response<ProfileResult>) {
                if (response.isSuccessful) {
                    val profileItem: ProfileResult? = response.body()

                    profileIamgeUrl = profileItem?.data?.profileImage.toString()

                    /* TODO 오류시 띄울 이미지(기본이미지) 수정 */
                    Glide.with(activity).load(profileItem?.data?.profileImage).circleCrop()
                        .error(R.drawable.ic_profile_dafault_image_1).into(binding.ivProfilePhoto)
                    binding.tvProfileName.text = profileItem?.data?.userName
                    binding.tvProfileIntroduction.setText(profileItem?.data?.userIntroduction)
                }
            }

            override fun onFailure(call: Call<ProfileResult>, t: Throwable) {}
        })
    }
}