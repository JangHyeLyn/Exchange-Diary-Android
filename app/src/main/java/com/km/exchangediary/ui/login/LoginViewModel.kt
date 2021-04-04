package com.km.exchangediary.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.km.exchangediary.base.BaseViewModel
import com.km.exchangediary.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

class LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {
    private val _userTokenForLogin = MutableLiveData<String>()
    val userTokenForLogin: LiveData<String> = _userTokenForLogin

    fun getUserToken(accessToken: String) {
        launch {
            try {
                _userTokenForLogin.value = loginUseCase.getUserToken(accessToken)
            } catch (e: HttpException) {
                Timber.e(e.response().toString())
            }
        }
    }
}