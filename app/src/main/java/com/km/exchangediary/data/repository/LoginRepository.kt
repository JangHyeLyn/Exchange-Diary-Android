package com.km.exchangediary.data.repository

import com.km.exchangediary.data.local.pref.LoginPreferences
import com.km.exchangediary.data.remote.datasource.LoginDataSource
import com.km.exchangediary.data.remote.request.LoginRequestBody
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginRepository(private val dataSource: LoginDataSource, private val localPreferences: LoginPreferences): CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    suspend fun getUserTokenAtRemote(accessToken: String): String? =
        withContext(coroutineContext) {
            dataSource.getLoginService().getJWT(LoginRequestBody(accessToken)).data?.token
        }

    fun getUserTokenAtLocal(): String? = localPreferences.getUserToken()

    fun writeUserToken(userToken: String) {
        localPreferences.setUserToken(userToken)
    }
}