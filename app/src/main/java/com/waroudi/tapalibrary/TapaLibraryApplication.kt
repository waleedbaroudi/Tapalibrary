package com.waroudi.tapalibrary

import android.app.Application
import com.waroudi.tapalibrary.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TapaLibraryApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TapaLibraryApplication)
            modules(listOf(appModule))
        }
    }
}