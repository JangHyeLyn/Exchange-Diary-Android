package com.km.exchangediary.domain.usecase

import com.km.exchangediary.data.repository.DiaryGroupRepository
import com.km.exchangediary.data.repository.HomeDiaryRepository
import com.km.exchangediary.ui.home.model.HomeDiary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import timber.log.Timber

class GetJoinedDiaryListUseCase(
    private val homeDiaryRepository: HomeDiaryRepository,
    private val diaryGroupRepository: DiaryGroupRepository
) {
    suspend fun getJoinedDiaryList(): List<HomeDiary> {
        val result = arrayListOf<HomeDiary>()
        val jwt = "jwt " + homeDiaryRepository.getJWT()

        val diaryMap = HashMap<Long, String>()
        for (item in diaryGroupRepository.getDiaryGroupList(jwt).data.diaryGroups) {
            diaryMap[item.id] = item.title
        }

        for (item in homeDiaryRepository.getJoinedDiaryList(jwt).data) {
            /* TODO: 사람 수 다시 적용 */
            if (diaryMap.containsKey(item.group)) {
                result.add(HomeDiary(item.title, diaryMap[item.group], item.nowWriter, 0, item.totalPage))
            } else {
                result.add(HomeDiary(item.title, null, item.nowWriter, 0, item.totalPage))
            }
        }

        return result
    }
}