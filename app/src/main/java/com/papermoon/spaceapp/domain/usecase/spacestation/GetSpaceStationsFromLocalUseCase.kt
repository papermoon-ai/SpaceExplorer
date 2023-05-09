package com.papermoon.spaceapp.domain.usecase.spacestation

import com.papermoon.spaceapp.domain.model.spacestation.SpaceStation
import com.papermoon.spaceapp.domain.repository.SpaceStationRepository
import com.papermoon.spaceapp.domain.resource.Resource
import com.papermoon.spaceapp.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSpaceStationsFromLocalUseCase(
    private val spaceStationRepository: SpaceStationRepository
) : UseCase<List<SpaceStation>, Unit> {
    override suspend fun execute(params: Unit): Resource<List<SpaceStation>> {
        return withContext(Dispatchers.IO) {
            spaceStationRepository.getSpaceStationsFromLocal()
        }
    }
}
