package com.papermoon.spaceapp.domain.usecase.event

import com.papermoon.spaceapp.domain.model.event.Event
import com.papermoon.spaceapp.domain.repository.EventRepository
import com.papermoon.spaceapp.domain.resource.Resource
import com.papermoon.spaceapp.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUpcomingEventsFromNetworkUseCase(
    private val eventRepository: EventRepository
) : UseCase<List<Event>, Unit> {
    override suspend fun execute(params: Unit): Resource<List<Event>> {
        return withContext(Dispatchers.IO) {
            eventRepository.getUpcomingEventsFromNetwork()
        }
    }
}
