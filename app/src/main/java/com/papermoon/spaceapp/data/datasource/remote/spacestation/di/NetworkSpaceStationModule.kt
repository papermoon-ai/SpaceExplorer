package com.papermoon.spaceapp.data.datasource.remote.spacestation.di

import com.papermoon.spaceapp.data.datasource.remote.spacestation.SpaceStationApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkSpaceStationModule = module {
    single { provideSpaceStationApiService(get()) }
}

fun provideSpaceStationApiService(retrofit: Retrofit): SpaceStationApiService =
    retrofit.create(SpaceStationApiService::class.java)
