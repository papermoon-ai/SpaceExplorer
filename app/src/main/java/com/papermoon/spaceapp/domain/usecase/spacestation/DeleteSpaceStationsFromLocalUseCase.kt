package com.papermoon.spaceapp.domain.usecase.spacestation

import com.papermoon.spaceapp.domain.repository.SpaceStationRepository
import com.papermoon.spaceapp.domain.resource.Resource
import com.papermoon.spaceapp.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteSpaceStationsFromLocalUseCase(
    private val spaceStationRepository: SpaceStationRepository
) : UseCase<Unit, Unit> {
    override suspend fun execute(params: Unit): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            spaceStationRepository.deleteAllFromLocal()
        }
    }
}
