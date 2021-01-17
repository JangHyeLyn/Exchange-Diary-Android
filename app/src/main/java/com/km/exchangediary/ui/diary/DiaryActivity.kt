package com.km.exchangediary.ui.diary

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityDiaryBinding
import com.km.exchangediary.ui.diary.adapter.DiaryViewPagerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiaryActivity : BaseActivity<ActivityDiaryBinding>(){
    private val viewModel: DiaryViewModel by viewModel()
    override fun layoutRes(): Int = R.layout.activity_diary

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.diaryVm = viewModel
        binding.vpDiary.adapter = DiaryViewPagerAdapter(supportFragmentManager, lifecycle)

        with (binding.vpDiary.adapter as DiaryViewPagerAdapter) {
            addFragment(arrayListOf(CoverFragment()))
        }


    }
}

