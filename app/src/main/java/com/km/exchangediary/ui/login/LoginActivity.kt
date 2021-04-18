package com.km.exchangediary.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseActivity
import com.km.exchangediary.databinding.ActivityLoginBinding
import com.km.exchangediary.ui.home.HomeActivity
import com.km.exchangediary.ui.login.adapter.OnBoardingViewPagerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun layoutRes(): Int = R.layout.activity_login
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        bindingView()
    }

    private fun initViewModel() {
        viewModel.isLoginSuccessful.observe(this, Observer { isLoginSuccessful ->
            if (isLoginSuccessful) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun bindingView() {
        binding.vpOnBoarding.apply {
            adapter = OnBoardingViewPagerAdapter(supportFragmentManager, lifecycle)
//            isUserInputEnabled = false
        }

        binding.btnKakaoLogin.setOnClickListener {
            onClickLoginButton(this)
        }
    }

    /* activityContext 를 필요로 하기 때문에 viewModel 로 로직을 이동하지 않고 activity 에서 실행 */
    private fun onClickLoginButton(context: Context) {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            viewModel.loginExchangeDiary(token?.accessToken ?: "INVALID")
            if (error != null) {
                Timber.e("카카오 로그인 실패 $error")
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }
}