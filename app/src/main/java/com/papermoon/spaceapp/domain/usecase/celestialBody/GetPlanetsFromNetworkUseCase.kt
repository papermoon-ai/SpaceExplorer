package com.papermoon.spaceapp.domain.usecase.celestialBody

import com.papermoon.spaceapp.domain.model.celestialbody.CelestialBody
import com.papermoon.spaceapp.domain.repository.CelestialBodyRepository
import com.papermoon.spaceapp.domain.resource.Resource
import com.papermoon.spaceapp.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPlanetsFromNetworkUseCase(
    private val celestialBodyRepository: CelestialBodyRepository
) : UseCase<List<CelestialBody>, Unit> {
    override suspend fun execute(params: Unit): Resource<List<CelestialBody>> {
        return withContext(Dispatchers.IO) {
            celestialBodyRepository.getPlanetsFromNetwork()
        }
    }
}
