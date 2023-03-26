package com.papermoon.spaceapp.data.datasource.di

import com.papermoon.spaceapp.data.AstronautRepositoryImpl
import com.papermoon.spaceapp.data.LaunchRepositoryImpl
import com.papermoon.spaceapp.data.datasource.remote.astronaut.AstronautApiService
import com.papermoon.spaceapp.data.datasource.remote.launches.LaunchApiService
import com.papermoon.spaceapp.domain.repository.AstronautRepository
import com.papermoon.spaceapp.domain.repository.LaunchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { provideLaunchRepository(get()) }
    single { provideAstronautRepository(get()) }
}

fun provideLaunchRepository(launchApiService: LaunchApiService): LaunchRepository {
    return LaunchRepositoryImpl(launchApiService)
}

fun provideAstronautRepository(astronautApiService: AstronautApiService): AstronautRepository {
    return AstronautRepositoryImpl(astronautApiService)
}