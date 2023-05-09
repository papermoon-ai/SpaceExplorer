package com.papermoon.spaceapp.domain.repository

import com.papermoon.spaceapp.domain.model.event.Event
import com.papermoon.spaceapp.domain.resource.Resource

interface EventRepository {
    suspend fun getUpcomingEventsFromNetwork(): Resource<List<Event>>
    suspend fun getUpcomingEventsFromLocal(): Resource<List<Event>>
    suspend fun saveUpcomingEventsToLocal(events: List<Event>): Resource<Unit>
}
