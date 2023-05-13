package com.papermoon.spaceapp.data.datasource.local.di

import android.content.Context
import androidx.room.Room
import com.papermoon.spaceapp.data.datasource.local.astronaut.di.astronautDaoModule
import com.papermoon.spaceapp.data.datasource.local.celestialBody.di.celestialBodyDaoModule
import com.papermoon.spaceapp.data.datasource.local.database.AppDatabase
import com.papermoon.spaceapp.data.datasource.local.database.AppDatabase.Companion.DATABASE_NAME
import com.papermoon.spaceapp.data.datasource.local.event.di.eventDaoModule
import com.papermoon.spaceapp.data.datasource.local.launch.di.launchDaoModule
import com.papermoon.spaceapp.data.datasource.local.spacestation.di.spaceStationDaoModule
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(get()) }
    includes(celestialBodyDaoModule, astronautDaoModule, launchDaoModule, spaceStationDaoModule, eventDaoModule)
}

fun provideDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
}
