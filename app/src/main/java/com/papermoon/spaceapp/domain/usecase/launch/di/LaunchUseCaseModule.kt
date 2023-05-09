package com.papermoon.spaceapp.domain.usecase.launch.di

import com.papermoon.spaceapp.domain.repository.LaunchRepository
import com.papermoon.spaceapp.domain.usecase.launch.GetUpcomingLaunchesFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.launch.GetUpcomingLaunchesFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.launch.SaveUpcomingLaunchesToLocalUseCase
import org.koin.dsl.module

val launchUseCaseModule = module {
    single { provideGetUpcomingLaunchesFromNetworkUseCase(get()) }
    single { provideGetUpcomingLaunchesFromLocalUseCase(get()) }
    single { provideSaveUpcomingLaunchesToLocalUseCase(get()) }
}

fun provideGetUpcomingLaunchesFromNetworkUseCase(launchRepository: LaunchRepository): GetUpcomingLaunchesFromNetworkUseCase {
    return GetUpcomingLaunchesFromNetworkUseCase(launchRepository)
}

fun provideGetUpcomingLaunchesFromLocalUseCase(launchRepository: LaunchRepository): GetUpcomingLaunchesFromLocalUseCase {
    return GetUpcomingLaunchesFromLocalUseCase(launchRepository)
}

fun provideSaveUpcomingLaunchesToLocalUseCase(launchRepository: LaunchRepository): SaveUpcomingLaunchesToLocalUseCase {
    return SaveUpcomingLaunchesToLocalUseCase(launchRepository)
}
