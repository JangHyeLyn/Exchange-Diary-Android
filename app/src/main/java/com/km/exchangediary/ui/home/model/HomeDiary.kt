package com.km.exchangediary.ui.home.model

data class HomeDiary(
    val title: String,
    val group: String?,
    val nowWriter: Long?,
    val numberOfPerson: Int,
    val totalPageCount: Int
)