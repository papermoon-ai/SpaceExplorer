package com.papermoon.spaceapp.domain.usecase.event.di

import com.papermoon.spaceapp.domain.repository.EventRepository
import com.papermoon.spaceapp.domain.usecase.event.DeleteEventsFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.event.GetUpcomingEventsFromLocalUseCase
import com.papermoon.spaceapp.domain.usecase.event.GetUpcomingEventsFromNetworkUseCase
import com.papermoon.spaceapp.domain.usecase.event.SaveUpcomingEventsToLocalUseCase
import org.koin.dsl.module

val eventUseCaseModule = module {
    single { provideGetUpcomingEventsFromNetworkUseCase(get()) }
    single { provideGetUpcomingEventsFromLocalUseCase(get()) }
    single { provideSaveUpcomingEventsToLocalUseCase(get()) }
    single { provideDeleteEventsFromLocalUseCase(get()) }
}

fun provideGetUpcomingEventsFromNetworkUseCase(repository: EventRepository): GetUpcomingEventsFromNetworkUseCase {
    return GetUpcomingEventsFromNetworkUseCase(repository)
}

fun provideGetUpcomingEventsFromLocalUseCase(repository: EventRepository): GetUpcomingEventsFromLocalUseCase {
    return GetUpcomingEventsFromLocalUseCase(repository)
}

fun provideSaveUpcomingEventsToLocalUseCase(repository: EventRepository): SaveUpcomingEventsToLocalUseCase {
    return SaveUpcomingEventsToLocalUseCase(repository)
}

fun provideDeleteEventsFromLocalUseCase(repository: EventRepository): DeleteEventsFromLocalUseCase {
    return DeleteEventsFromLocalUseCase(repository)
}
