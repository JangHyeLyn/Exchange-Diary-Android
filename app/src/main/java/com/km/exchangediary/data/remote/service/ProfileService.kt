package com.km.exchangediary.data.remote.service

import com.km.exchangediary.data.entity.GitRepo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileService {
    @GET("users/{user}/repos")
    suspend fun gitRepoList(): List<GitRepo>

}