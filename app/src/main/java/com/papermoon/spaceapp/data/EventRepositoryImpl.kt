package com.papermoon.spaceapp.data

import com.papermoon.spaceapp.data.datasource.local.event.dao.EventDao
import com.papermoon.spaceapp.data.datasource.local.event.model.toDomainObject
import com.papermoon.spaceapp.data.datasource.remote.event.EventApiService
import com.papermoon.spaceapp.data.datasource.remote.event.model.toDomainObject
import com.papermoon.spaceapp.domain.model.event.Event
import com.papermoon.spaceapp.domain.model.event.toLocalObject
import com.papermoon.spaceapp.domain.repository.EventRepository
import com.papermoon.spaceapp.domain.resource.Resource

class EventRepositoryImpl(
    private val eventApiService: EventApiService,
    private val eventDao: EventDao
) : EventRepository {
    override suspend fun getUpcomingEventsFromNetwork(): Resource<List<Event>> {
        return try {
            val events = eventApiService.getUpcomingEvents().toDomainObject()
            Resource.Success(events)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getUpcomingEventsFromLocal(): Resource<List<Event>> {
        return try {
            val events = eventDao.getAll().toDomainObject()
            Resource.Success(events)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun saveUpcomingEventsToLocal(events: List<Event>): Resource<Unit> {
        return try {
            eventDao.insertAll(events.toLocalObject())
            Resource.Success(Unit)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun deleteAllFromLocal(): Resource<Unit> {
        return try {
            eventDao.deleteAll()
            Resource.Success(Unit)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}
