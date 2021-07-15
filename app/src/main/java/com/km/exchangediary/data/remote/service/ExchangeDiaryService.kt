package com.km.exchangediary.data.remote.service

import com.km.exchangediary.data.remote.request.ChangeDiaryGroupNameRequestBody
import com.km.exchangediary.data.remote.request.CreateDiaryGroupRequestBody
import com.km.exchangediary.data.remote.request.LoginRequestBody
import com.km.exchangediary.data.remote.request.ProfileRequestBody
import com.km.exchangediary.data.remote.request.ReorderDiaryGroupsRequestBody
import com.km.exchangediary.data.remote.response.CreateDiaryGroupResponse
import com.km.exchangediary.data.remote.response.DeleteDiaryGroupResponse
import com.km.exchangediary.data.remote.response.DiaryGroupListResponse
import com.km.exchangediary.data.remote.response.LoginResponseBody
import com.km.exchangediary.data.remote.response.ProfileResponseBody
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    /* 프로필 */
    @GET(" /api/v1/users/me/")
    fun getProfile(): Call<ProfileResponseBody>

    @Multipart
    @PATCH("/api/v1/users/me/")
    fun patchProfile(
        @Header("Authorization") jwt: String,
        @Part("username") userName: RequestBody,
        @Part("description") userIntroduction: RequestBody,
        @Part profileImage: MultipartBody.Part
    ): Call<ProfileResponseBody>

    @PATCH("/api/v1/users/me/")
    fun patchProfile(
        @Header("Authorization") jwt: String,
        @Body profileRequestBody: ProfileRequestBody
    ): Call<ProfileResponseBody>
}