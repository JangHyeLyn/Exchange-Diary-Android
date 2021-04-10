package com.km.exchangediary.data.entity

import com.google.gson.annotations.SerializedName
import java.io.File

data class ProfileResult(
    val data: ProfileData
)

data class ProfileData(
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "username")
    val userName: String,
    @SerializedName(value = "description")
    val userIntroduction: String?,
    @SerializedName(value = "kakao_img")
    val kakaoImage: String?,
    @SerializedName(value = "profile_img")
    val profileImage: String?
)