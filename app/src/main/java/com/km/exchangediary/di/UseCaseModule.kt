package com.km.exchangediary.di

import com.km.exchangediary.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    single { LoginUseCase(get()) }
    single { GetGroupListUseCase(get()) }
    single { AddDiaryGroupUseCase(get()) }
    single { DeleteDiaryGroupUseCase(get()) }
    single { ChangeDiaryGroupNameUseCase(get()) }
}