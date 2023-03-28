package com.papermoon.spaceapp.data.datasource.di

import com.papermoon.spaceapp.data.AstronautRepositoryImpl
import com.papermoon.spaceapp.data.LaunchRepositoryImpl
import com.papermoon.spaceapp.data.SpaceStationRepositoryImpl
import com.papermoon.spaceapp.data.datasource.remote.astronaut.AstronautApiService
import com.papermoon.spaceapp.data.datasource.remote.launches.LaunchApiService
import com.papermoon.spaceapp.data.datasource.remote.spacestation.SpaceStationApiService
import com.papermoon.spaceapp.domain.repository.AstronautRepository
import com.papermoon.spaceapp.domain.repository.LaunchRepository
import com.papermoon.spaceapp.domain.repository.SpaceStationRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { provideLaunchRepository(get()) }
    single { provideAstronautRepository(get()) }
    single { provideSpaceStationRepository(get()) }
}

fun provideLaunchRepository(launchApiService: LaunchApiService): LaunchRepository {
    return LaunchRepositoryImpl(launchApiService)
}

fun provideAstronautRepository(astronautApiService: AstronautApiService): AstronautRepository {
    return AstronautRepositoryImpl(astronautApiService)
}

fun provideSpaceStationRepository(spaceStationApiService: SpaceStationApiService): SpaceStationRepository {
    return SpaceStationRepositoryImpl(spaceStationApiService)
}
