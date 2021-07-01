package com.km.exchangediary.domain.usecase

import com.km.exchangediary.data.repository.DiaryGroupRepository
import com.km.exchangediary.ui.group_management.model.DiaryGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class GetGroupListUseCase(private val repository: DiaryGroupRepository) : CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO

    suspend fun getDiaryGroupList(): List<DiaryGroup> {
        return withContext(coroutineContext) {
            val result = ArrayList<DiaryGroup>()
            for (item in repository.getDiaryGroupList("jwt " + repository.getJWT()).data.diaryGroups) {
                result.add(DiaryGroup(id = item.id, rank = item.rank, title = item.title, numberOfPeople = item.count))
            }
            result
        }
    }
}