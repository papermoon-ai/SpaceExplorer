package com.papermoon.spaceapp

import android.app.Application
import com.papermoon.spaceapp.di.appModule
import com.github.terrakok.cicerone.Cicerone
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class SpaceApp : Application() {
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigationHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        startKoin {
            androidLogger()
            androidContext(this@SpaceApp)
            modules(appModule)
        }
    }

    companion object {
        internal lateinit var INSTANCE: SpaceApp
            private set
    }
}