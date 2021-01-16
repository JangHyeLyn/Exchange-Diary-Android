package com.km.exchangediary.ui.diary

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityDiaryBinding
import com.km.exchangediary.ui.diary.adapter.DiaryViewPagerAdapter
import com.km.exchangediary.uihelper.UiHelper
import kotlinx.android.synthetic.main.activity_diary.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiaryActivity : BaseActivity<ActivityDiaryBinding>(){
    private val viewModel: DiaryViewModel by viewModel()
    override fun layoutRes(): Int = R.layout.activity_diary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        UiHelper.toolBarInit(this, binding.tbDiary)

        binding.diaryVm = viewModel
        binding.vpDiary.adapter = DiaryViewPagerAdapter(supportFragmentManager, lifecycle)

        with (binding.vpDiary.adapter as DiaryViewPagerAdapter) {
            addFragment(arrayListOf(CoverFragment()))
        }
    }
}
