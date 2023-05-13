package com.papermoon.spaceapp.domain.usecase.event

import com.papermoon.spaceapp.domain.repository.EventRepository
import com.papermoon.spaceapp.domain.resource.Resource
import com.papermoon.spaceapp.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteEventsFromLocalUseCase(
    private val eventRepository: EventRepository
) : UseCase<Unit, Unit> {
    override suspend fun execute(params: Unit): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            eventRepository.deleteAllFromLocal()
        }
    }
}
