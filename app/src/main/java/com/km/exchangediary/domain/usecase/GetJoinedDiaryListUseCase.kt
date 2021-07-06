package com.km.exchangediary.domain.usecase

import com.km.exchangediary.data.repository.HomeDiaryRepository
import com.km.exchangediary.ui.home.model.HomeDiary
import timber.log.Timber

class GetJoinedDiaryListUseCase(private val repository: HomeDiaryRepository) {
    suspend fun getJoinedDiaryList(): List<HomeDiary> {
        val result = arrayListOf<HomeDiary>()

        for (item in repository.getJoinedDiaryList("jwt " + repository.getJWT()).data) {
            /* TODO: 사람 수, 그룹 다시 적용 */
            result.add(HomeDiary(item.title, "item.group", item.nowWriter, 0, item.totalPage))
        }

        return result
    }
}