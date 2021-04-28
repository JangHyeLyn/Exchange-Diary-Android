package com.km.exchangediary.ui.group_management

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.observe
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityGroupManagementBinding
import com.km.exchangediary.ui.CommonDialog
import com.km.exchangediary.ui.DialogContentType
import com.km.exchangediary.ui.group_management.adapter.GroupItemTouchHelperCallback
import com.km.exchangediary.ui.group_management.adapter.GroupManagementAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

interface GroupItemDragListener {
    fun onDrag(holder: GroupManagementAdapter.GroupManagementViewHolder)
}

class GroupManagementActivity : BaseActivity<ActivityGroupManagementBinding>() {
    override fun layoutRes(): Int = R.layout.activity_group_management
    private val viewModel: GroupManagementViewModel by viewModel()
    private val addGroupDialog = CommonDialog(
        titleText = "그룹 추가",
        contentType = DialogContentType.EDIT_TEXT,
        onSuccess = { confirmGroupAddition() }
    )

    private lateinit var touchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView()
    }

    private fun bindingView() {
        initViewModel()
        binding.viewModel = viewModel

        val groupManagementAdapter = GroupManagementAdapter()
        binding.rvGroupList.adapter = groupManagementAdapter
        binding.rvGroupList.layoutManager = LinearLayoutManager(this)

        touchHelper = ItemTouchHelper(GroupItemTouchHelperCallback(groupManagementAdapter))
        (groupManagementAdapter).setDragListener(object : GroupItemDragListener {
            override fun onDrag(holder: GroupManagementAdapter.GroupManagementViewHolder) {
                touchHelper.startDrag(holder)
            }
        })
        touchHelper.attachToRecyclerView(binding.rvGroupList)

        binding.ivAddGroup.setOnClickListener {
            addGroupDialog.show(supportFragmentManager, "addGroupDialog")
            binding.rvGroupList.smoothScrollToPosition(groupManagementAdapter.itemCount)
        }
    }

    private fun initViewModel() {
        viewModel.refreshGroupList()

        viewModel.groupList.observe(this) {
            (binding.rvGroupList.adapter as GroupManagementAdapter).addGroupList(it)
        }
    }

    /* TODO: 그룹 추가 API 연결 */
    private fun confirmGroupAddition() {
        Toast.makeText(this, addGroupDialog.binding.etContents.text.toString(), Toast.LENGTH_LONG).show()
    }
}