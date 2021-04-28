package com.km.exchangediary.data.remote.service

import com.km.exchangediary.data.remote.request.LoginRequestBody
import com.km.exchangediary.data.remote.response.DiaryGroupListResponse
import com.km.exchangediary.data.remote.response.LoginResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ExchangeDiaryService {
    @POST("/rest-auth/kakao/")
    suspend fun getJWT(@Body loginRequestBody: LoginRequestBody): LoginResponseBody

    @GET("/api/v1/diarygroups/")
    suspend fun getDiaryGroupList(@Header("Authorization") jwt: String): DiaryGroupListResponse
}