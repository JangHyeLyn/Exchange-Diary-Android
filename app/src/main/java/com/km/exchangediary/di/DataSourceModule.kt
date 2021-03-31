package com.km.exchangediary.di

import com.km.exchangediary.data.remote.datasource.LoginDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { LoginDataSource(get()) }
}