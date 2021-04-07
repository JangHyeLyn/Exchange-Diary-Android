package com.km.exchangediary.di

import com.km.exchangediary.data.local.pref.LoginPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single { LoginPreferences(androidContext()) }
}