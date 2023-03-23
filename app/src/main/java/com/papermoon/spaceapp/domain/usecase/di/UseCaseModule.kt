package com.papermoon.spaceapp.domain.usecase.di

import com.papermoon.spaceapp.domain.repository.LaunchRepository
import com.papermoon.spaceapp.domain.usecase.GetUpcomingLaunchesFromNetworkUserCase
import org.koin.dsl.module

val useCaseModule = module {
    single { provideGetUpcomingLaunchesFromNetworkUseCase(get()) }
}

fun provideGetUpcomingLaunchesFromNetworkUseCase(launchRepository: LaunchRepository): GetUpcomingLaunchesFromNetworkUserCase {
    return GetUpcomingLaunchesFromNetworkUserCase(launchRepository)
}
