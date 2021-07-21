package com.km.exchangediary.ui.notification_page.model

data class NotificationList(
    val diaryTitle: String,
    val diaryGroup: String?,
    val message: String,
    val createdAt: Long
)
