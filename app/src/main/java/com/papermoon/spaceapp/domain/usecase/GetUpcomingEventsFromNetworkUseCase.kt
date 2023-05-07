package com.papermoon.spaceapp.domain.usecase

import com.papermoon.spaceapp.domain.model.event.Event
import com.papermoon.spaceapp.domain.repository.EventRepository
import com.papermoon.spaceapp.domain.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUpcomingEventsFromNetworkUseCase(
    private val networkEventRepository: EventRepository
) : UseCase<List<Event>, Unit> {
    override suspend fun execute(params: Unit): Resource<List<Event>> {
        return withContext(Dispatchers.IO) {
            networkEventRepository.getUpcomingEvents()
        }
    }
}
