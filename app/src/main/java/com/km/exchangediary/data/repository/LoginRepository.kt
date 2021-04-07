package com.km.exchangediary.data.repository

import com.km.exchangediary.data.local.pref.LoginPreferences
import com.km.exchangediary.data.remote.datasource.LoginDataSource
import com.km.exchangediary.data.remote.request.LoginRequestBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

class LoginRepository(private val dataSource: LoginDataSource, private val localPreferences: LoginPreferences): CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    suspend fun getUserTokenAtRemote(accessToken: String): String? =
        withContext(coroutineContext) {
            try {
                dataSource.getLoginService().getJWT(LoginRequestBody(accessToken)).data?.token
            } catch (e: HttpException) {
                e.printStackTrace()
                null
            }
        }

    fun writeUserToken(userToken: String) {
        localPreferences.setUserToken(userToken)
    }
}