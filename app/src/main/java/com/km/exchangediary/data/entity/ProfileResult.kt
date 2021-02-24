package com.km.exchangediary.data.entity

import com.google.gson.annotations.SerializedName

data class ProfileResult(
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "username")
    val name: String,
    @SerializedName(value = "email")
    val email: String,
    @SerializedName(value = "description")
    val info: String?,
    @SerializedName(value = "kakao_img")
    val kakaoImg: String?,
    @SerializedName(value = "profile_img")
    val profileImg: String?
)