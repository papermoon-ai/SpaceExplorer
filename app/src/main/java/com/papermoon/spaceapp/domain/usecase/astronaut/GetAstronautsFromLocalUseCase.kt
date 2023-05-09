package com.papermoon.spaceapp.domain.usecase.astronaut

import com.papermoon.spaceapp.domain.model.astronaut.Astronaut
import com.papermoon.spaceapp.domain.repository.AstronautRepository
import com.papermoon.spaceapp.domain.resource.Resource
import com.papermoon.spaceapp.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAstronautsFromLocalUseCase(
    private val astronautRepository: AstronautRepository
) : UseCase<List<Astronaut>, Unit> {
    override suspend fun execute(params: Unit): Resource<List<Astronaut>> {
        return withContext(Dispatchers.IO) {
            astronautRepository.getAstronautsFromLocal()
        }
    }
}
