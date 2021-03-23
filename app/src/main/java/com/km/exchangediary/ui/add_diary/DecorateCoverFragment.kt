package com.km.exchangediary.ui.add_diary

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val layoutCoverLayoutParams = binding.layoutCover.layoutParams
                layoutCoverLayoutParams.height = (binding.layoutCover.width * 1.48125).toInt()

                binding.layoutCover.layoutParams = layoutCoverLayoutParams

                view.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}