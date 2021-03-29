package com.km.exchangediary.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun layoutRes(): Int = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvKakaoLogin.setOnClickListener {
            loginCallback(this)
        }
    }

    private fun loginCallback(context: Context) {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("kakao_login_result", "로그인 실패 ", error)
            } else if (token != null) {
                Log.i("kakao_login_result", "로그인 성공 ${token.accessToken}")
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }
}