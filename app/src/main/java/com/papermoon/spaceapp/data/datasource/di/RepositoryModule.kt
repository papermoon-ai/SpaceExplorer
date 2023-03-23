package com.papermoon.spaceapp.data.datasource.di

import com.papermoon.spaceapp.data.LaunchRepositoryImpl
import com.papermoon.spaceapp.data.datasource.remote.launches.LaunchApiService
import com.papermoon.spaceapp.domain.repository.LaunchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { provideLaunchRepository(get()) }
}

fun provideLaunchRepository(launchApiService: LaunchApiService): LaunchRepository {
    return LaunchRepositoryImpl(launchApiService)
}
