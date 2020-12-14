package com.km.exchangediary.di

import com.km.exchangediary.data.remote.datasource.TestDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { TestDataSource(get()) }
}