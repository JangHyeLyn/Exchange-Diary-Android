package com.km.exchangediary.ui.add_diary

import com.km.exchangediary.R
import com.km.exchangediary.base.BaseFragment
import com.km.exchangediary.databinding.FragmentAddDiaryDecorateCoverBinding

class DecorateCoverFragment : BaseFragment<FragmentAddDiaryDecorateCoverBinding>() {
    override fun layoutRes(): Int = R.layout.fragment_add_diary_decorate_cover

    companion object {
        val instance: DecorateCoverFragment by lazy {
            DecorateCoverFragment()
        }
    }
}