package com.papermoon.spaceapp.data.datasource.remote.event.di

import com.papermoon.spaceapp.data.datasource.remote.event.EventApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkEventModule = module {
    single { provideEventApi(get()) }
}

fun provideEventApi(retrofit: Retrofit): EventApiService =
    retrofit.create(EventApiService::class.java)
