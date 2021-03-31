package com.km.exchangediary.di

import com.km.exchangediary.data.repository.LoginRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { LoginRepository(get()) }
}