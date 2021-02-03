package com.km.exchangediary.ui.diary

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import androidx.viewpager2.widget.ViewPager2
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityDiaryBinding
import com.km.exchangediary.ui.diary.adapter.DiaryViewPagerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiaryActivity: BaseActivity<ActivityDiaryBinding>(), GestureDetector.OnGestureListener {
    private val viewModel: DiaryViewModel by viewModel()
    override fun layoutRes(): Int = R.layout.activity_diary

    var max: Int = 40

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.diaryVm = viewModel

        binding.vpDiary.adapter = DiaryViewPagerAdapter(supportFragmentManager, lifecycle)

        with(binding.vpDiary.adapter as DiaryViewPagerAdapter) {
            addFragment(arrayListOf(CoverFragment(), CoverFragment(), CoverFragment(), CoverFragment(), CoverFragment(), CoverFragment(), CoverFragment()))
        }

        seekBarInit()

        var detector = GestureDetector(applicationContext,this)

        // 뷰페이저의 0번째 자식은 리사이클러뷰
        binding.vpDiary.getChildAt(0).setOnTouchListener{v, event->
            detector.onTouchEvent(event)
            false
        }

        //뷰페이저 움직이면 시크바 변경
        binding.vpDiary.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.sbPageNumber.progress = position
                updatePage(position)
            }
        })

        //시크바 움직이면 뷰페이져 같이 움직이기
        binding.sbPageNumber.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.vpDiary.currentItem = progress
                updatePage(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun seekBarInit() {
        binding.sbPageNumber.max = max
        binding.tvTotalDiaryPage.text = "0 / $max"
    }

    private fun updatePage(position: Int){
        var page: String = "" + position
        binding.tvTotalDiaryPage.text = "$page/ $max"
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return false
    }

    override fun onShowPress(e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        if (binding.sbContainer.visibility == View.VISIBLE) {
            binding.sbContainer.visibility = View.INVISIBLE
            binding.ivDiaryBack.visibility = View.INVISIBLE
            binding.tvDiaryName.visibility = View.INVISIBLE
            binding.ivDiaryText.visibility = View.INVISIBLE
            binding.switchDiary.visibility = View.INVISIBLE
            binding.ivDiaryImage.visibility = View.INVISIBLE
            binding.btNextPage.visibility = View.INVISIBLE
        } else {
            binding.sbContainer.visibility = View.VISIBLE
            binding.ivDiaryBack.visibility = View.VISIBLE
            binding.tvDiaryName.visibility = View.VISIBLE
            binding.ivDiaryText.visibility = View.VISIBLE
            binding.switchDiary.visibility = View.VISIBLE
            binding.ivDiaryImage.visibility = View.VISIBLE
            binding.btNextPage.visibility = View.VISIBLE
        }
        return false

    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        return false
    }
}
