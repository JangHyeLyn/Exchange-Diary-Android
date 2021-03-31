package com.km.exchangediary.data.remote.request

import com.google.gson.annotations.SerializedName

data class LoginRequestBody(
    @SerializedName("access_token")
    val accessToken: String
)