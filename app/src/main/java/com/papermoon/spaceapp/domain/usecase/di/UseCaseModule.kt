package com.papermoon.spaceapp.domain.usecase.di

import com.papermoon.spaceapp.domain.repository.AstronautRepository
import com.papermoon.spaceapp.domain.repository.EventRepository
import com.papermoon.spaceapp.domain.repository.LaunchRepository
import com.papermoon.spaceapp.domain.repository.SpaceStationRepository
import com.papermoon.spaceapp.domain.usecase.astronaut.GetAstronautsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.celestialBody.di.celestialBodyUseCaseModule
import com.papermoon.spaceapp.domain.usecase.event.GetUpcomingEventsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.launch.GetUpcomingLaunchesFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.spacestation.GetActiveSpaceStationsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.spacestation.GetSpaceStationsFromNetworkUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { provideGetUpcomingLaunchesFromNetworkUseCase(get()) }
    single { provideGetAstronautsFromNetworkUseCase(get()) }
    single { provideGetSpaceStationsFromNetworkUseCase(get()) }
    single { provideGetActiveSpaceStationsFromNetworkUseCase(get()) }
    single { provideGetUpcomingEventsFromNetworkUseCase(get()) }
    includes(celestialBodyUseCaseModule)
}

fun provideGetUpcomingLaunchesFromNetworkUseCase(launchRepository: LaunchRepository): GetUpcomingLaunchesFromNetworkUseCase {
    return GetUpcomingLaunchesFromNetworkUseCase(launchRepository)
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

fun provideGetUpcomingEventsFromNetworkUseCase(eventRepository: EventRepository): GetUpcomingEventsFromNetworkUseCase {
    return GetUpcomingEventsFromNetworkUseCase(eventRepository)
}
