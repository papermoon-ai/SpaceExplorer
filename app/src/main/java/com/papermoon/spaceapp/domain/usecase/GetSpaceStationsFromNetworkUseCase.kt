package com.papermoon.spaceapp.domain.usecase

import com.papermoon.spaceapp.domain.model.spacestation.SpaceStation
import com.papermoon.spaceapp.domain.repository.SpaceStationRepository
import com.papermoon.spaceapp.domain.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSpaceStationsFromNetworkUseCase(
    private val spaceStationRepository: SpaceStationRepository
) : UseCase<List<SpaceStation>, Unit> {
    override suspend fun execute(params: Unit): Resource<List<SpaceStation>> {
        return withContext(Dispatchers.IO) {
            spaceStationRepository.getSpaceStationsFromNetwork()
        }
    }
}
