package com.km.exchangediary.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.km.exchangediary.ui.home.HomeDiaryGroupFragment

// ViewPager2
class HomeDiaryListAdapter(fragmentManager: FragmentManager, lifeCycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifeCycle) {
    /* TODO: 그룹 관리가 만들어지면 그룹 관리와 엮자 */
    private val fragmentList = arrayListOf<Fragment>(
            HomeDiaryGroupFragment(),
            HomeDiaryGroupFragment(),
            HomeDiaryGroupFragment(),
            HomeDiaryGroupFragment(),
            HomeDiaryGroupFragment()
    )

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}