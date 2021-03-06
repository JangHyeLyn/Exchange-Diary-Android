package com.km.exchangediary.ui.group_management

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityGroupManagementBinding
import com.km.exchangediary.ui.CommonDialog
import com.km.exchangediary.ui.DialogContentType
import com.km.exchangediary.ui.group_management.adapter.GroupItemTouchHelperCallback
import com.km.exchangediary.ui.group_management.adapter.GroupManagementAdapter
import com.km.exchangediary.ui.group_management.model.DiaryGroup
import org.koin.androidx.viewmodel.ext.android.viewModel

class GroupManagementActivity : BaseActivity<ActivityGroupManagementBinding>() {
    override fun layoutRes(): Int = R.layout.activity_group_management
    private val viewModel: GroupManagementViewModel by viewModel()
    private val addGroupDialog = CommonDialog(
        titleText = "그룹 추가",
        contentType = DialogContentType.EDIT_TEXT,
        onSuccess = { confirmGroupAddition() }
    )
    private val changeDiaryGroupNameDialog = CommonDialog(
        titleText = "그룹명 수정",
        contentType = DialogContentType.EDIT_TEXT
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
        setupAdapter(groupManagementAdapter)

        binding.rvGroupList.adapter = groupManagementAdapter
        binding.rvGroupList.layoutManager = LinearLayoutManager(this)
        touchHelper = ItemTouchHelper(GroupItemTouchHelperCallback(groupManagementAdapter))
        touchHelper.attachToRecyclerView(binding.rvGroupList)

        binding.btnAddGroup.setOnClickListener {
            addGroupDialog.show(supportFragmentManager, "addGroupDialog")
            binding.rvGroupList.smoothScrollToPosition(groupManagementAdapter.itemCount)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupAdapter(groupManagementAdapter: GroupManagementAdapter) {
        groupManagementAdapter.apply {
            setOnDrag { holder ->
                touchHelper.startDrag(holder)
            }
            setDeleteDiaryGroup { diaryId ->
                viewModel.deleteDiaryGroup(diaryId)
            }
            setChangeDiaryGroupName { diaryId ->
                changeDiaryGroupNameDialog.setOnSuccess {
                    viewModel.changeDiaryGroupName(diaryId, changeDiaryGroupNameDialog.binding.etContents.text.toString())
                    changeDiaryGroupNameDialog.clearEditText()
                }
                changeDiaryGroupNameDialog.show(supportFragmentManager, "changeDiaryGroupNameDialog")
            }
            setReorderDiaryGroups { diaryGroupList ->
                viewModel.reorderDiaryGroups(diaryGroupList)
            }
        }
    }

    private fun initViewModel() {
        viewModel.refreshGroupList()
    }

    private fun confirmGroupAddition() {
        viewModel.addDiaryGroup(addGroupDialog.binding.etContents.text.toString())
        addGroupDialog.clearEditText()
        Toast.makeText(this, "그룹이 추가되었습니다.", Toast.LENGTH_LONG).show()
    }
}
