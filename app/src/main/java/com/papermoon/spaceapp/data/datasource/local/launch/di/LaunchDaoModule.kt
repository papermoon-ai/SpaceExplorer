package com.papermoon.spaceapp.data.datasource.local.launch.di

import com.papermoon.spaceapp.data.datasource.local.db.AppDatabase
import com.papermoon.spaceapp.data.datasource.local.launch.dao.LaunchDao
import org.koin.dsl.module

val launchDaoModule = module {
    single { provideLaunchDao(get()) }
}

fun provideLaunchDao(database: AppDatabase): LaunchDao {
    return database.launchDao()
}
