package com.km.exchangediary.ui.add_diary

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.km.exchangediary.ui.home.HomeDiaryGroupFragment

class ChooseCoverViewPagerAdapter(fragmentManager: FragmentManager, lifeCycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifeCycle) {
    /* TODO: 여기 프래그먼트 바꿔야함 */
    private val fragmentList = arrayListOf<Fragment>(
            HomeDiaryGroupFragment(),
            HomeDiaryGroupFragment()
    )

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}