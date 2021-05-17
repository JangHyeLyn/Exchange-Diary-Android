package com.km.exchangediary.data.remote.service

import com.km.exchangediary.data.remote.request.CreateDiaryGroupRequestBody
import com.km.exchangediary.data.remote.request.LoginRequestBody
import com.km.exchangediary.data.remote.response.CreateDiaryGroupResponse
import com.km.exchangediary.data.remote.response.DeleteDiaryGroupResponse
import com.km.exchangediary.data.remote.response.DiaryGroupListResponse
import com.km.exchangediary.data.remote.response.LoginResponseBody
import retrofit2.http.*

interface ExchangeDiaryService {
    @POST("/rest-auth/kakao/")
    suspend fun getJWT(@Body loginRequestBody: LoginRequestBody): LoginResponseBody

    @GET("/api/v1/diarygroups/")
    suspend fun getDiaryGroupList(@Header("Authorization") jwt: String): DiaryGroupListResponse

    @POST("/api/v1/diarygroups/")
    suspend fun addDiaryGroup(@Header("Authorization") jwt: String, @Body createDiaryGroupRequestBody: CreateDiaryGroupRequestBody): CreateDiaryGroupResponse

    @DELETE("/api/v1/diarygroups/{groupId}/")
    suspend fun deleteDiaryGroup(@Header("Authorization") jwt: String, @Path("groupId") groupId: Long): DeleteDiaryGroupResponse
}
