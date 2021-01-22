package com.km.exchangediary.ui.group_management

import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityGroupManagementBinding
import com.km.exchangediary.ui.group_management.adapter.GroupManagementAdapter
import com.km.exchangediary.ui.group_management.adapter.GroupItemTouchHelperCallback

interface GroupItemDragListener {
    fun onDrag(holder: GroupManagementAdapter.GroupManagementViewHolder)
}

class GroupManagementActivity : BaseActivity<ActivityGroupManagementBinding>() {
    override fun layoutRes(): Int = R.layout.activity_group_management

    private lateinit var touchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView()
    }

    private fun bindingView() {
        binding.rvGroupList.adapter = GroupManagementAdapter()
        binding.rvGroupList.layoutManager = LinearLayoutManager(this)

        touchHelper = ItemTouchHelper(GroupItemTouchHelperCallback(binding.rvGroupList.adapter as GroupManagementAdapter))
        (binding.rvGroupList.adapter as GroupManagementAdapter).setDragListener(object: GroupItemDragListener{
            override fun onDrag(holder: GroupManagementAdapter.GroupManagementViewHolder) {
                touchHelper.startDrag(holder)
            }
        })
        touchHelper.attachToRecyclerView(binding.rvGroupList)
    }
}