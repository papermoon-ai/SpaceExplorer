package com.papermoon.spaceapp.domain.usecase.di

import com.papermoon.spaceapp.domain.usecase.astronaut.di.astronautUseCaseModule
import com.papermoon.spaceapp.domain.usecase.celestialBody.di.celestialBodyUseCaseModule
import com.papermoon.spaceapp.domain.usecase.event.di.eventUseCaseModule
import com.papermoon.spaceapp.domain.usecase.launch.di.launchUseCaseModule
import com.papermoon.spaceapp.domain.usecase.spacestation.di.spaceStationUseCaseModule
import org.koin.dsl.module

val useCaseModule = module {
    includes(
        celestialBodyUseCaseModule,
        astronautUseCaseModule,
        launchUseCaseModule,
        spaceStationUseCaseModule,
        eventUseCaseModule
    )
}
