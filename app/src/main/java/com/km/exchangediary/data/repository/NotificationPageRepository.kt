package com.km.exchangediary.data.repository

import com.km.exchangediary.data.local.pref.LoginPreferences
import com.km.exchangediary.data.remote.datasource.ExchangeDiaryDataSource
import com.km.exchangediary.data.remote.response.NotificationResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class NotificationPageRepository(
    private val dataSource: ExchangeDiaryDataSource,
    private val loginPreferences: LoginPreferences
) : CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    fun getJWT(): String = loginPreferences.getUserToken() ?: "INVALID"

    suspend fun getNotificationList(jwt: String): NotificationResponse =
        withContext(coroutineContext) {
            dataSource.getExchangeDiaryService().getNotifications(jwt)
        }
}