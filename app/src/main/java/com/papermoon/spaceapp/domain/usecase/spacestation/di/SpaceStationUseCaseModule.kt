package com.papermoon.spaceapp.domain.usecase.spacestation.di

import com.papermoon.spaceapp.domain.repository.SpaceStationRepository
import com.papermoon.spaceapp.domain.usecase.spacestation.GetSpaceStationsFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.spacestation.GetSpaceStationsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.spacestation.SaveSpaceStationsToLocalUseCase
import org.koin.dsl.module

val spaceStationUseCaseModule = module {
    single { provideGetSpaceStationsFromNetworkUseCase(get()) }
    single { provideGetSpaceStationsFromLocalUseCase(get()) }
    single { provideSaveSpaceStationsToLocalUseCase(get()) }
}

fun provideGetSpaceStationsFromNetworkUseCase(repository: SpaceStationRepository): GetSpaceStationsFromNetworkUseCase {
    return GetSpaceStationsFromNetworkUseCase(repository)
}

fun provideGetSpaceStationsFromLocalUseCase(repository: SpaceStationRepository): GetSpaceStationsFromLocalUseCase {
    return GetSpaceStationsFromLocalUseCase(repository)
}

fun provideSaveSpaceStationsToLocalUseCase(repository: SpaceStationRepository): SaveSpaceStationsToLocalUseCase {
    return SaveSpaceStationsToLocalUseCase(repository)
}
