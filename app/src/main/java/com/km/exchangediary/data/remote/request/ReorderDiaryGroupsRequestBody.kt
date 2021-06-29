package com.km.exchangediary.data.remote.request

data class ReorderDiaryGroupsRequestBody(
    val id: Long,
    val rank: Int
)