package com.km.exchangediary.ui.add_diary

import android.os.Bundle
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityCompleteDiaryCreationBinding

class CompleteDiaryCreationActivity : BaseActivity<ActivityCompleteDiaryCreationBinding>() {
    override fun layoutRes(): Int = R.layout.activity_complete_diary_creation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBind()
    }

    private fun onBind() {
        binding.btnGoToHome.setOnClickListener {
            finish()
        }
    }
}