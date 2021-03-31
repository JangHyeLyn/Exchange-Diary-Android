package com.km.exchangediary.di

import com.km.exchangediary.domain.usecase.LoginUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { LoginUseCase(get()) }
}