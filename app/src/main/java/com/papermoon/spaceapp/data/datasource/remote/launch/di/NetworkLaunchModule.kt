package com.papermoon.spaceapp.data.datasource.remote.launch.di

import com.papermoon.spaceapp.data.datasource.remote.launch.LaunchApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val networkLaunchModule = module {
    single { provideLaunchApi(get()) }
}

fun provideLaunchApi(retrofit: Retrofit): LaunchApiService =
    retrofit.create(LaunchApiService::class.java)
