package com.papermoon.spaceapp.domain.usecase.di

import com.papermoon.spaceapp.domain.repository.AstronautRepository
import com.papermoon.spaceapp.domain.repository.LaunchRepository
import com.papermoon.spaceapp.domain.repository.SpaceStationRepository
import com.papermoon.spaceapp.domain.usecase.GetActiveSpaceStationsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.GetAstronautsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.GetSpaceStationsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.GetUpcomingLaunchesFromNetworkUserCase
import org.koin.dsl.module

val useCaseModule = module {
    single { provideGetUpcomingLaunchesFromNetworkUseCase(get()) }
    single { provideAstronautsFromNetworkUseCase(get()) }
    single { provideSpaceStationsFromNetworkUseCase(get()) }
    single { provideActiveSpaceStationsFromNetworkUseCase(get()) }
}

fun provideGetUpcomingLaunchesFromNetworkUseCase(launchRepository: LaunchRepository): GetUpcomingLaunchesFromNetworkUserCase {
    return GetUpcomingLaunchesFromNetworkUserCase(launchRepository)
}

fun provideAstronautsFromNetworkUseCase(astronautRepository: AstronautRepository): GetAstronautsFromNetworkUseCase {
    return GetAstronautsFromNetworkUseCase(astronautRepository)
}

fun provideSpaceStationsFromNetworkUseCase(spaceStationRepository: SpaceStationRepository): GetSpaceStationsFromNetworkUseCase {
    return GetSpaceStationsFromNetworkUseCase(spaceStationRepository)
}

fun provideActiveSpaceStationsFromNetworkUseCase(spaceStationRepository: SpaceStationRepository): GetActiveSpaceStationsFromNetworkUseCase {
    return GetActiveSpaceStationsFromNetworkUseCase(spaceStationRepository)
}
