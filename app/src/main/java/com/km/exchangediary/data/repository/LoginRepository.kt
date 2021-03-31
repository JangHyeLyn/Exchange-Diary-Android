package com.km.exchangediary.data.repository

import com.km.exchangediary.data.remote.datasource.LoginDataSource
import com.km.exchangediary.data.remote.request.LoginRequestBody
import com.km.exchangediary.data.remote.response.LoginResponseBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class LoginRepository(private val dataSource: LoginDataSource): CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    suspend fun getJWTToken(accessToken: String): LoginResponseBody =
        withContext(coroutineContext) {
            dataSource.getLoginService().getJWTToken(LoginRequestBody(accessToken))
        }
}