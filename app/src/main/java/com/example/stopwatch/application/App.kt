package com.example.stopwatch.application

import android.app.Application
import com.example.stopwatch.di.mainActivityViewModel
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(mainActivityViewModel)
        }
    }
}