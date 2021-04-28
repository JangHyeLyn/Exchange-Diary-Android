package com.km.exchangediary.data.remote.response

import com.google.gson.annotations.SerializedName

data class DiaryGroupListResponse(
    val code: Int,
    val data: DiaryGroupResponseData,
    val message: String
)

data class DiaryGroupResponseInfo(
    val count: Int,
    @SerializedName("created_at")
    val createdAt: String,
    val id: Int,
    val rank: Int,
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class DiaryGroupResponseData(
    @SerializedName("diary_groups")
    val diaryGroups: List<DiaryGroupResponseInfo>,
    @SerializedName("not_join_group_cnt")
    val notJoinGroupCount: Int
)