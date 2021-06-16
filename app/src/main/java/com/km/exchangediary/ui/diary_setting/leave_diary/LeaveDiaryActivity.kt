package com.km.exchangediary.ui.diary_setting.leave_diary

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityLeaveDiaryBinding
import com.km.exchangediary.ui.CommonDialog

class LeaveDiaryActivity : BaseActivity<ActivityLeaveDiaryBinding>() {
    override fun layoutRes(): Int = R.layout.activity_leave_diary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTextColor()

        binding.tvLeaveDiaryButton.setOnClickListener {
            CommonDialog(
                titleText = "해당 일기장을 탈퇴하시겠습니까?",
                onSuccess = { changeButton() }
            ).show(supportFragmentManager, "tag")
        }
    }

    private fun setTextColor() {
        val color = getColor(R.color.notice)
        val spannable = SpannableString("탈퇴 후 해당 일기장은 목록에서 삭제됩니다.")
        spannable.setSpan(ForegroundColorSpan(color), 13, 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvLeaveDiaryDescription.setText(spannable, TextView.BufferType.SPANNABLE)
    }

    private fun changeButton() {
        binding.tvLeaveDiaryButton.visibility = View.GONE
        binding.tvSendDiaryButton.visibility = View.VISIBLE
        binding.tvLeaveDiaryButtonDisable.visibility = View.VISIBLE
    }
}