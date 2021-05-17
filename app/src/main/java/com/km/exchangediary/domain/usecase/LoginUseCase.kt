package com.km.exchangediary.domain.usecase

import com.km.exchangediary.data.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class LoginUseCase(private val repository: LoginRepository) : CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    suspend fun isLoginSuccessful(accessToken: String): Boolean {
        return withContext(coroutineContext) {
            val userTokenFromRemote = repository.getUserTokenAtRemote(accessToken)
            userTokenFromRemote?.let {
                repository.writeUserToken(it)
            }

            userTokenFromRemote != null
        }
    }
}