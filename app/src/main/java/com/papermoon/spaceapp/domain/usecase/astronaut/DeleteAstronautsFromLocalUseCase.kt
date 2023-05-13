package com.papermoon.spaceapp.domain.usecase.astronaut

import com.papermoon.spaceapp.domain.repository.AstronautRepository
import com.papermoon.spaceapp.domain.resource.Resource
import com.papermoon.spaceapp.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteAstronautsFromLocalUseCase(
    private val astronautRepository: AstronautRepository
) : UseCase<Unit, Unit> {
    override suspend fun execute(params: Unit): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            astronautRepository.deleteAllFromLocal()
        }
    }
}
