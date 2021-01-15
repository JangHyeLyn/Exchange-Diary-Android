package com.km.exchangediary.ui.diary.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.km.exchangediary.ui.diary.DiaryActivity

class DiaryViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private var itemList = ArrayList<Fragment>()

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun createFragment(position : Int): Fragment {
        return itemList[position]
    }

    fun addFragment(fragmentList : ArrayList<Fragment>){
        itemList = fragmentList
    }
}