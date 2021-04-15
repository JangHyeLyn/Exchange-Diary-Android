package com.km.exchangediary.data.remote.datasource

import com.km.exchangediary.data.remote.service.ExchangeDiaryService
import retrofit2.Retrofit

class ProfileDataSource(private val retrofit: Retrofit) {
    fun getProfileService() = retrofit.create(ExchangeDiaryService::class.java)
}