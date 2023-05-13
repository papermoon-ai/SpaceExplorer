package com.papermoon.spaceapp.domain.usecase.launch.di

import com.papermoon.spaceapp.domain.repository.LaunchRepository
import com.papermoon.spaceapp.domain.usecase.launch.DeleteLaunchesFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.launch.GetUpcomingLaunchesFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.launch.GetUpcomingLaunchesFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.launch.SaveUpcomingLaunchesToLocalUseCase
import org.koin.dsl.module

val launchUseCaseModule = module {
    single { provideGetUpcomingLaunchesFromNetworkUseCase(get()) }
    single { provideGetUpcomingLaunchesFromLocalUseCase(get()) }
    single { provideSaveUpcomingLaunchesToLocalUseCase(get()) }
    single { provideDeleteLaunchesFromLocalUseCase(get()) }
}

fun provideGetUpcomingLaunchesFromNetworkUseCase(repository: LaunchRepository): GetUpcomingLaunchesFromNetworkUseCase {
    return GetUpcomingLaunchesFromNetworkUseCase(repository)
}

fun provideGetUpcomingLaunchesFromLocalUseCase(repository: LaunchRepository): GetUpcomingLaunchesFromLocalUseCase {
    return GetUpcomingLaunchesFromLocalUseCase(repository)
}

fun provideSaveUpcomingLaunchesToLocalUseCase(repository: LaunchRepository): SaveUpcomingLaunchesToLocalUseCase {
    return SaveUpcomingLaunchesToLocalUseCase(repository)
}

fun provideDeleteLaunchesFromLocalUseCase(repository: LaunchRepository): DeleteLaunchesFromLocalUseCase {
    return DeleteLaunchesFromLocalUseCase(repository)
}
