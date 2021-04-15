package com.km.exchangediary.data.remote.request

import com.google.gson.annotations.SerializedName

data class ProfileRequestBody(
    @SerializedName(value = "username")
    val userName: String,
    @SerializedName(value = "description")
    val userIntroduction: String?
)