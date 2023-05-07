package com.papermoon.spaceapp.data

import com.papermoon.spaceapp.data.datasource.remote.event.EventApiService
import com.papermoon.spaceapp.data.datasource.remote.event.model.toDomainObject
import com.papermoon.spaceapp.domain.model.event.Event
import com.papermoon.spaceapp.domain.repository.EventRepository
import com.papermoon.spaceapp.domain.resource.Resource

class EventRepositoryImpl(
    private val eventApiService: EventApiService
) : EventRepository {
    override suspend fun getUpcomingEvents(): Resource<List<Event>> {
        return try {
            val events = eventApiService.getUpcomingEvents().toDomainObject()
            Resource.Success(events)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}
