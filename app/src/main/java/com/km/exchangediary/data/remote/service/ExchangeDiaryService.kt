package com.km.exchangediary.data.remote.service

import com.km.exchangediary.data.remote.request.LoginRequestBody
import com.km.exchangediary.data.remote.response.LoginResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ExchangeDiaryService {
    @POST("/rest-auth/kakao/")
    suspend fun getJWTToken(@Body loginRequestBody: LoginRequestBody): LoginResponseBody
}