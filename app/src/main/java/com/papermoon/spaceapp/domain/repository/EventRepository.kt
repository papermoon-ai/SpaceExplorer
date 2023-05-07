package com.papermoon.spaceapp.domain.repository

import com.papermoon.spaceapp.domain.model.event.Event
import com.papermoon.spaceapp.domain.resource.Resource

interface EventRepository {
    suspend fun getUpcomingEvents(): Resource<List<Event>>
}
