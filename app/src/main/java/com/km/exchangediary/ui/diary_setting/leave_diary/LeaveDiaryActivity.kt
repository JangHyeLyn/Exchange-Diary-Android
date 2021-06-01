package com.km.exchangediary.ui.diary_setting.leave_diary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityLeaveDiaryBinding

class LeaveDiaryActivity : BaseActivity<ActivityLeaveDiaryBinding>() {
    override fun layoutRes(): Int = R.layout.activity_leave_diary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val color = getColor(R.color.notice)
        val str = "탈퇴 후 해당 일기장은 목록에서 삭제됩니다."
        val spannable = SpannableString("$str")
        spannable.setSpan(ForegroundColorSpan(color), 13, 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvLeaveDiaryDescription.setText(spannable, TextView.BufferType.SPANNABLE)
    }
}