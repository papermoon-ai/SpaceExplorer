package com.papermoon.spaceapp.domain.usecase

import com.papermoon.spaceapp.domain.model.Astronaut
import com.papermoon.spaceapp.domain.repository.AstronautRepository
import com.papermoon.spaceapp.domain.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAstronautsFromNetworkUseCase(
    private val astronautRepository: AstronautRepository
) : UseCase<List<Astronaut>, Unit> {
    override suspend fun execute(params: Unit): Resource<List<Astronaut>> {
        return withContext(Dispatchers.IO) {
            astronautRepository.getAstronautsFromNetwork()
        }
    }
}