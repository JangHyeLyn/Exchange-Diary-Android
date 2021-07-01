package com.km.exchangediary.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.km.exchangediary.R
import com.km.exchangediary.base.BaseFragment
import com.km.exchangediary.databinding.FragmentLoginBinding
import com.km.exchangediary.ui.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override fun layoutRes(): Int = R.layout.fragment_login
    private val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        bindingView()
    }

    private fun initViewModel() {
        viewModel.isLoginSuccessful.observe(viewLifecycleOwner, Observer { isLoginSuccessful ->
            if (isLoginSuccessful) {
                startActivity(Intent(activity, HomeActivity::class.java))
                activity?.finish()
            } else {
                Toast.makeText(this.context, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun bindingView() {
        binding.btnKakaoLogin.setOnClickListener {
            onClickLoginButton(it.context)
        }
    }

    /* activityContext 를 필요로 하기 때문에 viewModel 로 로직을 이동하지 않음 */
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