package com.km.exchangediary.ui.group_management.model

data class DiaryGroup(
    val id: Long,
    var rank: Int,
    val title: String,
    val numberOfPeople: Int
)