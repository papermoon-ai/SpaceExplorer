package com.papermoon.spaceapp.domain.usecase.di

import com.papermoon.spaceapp.domain.repository.AstronautRepository
import com.papermoon.spaceapp.domain.repository.LaunchRepository
import com.papermoon.spaceapp.domain.usecase.GetAstronautsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.GetUpcomingLaunchesFromNetworkUserCase
import org.koin.dsl.module

val useCaseModule = module {
    single { provideGetUpcomingLaunchesFromNetworkUseCase(get()) }
    single { provideAstronautsFromNetworkUseCase(get()) }
}

fun provideGetUpcomingLaunchesFromNetworkUseCase(launchRepository: LaunchRepository): GetUpcomingLaunchesFromNetworkUserCase {
    return GetUpcomingLaunchesFromNetworkUserCase(launchRepository)
}

fun provideAstronautsFromNetworkUseCase(astronautRepository: AstronautRepository): GetAstronautsFromNetworkUseCase {
    return GetAstronautsFromNetworkUseCase(astronautRepository)
}
