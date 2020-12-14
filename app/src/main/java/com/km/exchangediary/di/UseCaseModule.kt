package com.km.exchangediary.di

import com.km.exchangediary.domain.usecase.TestUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { TestUseCase(get()) }
}