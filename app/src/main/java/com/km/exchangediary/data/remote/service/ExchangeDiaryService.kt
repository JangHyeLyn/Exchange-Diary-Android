package com.km.exchangediary.data.remote.service

import com.km.exchangediary.data.remote.request.LoginRequestBody
import com.km.exchangediary.data.remote.request.ProfileRequestBody
import com.km.exchangediary.data.remote.response.LoginResponseBody
import com.km.exchangediary.data.remote.response.ProfileResponseBody
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface ExchangeDiaryService {
    @POST("/rest-auth/kakao/")
    suspend fun getJWT(@Body loginRequestBody: LoginRequestBody): LoginResponseBody

    /* 프로필 */
    @GET("/api/v1/users/hyelyn/")
    fun getProfile(): Call<ProfileResponseBody>

    @Multipart
    @PATCH("/api/v1/users/hyelyn/")
    fun patchProfile(
        @Part("username") userName: RequestBody,
        @Part("description") userIntroduction: RequestBody,
        @Part profileImage: MultipartBody.Part
    ): Call<ProfileResponseBody>

    @PATCH("/api/v1/users/hyelyn/")
    fun patchProfile(
        @Body profileRequestBody: ProfileRequestBody
    ): Call<ProfileResponseBody>
}