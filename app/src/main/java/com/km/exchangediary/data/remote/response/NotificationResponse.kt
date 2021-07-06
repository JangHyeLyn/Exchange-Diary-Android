package com.km.exchangediary.data.remote.response

import com.google.gson.annotations.SerializedName

data class NotificationResponse(
    val code: Int,
    val message: String,
    val data: ArrayList<Notification>
)

data class Notification(
    val id: Int,
    @SerializedName("diary_id")
    val diaryId: Int,
    @SerializedName("diary_title")
    val diaryTitle: String,
    @SerializedName("diary_cover")
    val diaryCover: Int,
    @SerializedName("diary_status")
    val diaryStatus: Int,
    @SerializedName("diary_group")
    val diaryGroup: String?,
    val message: String,
    @SerializedName("created_at")
    val createdAt: Long
)