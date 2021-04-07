package com.km.exchangediary.data.remote.datasource

import com.km.exchangediary.data.remote.service.ExchangeDiaryService
import retrofit2.Retrofit

class LoginDataSource(private val retrofit: Retrofit) {
    fun getLoginService() = retrofit.create(ExchangeDiaryService::class.java)
}