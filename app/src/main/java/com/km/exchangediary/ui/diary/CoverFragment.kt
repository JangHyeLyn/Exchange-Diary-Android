package com.km.exchangediary.ui.diary

import android.os.Bundle
import android.view.View
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseFragment
import com.km.exchangediary.databinding.FragmentCoverBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoverFragment : BaseFragment<FragmentCoverBinding>() {
    private val viewModel: CoverViewModel by viewModel()
    override fun layoutRes(): Int = R.layout.fragment_cover

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.coverVm = viewModel
    }
}


