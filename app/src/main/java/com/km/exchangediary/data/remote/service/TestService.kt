package com.km.exchangediary.data.remote.service

import com.km.exchangediary.data.entity.GitRepo
import retrofit2.http.GET

interface TestService {
    @GET("users/AnkyoungMoo/repos")
    suspend fun gitRepoList(): List<GitRepo>
}