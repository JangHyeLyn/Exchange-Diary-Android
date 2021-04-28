package com.km.exchangediary.ui.group_management

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.km.exchangediary.base.BaseViewModel
import com.km.exchangediary.domain.usecase.GetGroupListUseCase
import com.km.exchangediary.ui.group_management.model.DiaryGroup
import kotlinx.coroutines.launch

class GroupManagementViewModel(private val getGroupListUseCase: GetGroupListUseCase) : BaseViewModel() {
    private val _groupList = MutableLiveData<List<DiaryGroup>>()
    val groupList: LiveData<List<DiaryGroup>> = _groupList

    fun refreshGroupList() {
        launch {
            _groupList.value = getGroupListUseCase.getDiaryGroupList()
        }
    }
}