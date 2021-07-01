package com.km.exchangediary.data.remote.request

import com.google.gson.annotations.SerializedName

data class CreateDiaryGroupRequestBody(
    @SerializedName("title")
    val diaryName: String
)