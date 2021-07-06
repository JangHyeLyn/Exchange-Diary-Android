package com.km.exchangediary.data.repository

import com.km.exchangediary.data.local.pref.LoginPreferences
import com.km.exchangediary.data.remote.datasource.ExchangeDiaryDataSource
import com.km.exchangediary.data.remote.response.JoinedDiaryListResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class HomeDiaryRepository(private val dataSource: ExchangeDiaryDataSource, private val localPreferences: LoginPreferences): CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    suspend fun getJoinedDiaryList(jwt: String): JoinedDiaryListResponse =
        withContext(coroutineContext) {
            dataSource.getExchangeDiaryService().getJoinedDiaryList(jwt)
        }

    fun getJWT() = localPreferences.getUserToken() ?: "INVALID"
}