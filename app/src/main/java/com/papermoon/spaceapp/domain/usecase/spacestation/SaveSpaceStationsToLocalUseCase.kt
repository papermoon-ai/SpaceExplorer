package com.papermoon.spaceapp.domain.usecase.spacestation

import com.papermoon.spaceapp.domain.model.spacestation.SpaceStation
import com.papermoon.spaceapp.domain.repository.SpaceStationRepository
import com.papermoon.spaceapp.domain.resource.Resource
import com.papermoon.spaceapp.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveSpaceStationsToLocalUseCase(
    private val spaceStationRepository: SpaceStationRepository
) : UseCase<Unit, List<SpaceStation>> {
    override suspend fun execute(params: List<SpaceStation>): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            spaceStationRepository.saveSpaceStationsToLocal(params)
        }
    }
}
