package com.km.exchangediary.application

import android.app.Application
import com.km.exchangediary.BuildConfig
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
    }
}