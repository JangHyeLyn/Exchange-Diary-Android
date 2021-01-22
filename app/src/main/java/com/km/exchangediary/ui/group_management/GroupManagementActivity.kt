package com.km.exchangediary.ui.group_management

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityGroupManagementBinding

class GroupManagementActivity : BaseActivity<ActivityGroupManagementBinding>() {
    override fun layoutRes(): Int = R.layout.activity_group_management

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView()
    }

    private fun bindingView() {
        binding.rvGroupList.adapter = GroupManagementAdapter()
        binding.rvGroupList.layoutManager = LinearLayoutManager(this)
    }
}