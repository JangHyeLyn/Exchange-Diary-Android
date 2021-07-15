package com.km.exchangediary.di

import com.km.exchangediary.data.repository.DiaryGroupRepository
import com.km.exchangediary.data.repository.LoginRepository
import com.km.exchangediary.data.repository.ProfileRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { LoginRepository(get(), get()) }
    single { DiaryGroupRepository(get(), get()) }
    single { ProfileRepository(get(), get()) }
}