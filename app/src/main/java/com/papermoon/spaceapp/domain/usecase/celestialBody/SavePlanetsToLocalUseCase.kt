package com.papermoon.spaceapp.domain.usecase.celestialBody

import com.papermoon.spaceapp.domain.model.celestialbody.CelestialBody
import com.papermoon.spaceapp.domain.repository.CelestialBodyRepository
import com.papermoon.spaceapp.domain.resource.Resource
import com.papermoon.spaceapp.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SavePlanetsToLocalUseCase(
    private val celestialBodyRepository: CelestialBodyRepository
) : UseCase<Unit, List<CelestialBody>> {
    override suspend fun execute(params: List<CelestialBody>): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            celestialBodyRepository.savePlanetsToLocal(params)
        }
    }
}
