package com.km.exchangediary.data.remote.response

data class LoginResponseBody(
    val code: Long?,
    val message: String?,
    val data: UserToken?
)

data class UserToken(
    val token: String?
)
