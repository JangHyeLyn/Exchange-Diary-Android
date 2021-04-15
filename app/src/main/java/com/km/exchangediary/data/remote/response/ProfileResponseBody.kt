package com.km.exchangediary.data.remote.response

import com.google.gson.annotations.SerializedName

data class ProfileResponseBody(
    val data: ProfileData
)

data class ProfileData(
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "username")
    var userName: String,
    @SerializedName(value = "description")
    var userIntroduction: String?,
    @SerializedName(value = "profile_img")
    var profileImage: String?
)