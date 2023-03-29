package com.papermoon.spaceapp.domain.usecase.di

import com.papermoon.spaceapp.domain.repository.AstronautRepository
import com.papermoon.spaceapp.domain.repository.CelestialBodyRepository
import com.papermoon.spaceapp.domain.repository.LaunchRepository
import com.papermoon.spaceapp.domain.repository.SpaceStationRepository
import com.papermoon.spaceapp.domain.usecase.GetActiveSpaceStationsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.GetAstronautsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.GetPlanetsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.GetSpaceStationsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.GetUpcomingLaunchesFromNetworkUserCase
import org.koin.dsl.module

val useCaseModule = module {
    single { provideGetUpcomingLaunchesFromNetworkUseCase(get()) }
    single { provideGetAstronautsFromNetworkUseCase(get()) }
    single { provideGetSpaceStationsFromNetworkUseCase(get()) }
    single { provideGetActiveSpaceStationsFromNetworkUseCase(get()) }
    single { provideGetPlanetsFromNetworkUseCase(get()) }
}

fun provideGetUpcomingLaunchesFromNetworkUseCase(launchRepository: LaunchRepository): GetUpcomingLaunchesFromNetworkUserCase {
    return GetUpcomingLaunchesFromNetworkUserCase(launchRepository)
}

fun provideGetAstronautsFromNetworkUseCase(astronautRepository: AstronautRepository): GetAstronautsFromNetworkUseCase {
    return GetAstronautsFromNetworkUseCase(astronautRepository)
}

fun provideGetSpaceStationsFromNetworkUseCase(spaceStationRepository: SpaceStationRepository): GetSpaceStationsFromNetworkUseCase {
    return GetSpaceStationsFromNetworkUseCase(spaceStationRepository)
}

fun provideGetActiveSpaceStationsFromNetworkUseCase(spaceStationRepository: SpaceStationRepository): GetActiveSpaceStationsFromNetworkUseCase {
    return GetActiveSpaceStationsFromNetworkUseCase(spaceStationRepository)
}

fun provideGetPlanetsFromNetworkUseCase(celestialBodyRepository: CelestialBodyRepository): GetPlanetsFromNetworkUseCase {
    return GetPlanetsFromNetworkUseCase(celestialBodyRepository)
}
