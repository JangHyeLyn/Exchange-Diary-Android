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


class ProfileActivity : BaseActivity<ActivityProfileBinding>() {
    override fun layoutRes(): Int = R.layout.activity_profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* TODO 오류시 띄울 이미지(기본이미지) 수정 */
        Glide.with(this).load("https://cdn.idreambank.com/news/photo/202004/81215_78965_4506.jpg")
            .circleCrop().error(R.drawable.sample).into(binding.ivProfilePhoto)

        binding.ivProfileEdit.setOnClickListener {
            val intent = Intent(this, ProfileEditActivity::class.java).apply {
                putExtra("name", binding.tvProfileName.text.toString())
                putExtra("info", binding.tvProfileInfo.text.toString())
            }
            startActivity(intent)
        }

        getProfileFromServer()

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

    fun getProfileFromServer() {

        val retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build() /* TODO 위치수정 */
        val service = retrofit.create(ProfileService::class.java)
        val callGetResult = service.getProfile()

        callGetResult.enqueue(object : Callback<ProfileResult> {
            override fun onResponse(call: Call<ProfileResult>, response: Response<ProfileResult>) {
                Log.d("testtest", "성공 : ${response.raw()}")
            }

            override fun onFailure(call: Call<ProfileResult>, t: Throwable) {
                Log.d("testtest", "실패 : $t")
            }
        })
    }
}