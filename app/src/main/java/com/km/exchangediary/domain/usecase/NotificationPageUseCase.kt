package com.km.exchangediary.domain.usecase

import com.km.exchangediary.data.repository.NotificationPageRepository
import com.km.exchangediary.ui.notification_page.model.NotificationList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class NotificationPageUseCase(private val repository: NotificationPageRepository) : CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO

    suspend fun getNotificationList(): List<NotificationList> {
        return withContext(coroutineContext) {
            val result = ArrayList<NotificationList>()
            for (item in repository.getNotificationList("jwt " + repository.getJWT()).data) {
                result.add(
                    NotificationList(
                        diaryTitle = item.diaryTitle,
                        diaryGroup = item.diaryGroup,
                        message = item.message,
                        createdAt = item.createdAt
                    )
                )
            }
            result
        }
    }
}