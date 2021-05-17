package com.km.exchangediary.domain.usecase

import com.km.exchangediary.data.repository.DiaryGroupRepository

class DeleteDiaryGroupUseCase(private val repository: DiaryGroupRepository) {
    suspend fun deleteDiaryGroup(diaryId: Long): Boolean {
        return repository.deleteDiaryGroup("jwt " + repository.getJWT(), diaryId).code == 200
    }
}