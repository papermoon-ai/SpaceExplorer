package com.papermoon.spaceapp.data.datasource.local.event.di

import com.papermoon.spaceapp.data.datasource.local.database.AppDatabase
import com.papermoon.spaceapp.data.datasource.local.event.dao.EventDao
import org.koin.dsl.module

val eventDaoModule = module {
    single { provideEventDao(get()) }
}

fun provideEventDao(database: AppDatabase): EventDao {
    return database.eventDao()
}
