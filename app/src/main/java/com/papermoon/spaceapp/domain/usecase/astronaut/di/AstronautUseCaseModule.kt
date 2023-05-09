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

fun provideGetAstronautsFromNetworkUseCase(repository: AstronautRepository): GetAstronautsFromNetworkUseCase {
    return GetAstronautsFromNetworkUseCase(repository)
}

fun provideGetAstronautsFromLocalUseCase(repository: AstronautRepository): GetAstronautsFromLocalUseCase {
    return GetAstronautsFromLocalUseCase(repository)
}

fun provideSaveAstronautsToLocalUseCase(repository: AstronautRepository): SaveAstronautsToLocalUseCase {
    return SaveAstronautsToLocalUseCase(repository)
}
