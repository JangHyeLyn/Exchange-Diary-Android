package com.km.exchangediary.data.repository

import com.google.gson.JsonObject
import com.km.exchangediary.data.local.pref.LoginPreferences
import com.km.exchangediary.data.remote.datasource.ExchangeDiaryDataSource
import com.km.exchangediary.data.remote.request.CreateDiaryGroupRequestBody
import com.km.exchangediary.data.remote.response.CreateDiaryGroupResponse
import com.km.exchangediary.data.remote.response.DeleteDiaryGroupResponse
import com.km.exchangediary.data.remote.response.DiaryGroupListResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class  DiaryGroupRepository(private val dataSource: ExchangeDiaryDataSource, private val loginPreferences: LoginPreferences) : CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    suspend fun getDiaryGroupList(jwt: String): DiaryGroupListResponse =
        withContext(coroutineContext) {
            dataSource.getExchangeDiaryService().getDiaryGroupList(jwt)
        }

    fun getJWT(): String = loginPreferences.getUserToken() ?: "INVALID"

    suspend fun addDiaryGroup(jwt: String, diaryName: String): CreateDiaryGroupResponse =
        withContext(coroutineContext) {
            dataSource.getExchangeDiaryService().addDiaryGroup(jwt, CreateDiaryGroupRequestBody(diaryName))
        }

    suspend fun deleteDiaryGroup(jwt: String, diaryId: Long): DeleteDiaryGroupResponse {
        return withContext(coroutineContext) {
            dataSource.getExchangeDiaryService().deleteDiaryGroup(jwt, diaryId)
        }
    }
}