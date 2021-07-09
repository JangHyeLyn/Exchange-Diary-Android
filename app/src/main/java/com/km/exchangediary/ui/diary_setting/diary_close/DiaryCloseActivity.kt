package com.km.exchangediary.ui.diary_setting.diary_close

import android.os.Bundle
import android.view.View
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityDiaryCloseBinding
import com.km.exchangediary.ui.CommonDialog

class DiaryCloseActivity : BaseActivity<ActivityDiaryCloseBinding>() {
    override fun layoutRes(): Int = R.layout.activity_diary_close

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvDiaryCloseButton.setOnClickListener {
            CommonDialog(
                titleText = "일기장을 임의종료하시겠습니까?",
                contentText = "확인 선택시 멤버들에게 투표 요청 알림이 전송됩니다.\n" +
                        "임의종료 후 해당 일기장에서의 일기 작성이 불가하며,\n" +
                        "지금까지 저장된 일기는 삭제되지 않습니다.",
                highlightText = listOf(Pair(59, 83)),
                confirmText = "확인",
                cancelText = "취소",
                onSuccess = { changeButton() }
            ).show(supportFragmentManager, "tag")
        }
    }

    private fun changeButton() {
        binding.tvDiaryCloseButton.visibility = View.GONE
        binding.tvCountAgreeMember.visibility = View.VISIBLE
        binding.tvDiaryCloseVotingProgress.visibility = View.VISIBLE
    }
}