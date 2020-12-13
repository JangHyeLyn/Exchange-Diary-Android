package com.km.exchangediary.application

import android.app.Application
import com.km.exchangediary.BuildConfig
import com.km.exchangediary.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    companion object {
        lateinit var app: App
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(app)
            modules(listOf(viewModelModule))
        }
    }
}