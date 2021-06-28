package com.km.exchangediary.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
}

fun provideOkHttpClient() =
    OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build()

fun provideRetrofit(okHttpClient: OkHttpClient) =
    Retrofit.Builder()
        .baseUrl("http://13.209.49.204/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()