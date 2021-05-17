package com.km.exchangediary.domain.usecase

import com.km.exchangediary.data.repository.DiaryGroupRepository

class AddDiaryGroupUseCase(private val repository: DiaryGroupRepository) {
    suspend fun addDiaryGroup(diaryName: String): Boolean
        = repository.addDiaryGroup("jwt " + repository.getJWT(), diaryName).code in 200..299
}