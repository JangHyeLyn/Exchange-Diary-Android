package com.km.exchangediary.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.km.exchangediary.base.BaseViewModel
import com.km.exchangediary.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase) : BaseViewModel() {
    private val _isLoginSuccessful = MutableLiveData<Boolean>()
    val isLoginSuccessful: LiveData<Boolean> = _isLoginSuccessful

    fun loginExchangeDiary(accessToken: String) {
        launch {
            _isLoginSuccessful.value = loginUseCase.isLoginSuccessful(accessToken)
        }
    }
}