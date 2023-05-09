package com.papermoon.spaceapp.domain.usecase.astronaut.di

import com.papermoon.spaceapp.domain.repository.AstronautRepository
import com.papermoon.spaceapp.domain.usecase.astronaut.GetAstronautsFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.astronaut.GetAstronautsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.astronaut.SaveAstronautsToLocalUseCase
import org.koin.dsl.module

val astronautUseCaseModule = module {
    single { provideGetAstronautsFromNetworkUseCase(get()) }
    single { provideGetAstronautsFromLocalUseCase(get()) }
    single { provideSaveAstronautsToLocalUseCase(get()) }
}

fun provideGetAstronautsFromNetworkUseCase(astronautRepository: AstronautRepository): GetAstronautsFromNetworkUseCase {
    return GetAstronautsFromNetworkUseCase(astronautRepository)
}

fun provideGetAstronautsFromLocalUseCase(astronautRepository: AstronautRepository): GetAstronautsFromLocalUseCase {
    return GetAstronautsFromLocalUseCase(astronautRepository)
}

fun provideSaveAstronautsToLocalUseCase(astronautRepository: AstronautRepository): SaveAstronautsToLocalUseCase {
    return SaveAstronautsToLocalUseCase(astronautRepository)
}
