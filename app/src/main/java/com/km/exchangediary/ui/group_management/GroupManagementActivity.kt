package com.km.exchangediary.ui.group_management

import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityGroupManagementBinding
import com.km.exchangediary.ui.group_management.adapter.GroupManagementAdapter
import com.km.exchangediary.ui.group_management.adapter.GroupItemTouchHelperCallback
import com.km.exchangediary.ui.group_management.model.Group

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
        val groupManagementAdapter = GroupManagementAdapter()
        binding.rvGroupList.adapter = groupManagementAdapter
        binding.rvGroupList.layoutManager = LinearLayoutManager(this)

        touchHelper = ItemTouchHelper(GroupItemTouchHelperCallback(groupManagementAdapter))
        (groupManagementAdapter).setDragListener(object: GroupItemDragListener{
            override fun onDrag(holder: GroupManagementAdapter.GroupManagementViewHolder) {
                touchHelper.startDrag(holder)
            }
        })
        touchHelper.attachToRecyclerView(binding.rvGroupList)

        binding.ivAddGroup.setOnClickListener {
            /* TODO: 팝업이 생기면 팝업을 이용하여 그룹 정보를 추가하자. */
            groupManagementAdapter.addGroup(Group("안뇽", 5))
            binding.rvGroupList.smoothScrollToPosition(groupManagementAdapter.itemCount)
        }
    }
}