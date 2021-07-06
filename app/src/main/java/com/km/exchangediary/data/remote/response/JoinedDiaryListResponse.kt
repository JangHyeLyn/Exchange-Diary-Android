package com.km.exchangediary.data.remote.response

import com.google.gson.annotations.SerializedName

data class JoinedDiaryListResponse(
    val code: Int,
    val data: List<JoinedDiaryListResponseData>,
    val message: String
)

data class JoinedDiaryListResponseData(
    val id: Long,
    val title: String,
    @SerializedName("now_page")
    val nowPage: Int,
    @SerializedName("total_page")
    val totalPage: Int,
    val user: Long,
    @SerializedName("now_writer")
    val nowWriter: Long,
    val cover: Int,
    val group: Int?,
    @SerializedName("create_at")
    val createdAt: Long,
    @SerializedName("updated_at")
    val updatedAt: Long
)