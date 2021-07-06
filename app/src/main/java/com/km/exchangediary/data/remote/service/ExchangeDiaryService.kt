package com.km.exchangediary.data.remote.service

import com.km.exchangediary.data.remote.request.ChangeDiaryGroupNameRequestBody
import com.km.exchangediary.data.remote.request.CreateDiaryGroupRequestBody
import com.km.exchangediary.data.remote.request.LoginRequestBody
import com.km.exchangediary.data.remote.request.ReorderDiaryGroupsRequestBody
import com.km.exchangediary.data.remote.response.*
import retrofit2.Call
import retrofit2.http.*

interface ExchangeDiaryService {
    @POST("/rest-auth/kakao/")
    suspend fun getJWT(@Body loginRequestBody: LoginRequestBody): LoginResponseBody

    @GET("/api/v1/diarygroups/")
    suspend fun getDiaryGroupList(@Header("Authorization") jwt: String): DiaryGroupListResponse

    @POST("/api/v1/diarygroups/")
    suspend fun addDiaryGroup(
        @Header("Authorization") jwt: String,
        @Body createDiaryGroupRequestBody: CreateDiaryGroupRequestBody
    ): CreateDiaryGroupResponse

    @DELETE("/api/v1/diarygroups/{groupId}/")
    suspend fun deleteDiaryGroup(
        @Header("Authorization") jwt: String,
        @Path("groupId") groupId: Long
    ): DeleteDiaryGroupResponse

    @PATCH("/api/v1/diarygroups/{groupId}/")
    suspend fun changeDiaryGroupName(
        @Header("Authorization") jwt: String,
        @Path("groupId") groupId: Long,
        @Body changeDiaryGroupNameRequestBody: ChangeDiaryGroupNameRequestBody
    ): Any

    @PATCH("/api/v1/diarygroups/")
    suspend fun reorderDiaryGroups(
        @Header("Authorization") jwt: String,
        @Body reorderDiaryGroupsRequestBody: List<ReorderDiaryGroupsRequestBody>
    )

    @GET("/api/v1/notifications/")
    fun getNotifications(
        @Header("Authorization") jwt: String
    ) : Call<NotificationResponse>
}
