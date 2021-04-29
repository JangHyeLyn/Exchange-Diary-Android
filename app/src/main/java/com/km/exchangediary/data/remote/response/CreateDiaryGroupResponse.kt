package com.km.exchangediary.data.remote.response

import com.google.gson.annotations.SerializedName

data class CreateDiaryGroupResponse(
    val code: Int,
    val data: CreateGroupData,
    val message: String
)

data class CreateGroupData(
    val id: Int,
    val title: String,
    @SerializedName("user")
    val userId: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("created_at")
    val createdAt: String
)