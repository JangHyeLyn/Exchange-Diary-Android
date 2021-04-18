package com.km.exchangediary.ui.login.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.km.exchangediary.ui.login.LoginFragment
import com.km.exchangediary.ui.login.OnBoardingFragment

class OnBoardingViewPagerAdapter(fragmentManager: FragmentManager, lifeCycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifeCycle) {
    private val fragmentList = arrayListOf<Fragment>(
        OnBoardingFragment(0),
        OnBoardingFragment(1),
        OnBoardingFragment(2),
        LoginFragment()
    )

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}