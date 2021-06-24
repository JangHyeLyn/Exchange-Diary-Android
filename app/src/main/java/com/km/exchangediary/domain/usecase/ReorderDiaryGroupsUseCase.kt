package com.km.exchangediary.domain.usecase

import com.km.exchangediary.data.remote.request.ReorderDiaryGroupsRequestBody
import com.km.exchangediary.data.repository.DiaryGroupRepository
import com.km.exchangediary.ui.group_management.model.DiaryGroup

class ReorderDiaryGroupsUseCase(private val repository: DiaryGroupRepository) {
    suspend fun reorderDiaryGroups(diaryGroupList: List<DiaryGroup>) {
        val reorderDiaryGroupsRequestBody = arrayListOf<ReorderDiaryGroupsRequestBody>()
        for (item in diaryGroupList) {
            reorderDiaryGroupsRequestBody.add(ReorderDiaryGroupsRequestBody(item.id, item.rank))
        }

        repository.reorderDiaryGroups("jwt " + repository.getJWT(), reorderDiaryGroupsRequestBody)
    }
}