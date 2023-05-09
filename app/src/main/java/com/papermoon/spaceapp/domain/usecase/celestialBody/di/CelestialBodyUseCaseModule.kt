package com.papermoon.spaceapp.domain.usecase.celestialBody.di

import com.papermoon.spaceapp.domain.repository.CelestialBodyRepository
import com.papermoon.spaceapp.domain.usecase.celestialBody.GetPlanetsFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.celestialBody.GetPlanetsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.celestialBody.SavePlanetsToLocalUseCase
import org.koin.dsl.module

val celestialBodyUseCaseModule = module {
    single { provideGetPlanetsFromNetworkUseCase(get()) }
    single { provideGetPlanetsFromLocalUseCase(get()) }
    single { provideSavePlanetsToLocalUseCase(get()) }
}

fun provideGetPlanetsFromNetworkUseCase(repository: CelestialBodyRepository): GetPlanetsFromNetworkUseCase {
    return GetPlanetsFromNetworkUseCase(repository)
}

fun provideGetPlanetsFromLocalUseCase(repository: CelestialBodyRepository): GetPlanetsFromLocalUseCase {
    return GetPlanetsFromLocalUseCase(repository)
}

fun provideSavePlanetsToLocalUseCase(repository: CelestialBodyRepository): SavePlanetsToLocalUseCase {
    return SavePlanetsToLocalUseCase(repository)
}
