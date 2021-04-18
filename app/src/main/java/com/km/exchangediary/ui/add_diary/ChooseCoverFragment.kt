package com.km.exchangediary.ui.add_diary

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseFragment
import com.km.exchangediary.databinding.FragmentAddDiaryChooseCoverBinding

class ChooseCoverFragment : BaseFragment<FragmentAddDiaryChooseCoverBinding>() {
    override fun layoutRes(): Int = R.layout.fragment_add_diary_choose_cover
    private val tabNameList = arrayListOf("기본", "테마")

    companion object {
        val instance: ChooseCoverFragment by lazy {
            ChooseCoverFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpDiaryList.apply {
            adapter = ChooseCoverViewPagerAdapter(parentFragmentManager, lifecycle)
            isUserInputEnabled = false
        }

        TabLayoutMediator(binding.tabDiaryList, binding.vpDiaryList) { tab, position ->
            tab.text = tabNameList[position]
        }.attach()
    }
}