package com.papermoon.spaceapp.domain.usecase.celestialBody

import com.papermoon.spaceapp.domain.repository.CelestialBodyRepository
import com.papermoon.spaceapp.domain.resource.Resource
import com.papermoon.spaceapp.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteCelestialBodiesFromLocalUseCase(
    private val celestialBodyRepository: CelestialBodyRepository
) : UseCase<Unit, Unit> {
    override suspend fun execute(params: Unit): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            celestialBodyRepository.deleteAllFromLocal()
        }
    }
}
