package com.km.exchangediary.ui.group_management

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.km.exchangediary.base.BaseViewModel
import com.km.exchangediary.domain.usecase.AddDiaryGroupUseCase
import com.km.exchangediary.domain.usecase.ChangeDiaryGroupNameUseCase
import com.km.exchangediary.domain.usecase.DeleteDiaryGroupUseCase
import com.km.exchangediary.domain.usecase.GetGroupListUseCase
import com.km.exchangediary.ui.group_management.model.DiaryGroup
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GroupManagementViewModel(
    private val getGroupListUseCase: GetGroupListUseCase,
    private val addDiaryGroupUseCase: AddDiaryGroupUseCase,
    private val deleteDiaryGroupUseCase: DeleteDiaryGroupUseCase,
    private val changeDiaryGroupNameUseCase: ChangeDiaryGroupNameUseCase
) : BaseViewModel() {
    private val _groupList = MutableLiveData<List<DiaryGroup>>()
    val groupList: LiveData<List<DiaryGroup>> = _groupList

    fun refreshGroupList() {
        launch {
            _groupList.value = getGroupListUseCase.getDiaryGroupList()
        }
    }

    fun addDiaryGroup(diaryName: String) {
        launch {
            if (addDiaryGroupUseCase.addDiaryGroup(diaryName)) {
                refreshGroupList()
            }
        }
    }

    fun deleteDiaryGroup(diaryId: Long) {
        launch {
            withContext(coroutineContext) {
                deleteDiaryGroupUseCase.deleteDiaryGroup(diaryId)
            }
            refreshGroupList()
        }
    }

    fun changeDiaryGroupName(diaryId: Long, changedName: String) {
        launch {
            withContext(coroutineContext) {
                changeDiaryGroupNameUseCase.changeDiaryGroupName(diaryId, changedName)
            }
            refreshGroupList()
        }
    }
}
