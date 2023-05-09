package com.papermoon.spaceapp.domain.usecase.di

import com.papermoon.spaceapp.domain.repository.EventRepository
import com.papermoon.spaceapp.domain.repository.LaunchRepository
import com.papermoon.spaceapp.domain.repository.SpaceStationRepository
import com.papermoon.spaceapp.domain.usecase.astronaut.di.astronautUseCaseModule
import com.papermoon.spaceapp.domain.usecase.celestialBody.di.celestialBodyUseCaseModule
import com.papermoon.spaceapp.domain.usecase.event.GetUpcomingEventsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.launch.GetUpcomingLaunchesFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.spacestation.GetActiveSpaceStationsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.spacestation.GetSpaceStationsFromNetworkUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { provideGetUpcomingLaunchesFromNetworkUseCase(get()) }
    single { provideGetSpaceStationsFromNetworkUseCase(get()) }
    single { provideGetActiveSpaceStationsFromNetworkUseCase(get()) }
    single { provideGetUpcomingEventsFromNetworkUseCase(get()) }
    includes(celestialBodyUseCaseModule, astronautUseCaseModule)
}

fun provideGetUpcomingLaunchesFromNetworkUseCase(launchRepository: LaunchRepository): GetUpcomingLaunchesFromNetworkUseCase {
    return GetUpcomingLaunchesFromNetworkUseCase(launchRepository)
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
