package com.km.exchangediary.domain.usecase

import com.km.exchangediary.data.repository.DiaryGroupRepository

class ChangeDiaryGroupNameUseCase(private val repository: DiaryGroupRepository) {
    suspend fun changeDiaryGroupName(diaryId: Long, changedName: String) {
        repository.changeDiaryGroupName("jwt " + repository.getJWT(), diaryId, changedName)
    }
}