package com.km.exchangediary.data.remote.service

import com.km.exchangediary.data.entity.ProfileResult
import retrofit2.Call
import retrofit2.http.GET

/* TODO 위치수정 */
const val BASE_URL = "http://ec2-13-209-49-204.ap-northeast-2.compute.amazonaws.com:8000/"

interface ProfileService {
    @GET("api/v1/users/hyelyn")
    fun getProfile(): Call<ProfileResult>
}