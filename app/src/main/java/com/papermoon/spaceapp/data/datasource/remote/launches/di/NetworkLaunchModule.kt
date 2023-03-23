package com.papermoon.spaceapp.data.datasource.remote.launches.di

import com.papermoon.spaceapp.data.datasource.remote.launches.LaunchApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkLaunchModule = module {
    single { provideLaunchApi(get()) }
}

fun provideLaunchApi(retrofit: Retrofit): LaunchApiService = retrofit.create(LaunchApiService::class.java)
