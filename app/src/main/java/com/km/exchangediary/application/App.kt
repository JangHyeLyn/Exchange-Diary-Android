package com.km.exchangediary.application

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.km.exchangediary.BuildConfig
import com.km.exchangediary.R
import com.km.exchangediary.di.*
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
            modules(listOf(
                viewModelModule,
                repositoryModule,
                networkModule,
                dataSourceModule,
                useCaseModule
            ))
        }

        KakaoSdk.init(this, getString(R.string.kakao_app_key))
    }
}