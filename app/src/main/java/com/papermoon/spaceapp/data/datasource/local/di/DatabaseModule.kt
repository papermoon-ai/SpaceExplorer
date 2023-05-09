package com.papermoon.spaceapp.data.datasource.local.di

import android.content.Context
import androidx.room.Room
import com.papermoon.spaceapp.data.datasource.local.celestialBody.di.celestialBodyDaoModule
import com.papermoon.spaceapp.data.datasource.local.db.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(get()) }
    includes(celestialBodyDaoModule)
}

fun provideDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "space-app-database").build()
}
