package com.km.exchangediary.data.remote.datasource

import com.km.exchangediary.data.remote.service.ExchangeDiaryService
import retrofit2.Retrofit

class ExchangeDiaryDataSource(private val retrofit: Retrofit) {
    fun getExchangeDiaryService() = retrofit.create(ExchangeDiaryService::class.java)
}