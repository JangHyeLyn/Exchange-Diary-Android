package com.km.exchangediary.ui.login

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun layoutRes(): Int = R.layout.activity_login
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.userTokenForLogin.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        binding.btnKakaoLogin.setOnClickListener {
            loginCallback(this)
        }
    }

    private fun loginCallback(context: Context) {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Timber.e("로그인 실패 $error")
            } else if (token != null) {
                viewModel.getUserToken(token.accessToken)
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }
}