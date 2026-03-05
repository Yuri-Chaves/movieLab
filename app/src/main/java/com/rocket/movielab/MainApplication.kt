package com.rocket.movielab

import android.app.Application
import com.rocket.movielab.di.AppModules.dataModule
import com.rocket.movielab.di.AppModules.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                uiModule,
                dataModule
            )
        }
    }
}