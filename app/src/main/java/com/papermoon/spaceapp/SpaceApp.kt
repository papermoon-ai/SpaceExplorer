package com.papermoon.spaceapp

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.papermoon.spaceapp.data.di.repositoryModule
import com.papermoon.spaceapp.data.datasource.local.di.databaseModule
import com.papermoon.spaceapp.data.datasource.remote.di.networkModule
import com.papermoon.spaceapp.di.appModule
import com.papermoon.spaceapp.domain.usecase.di.useCaseModule
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
            modules(
                appModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                databaseModule
            )
        }
    }

    companion object {
        internal lateinit var INSTANCE: SpaceApp
            private set
    }
}
