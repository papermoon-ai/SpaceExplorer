package com.papermoon.spaceapp.data.datasource.local.celestialBody.di

import com.papermoon.spaceapp.data.datasource.local.celestialBody.dao.CelestialBodyDao
import com.papermoon.spaceapp.data.datasource.local.database.AppDatabase
import org.koin.dsl.module

val celestialBodyDaoModule = module {
    single { provideCelestialBodyDao(get()) }
}

fun provideCelestialBodyDao(database: AppDatabase): CelestialBodyDao {
    return database.celestialBodyDao()
}
