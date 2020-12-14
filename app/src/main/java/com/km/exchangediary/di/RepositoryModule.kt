package com.km.exchangediary.di

import com.km.exchangediary.data.repository.TestRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { TestRepository(get()) }
}