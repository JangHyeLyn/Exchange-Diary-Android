package com.km.exchangediary.ui.diary

import android.annotation.SuppressLint
import android.gesture.GestureOverlayView
import android.gesture.GestureOverlayView.OnGestureListener
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.view.GestureDetectorCompat
import androidx.viewpager2.widget.ViewPager2
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityDiaryBinding
import com.km.exchangediary.ui.diary.adapter.DiaryViewPagerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class DiaryActivity: BaseActivity<ActivityDiaryBinding>() {
    private val viewModel: DiaryViewModel by viewModel()
    override fun layoutRes(): Int = R.layout.activity_diary

    var max: Int = 20

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.diaryVm = viewModel

        binding.vpDiary.adapter = DiaryViewPagerAdapter(supportFragmentManager, lifecycle)

        seekBarInit()

        with(binding.vpDiary.adapter as DiaryViewPagerAdapter) {
            addFragment(arrayListOf(CoverFragment(), CoverFragment(), CoverFragment(), CoverFragment(), CoverFragment(), CoverFragment(), CoverFragment()))
        }

        //뷰페이저 움직이면 시크바 변경
        binding.vpDiary.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setSeekBarChange(max, 0, position, binding.tvTotalDiaryPage)
            }
        })


        //시크바 움직이면 뷰페이져 같이 움직이기
        binding.sbPageNumber.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                setSeekBarChange(max, 0, progress, binding.tvTotalDiaryPage)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        // 이벤트 재정의
        var detector = GestureDetector(this, object:GestureDetector.OnGestureListener{
            override fun onDown(p0: MotionEvent?): Boolean {
                Log.d("test", "onDown")
                return false
            }

            override fun onShowPress(p0: MotionEvent?) {
                Log.d("test", "onShowPress")
            }

            override fun onSingleTapUp(p0: MotionEvent?): Boolean {
                Log.d("test", "onSingleTapUp")

                if (binding.sbContainer.visibility == View.VISIBLE) {
                    binding.sbContainer.visibility = View.INVISIBLE
                    binding.tbDiary.title = ""

                } else {
                    binding.sbContainer.visibility = View.VISIBLE
                    binding.tbDiary.title = "최대열네글자를쓸수있게하고자"
                }

                return false
            }

            override fun onScroll(
                    p0: MotionEvent?,
                    p1: MotionEvent?,
                    p2: Float,
                    p3: Float
            ): Boolean {
                Log.d("test", "onScroll")
                return false
            }

            override fun onLongPress(p0: MotionEvent?) {
                Log.d("test", "onLongPress")
            }

            override fun onFling(
                    p0: MotionEvent?,
                    p1: MotionEvent?,
                    p2: Float,
                    p3: Float
            ): Boolean {
                Log.d("test", "onFling")
                return false
            }
        })


        // 뷰페이저의 0번째 자식은 리사이클러뷰
        binding.vpDiary.getChildAt(0).setOnTouchListener{v, event->
            detector.onTouchEvent(event)
            false
        }
    }

    private fun seekBarInit() {
        binding.sbPageNumber.max = max
        binding.tvTotalDiaryPage.text = "0 / $max"
    }

    private fun setSeekBarChange(max: Int, min: Int, progress: Int, tv: TextView) {
        var page: Int = min + (progress * 1)
        binding.tvTotalDiaryPage.text = "$page / $max"
        binding.vpDiary.currentItem = page
    }
}
