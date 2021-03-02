package com.km.exchangediary.ui.add_diary

import com.km.exchangediary.R
import com.km.exchangediary.base.BaseFragment
import com.km.exchangediary.databinding.FragmentAddDiaryChooseCoverBinding

class ChooseCoverFragment : BaseFragment<FragmentAddDiaryChooseCoverBinding>() {
    override fun layoutRes(): Int = R.layout.fragment_add_diary_choose_cover

    companion object {
        val instance: ChooseCoverFragment by lazy {
            ChooseCoverFragment()
        }
    }
}