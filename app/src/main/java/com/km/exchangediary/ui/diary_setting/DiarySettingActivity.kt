package com.km.exchangediary.ui.diary_setting

import android.content.Intent
import android.os.Bundle
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityDiarySettingBinding
import com.km.exchangediary.ui.diary_setting.diary_close.DiaryCloseActivity
import com.km.exchangediary.ui.diary_setting.group_change.GroupChangeActivity
import com.km.exchangediary.ui.diary_setting.leave_diary.LeaveDiaryActivity

class DiarySettingActivity : BaseActivity<ActivityDiarySettingBinding>() {
    override fun layoutRes(): Int = R.layout.activity_diary_setting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnGroupChange.setOnClickListener {
            startActivity(Intent(this, GroupChangeActivity::class.java))
        }
        binding.btnDiaryClose.setOnClickListener {
            startActivity(Intent(this, DiaryCloseActivity::class.java))
        }
        binding.btnLeaveDiary.setOnClickListener {
            startActivity(Intent(this, LeaveDiaryActivity::class.java))
        }
    }
}