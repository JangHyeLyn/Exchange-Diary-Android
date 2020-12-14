package com.km.exchangediary.data.remote.datasource

import com.km.exchangediary.data.remote.service.TestService
import retrofit2.Retrofit

class TestDataSource(private val retrofit: Retrofit) {
    suspend fun getGitRepoList() =
            retrofit.create(TestService::class.java).gitRepoList()
}