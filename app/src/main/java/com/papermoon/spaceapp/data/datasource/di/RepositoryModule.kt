package com.papermoon.spaceapp.data.datasource.di

import com.papermoon.spaceapp.data.AstronautRepositoryImpl
import com.papermoon.spaceapp.data.CelestialBodyRepositoryImpl
import com.papermoon.spaceapp.data.EventRepositoryImpl
import com.papermoon.spaceapp.data.LaunchRepositoryImpl
import com.papermoon.spaceapp.data.SpaceStationRepositoryImpl
import com.papermoon.spaceapp.data.datasource.remote.astronaut.AstronautApiService
import com.papermoon.spaceapp.data.datasource.remote.celestialBody.CelestialBodyApiService
import com.papermoon.spaceapp.data.datasource.remote.event.EventApiService
import com.papermoon.spaceapp.data.datasource.remote.launch.LaunchApiService
import com.papermoon.spaceapp.data.datasource.remote.spacestation.SpaceStationApiService
import com.papermoon.spaceapp.domain.repository.AstronautRepository
import com.papermoon.spaceapp.domain.repository.CelestialBodyRepository
import com.papermoon.spaceapp.domain.repository.EventRepository
import com.papermoon.spaceapp.domain.repository.LaunchRepository
import com.papermoon.spaceapp.domain.repository.SpaceStationRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { provideLaunchRepository(get()) }
    single { provideAstronautRepository(get()) }
    single { provideSpaceStationRepository(get()) }
    single { provideCelestialBodyRepository(get()) }
    single { provideEventRepository(get()) }
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

fun provideCelestialBodyRepository(celestialBodyApiService: CelestialBodyApiService): CelestialBodyRepository {
    return CelestialBodyRepositoryImpl(celestialBodyApiService)
}

fun provideEventRepository(eventApiService: EventApiService): EventRepository {
    return EventRepositoryImpl(eventApiService)
}
