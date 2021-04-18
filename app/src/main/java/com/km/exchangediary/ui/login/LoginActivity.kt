package com.km.exchangediary.ui.login

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityLoginBinding
import com.km.exchangediary.ui.login.adapter.OnBoardingViewPagerAdapter

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun layoutRes(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingView()
    }

    private fun bindingView() {
        binding.vpOnBoarding.adapter = OnBoardingViewPagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.tabOnBoarding, binding.vpOnBoarding) { tab, _ ->
            tab.view.isClickable = false
        }.attach()
    }
}