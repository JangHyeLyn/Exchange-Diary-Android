package com.km.exchangediary.di

import com.km.exchangediary.data.remote.datasource.ExchangeDiaryDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { ExchangeDiaryDataSource(get()) }
}