package com.papermoon.spaceapp.data.datasource.local.spacestation.di

import com.papermoon.spaceapp.data.datasource.local.database.AppDatabase
import com.papermoon.spaceapp.data.datasource.local.spacestation.dao.SpaceStationDao
import org.koin.dsl.module

val spaceStationDaoModule = module {
    single { provideSpaceStationDao(get()) }
}

fun provideSpaceStationDao(database: AppDatabase): SpaceStationDao {
    return database.spaceStationDao()
}
