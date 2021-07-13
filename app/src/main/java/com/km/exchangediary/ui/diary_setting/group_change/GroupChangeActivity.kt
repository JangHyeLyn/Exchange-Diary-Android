package com.km.exchangediary.ui.diary_setting.group_change

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityGroupChangeBinding
import com.km.exchangediary.utils.toPx

class GroupChangeActivity : BaseActivity<ActivityGroupChangeBinding>() {
    override fun layoutRes(): Int = R.layout.activity_group_change

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBindView()
    }

    private fun onBindView() {
        binding.rvGroupList.apply {
            adapter = GroupChangeAdapter()
            layoutManager = LinearLayoutManager(
                this@GroupChangeActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
        }

        binding.rvGroupList.addItemDecoration(GroupChangeItemDecoration(4.toPx().toFloat()))
    }
}