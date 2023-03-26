package com.papermoon.spaceapp.data.datasource.remote.astronaut.di

import com.papermoon.spaceapp.data.datasource.remote.astronaut.AstronautApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkAstronautModule = module {
    single { provideAstronautApiService(get()) }
}

fun provideAstronautApiService(retrofit: Retrofit): AstronautApiService =
    retrofit.create(AstronautApiService::class.java)