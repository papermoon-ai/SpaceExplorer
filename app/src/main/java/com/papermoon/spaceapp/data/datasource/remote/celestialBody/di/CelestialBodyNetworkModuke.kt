package com.papermoon.spaceapp.data.datasource.remote.celestialBody.di

import com.papermoon.spaceapp.data.datasource.remote.celestialBody.CelestialBodyApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkCelestialBodyModule = module {
    single { provideCelestialBodyApiService(get()) }
}

fun provideCelestialBodyApiService(retrofit: Retrofit): CelestialBodyApiService =
    retrofit.create(CelestialBodyApiService::class.java)
