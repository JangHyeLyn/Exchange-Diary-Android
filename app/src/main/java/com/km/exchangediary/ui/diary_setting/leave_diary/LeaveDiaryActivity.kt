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
import com.km.exchangediary.ui.DialogContentType
import com.km.exchangediary.utils.toPx

class LeaveDiaryActivity : BaseActivity<ActivityLeaveDiaryBinding>() {
    override fun layoutRes(): Int = R.layout.activity_leave_diary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTextColor()

        binding.tvLeaveDiaryButton.setOnClickListener {
            CommonDialog(
                titleVisible = false,
                contentType = DialogContentType.TEXT_VIEW,
                contentText = "해당 일기장을 탈퇴하시겠습니까?",
                contentTextSize = 14,
                onSuccess = { changeButtonDisable() }
            ).show(supportFragmentManager, "LeaveDiaryDialog")
        }
    }

    private fun setTextColor() {
        val color = getColor(R.color.notice)
        val spannable = SpannableString("탈퇴 후 해당 일기장은 목록에서 삭제됩니다.")
        spannable.setSpan(ForegroundColorSpan(color), 13, 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvLeaveDiaryDescription.setText(spannable, TextView.BufferType.SPANNABLE)
    }

    private fun changeButtonDisable() {
        binding.tvLeaveDiaryButton.visibility = View.GONE
        binding.tvSendDiaryButton.visibility = View.VISIBLE
        binding.tvLeaveDiaryButtonDisable.visibility = View.VISIBLE
    }
}