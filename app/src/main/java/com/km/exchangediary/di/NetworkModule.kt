package com.km.exchangediary.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build()
    }

    single {
        Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(get())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}