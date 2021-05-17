package com.km.exchangediary.di

import com.km.exchangediary.domain.usecase.AddDiaryGroupUseCase
import com.km.exchangediary.domain.usecase.DeleteDiaryGroupUseCase
import com.km.exchangediary.domain.usecase.GetGroupListUseCase
import com.km.exchangediary.domain.usecase.LoginUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { LoginUseCase(get()) }
    single { GetGroupListUseCase(get()) }
    single { AddDiaryGroupUseCase(get()) }
    single { DeleteDiaryGroupUseCase(get()) }
}