package com.papermoon.spaceapp.data.datasource.local.astronaut.di

import com.papermoon.spaceapp.data.datasource.local.astronaut.dao.AstronautDao
import com.papermoon.spaceapp.data.datasource.local.db.AppDatabase
import org.koin.dsl.module

val astronautDaoModule = module {
    single { provideAstronautDao(get()) }
}

fun provideAstronautDao(database: AppDatabase): AstronautDao {
    return database.astronautDao()
}
