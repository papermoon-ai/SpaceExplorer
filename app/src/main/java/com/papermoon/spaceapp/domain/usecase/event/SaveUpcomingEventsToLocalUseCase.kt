package com.papermoon.spaceapp.domain.usecase.event

import com.papermoon.spaceapp.domain.model.event.Event
import com.papermoon.spaceapp.domain.repository.EventRepository
import com.papermoon.spaceapp.domain.resource.Resource
import com.papermoon.spaceapp.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveUpcomingEventsToLocalUseCase(
    private val eventRepository: EventRepository
) : UseCase<Unit, List<Event>> {
    override suspend fun execute(params: List<Event>): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            eventRepository.saveUpcomingEventsToLocal(params)
        }
    }
}
