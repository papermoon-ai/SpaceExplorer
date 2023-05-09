package com.papermoon.spaceapp.domain.usecase.astronaut

import com.papermoon.spaceapp.domain.model.astronaut.Astronaut
import com.papermoon.spaceapp.domain.repository.AstronautRepository
import com.papermoon.spaceapp.domain.resource.Resource
import com.papermoon.spaceapp.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveAstronautsToLocalUseCase(
    private val astronautRepository: AstronautRepository
) : UseCase<Unit, List<Astronaut>> {
    override suspend fun execute(params: List<Astronaut>): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            astronautRepository.saveAstronautsToLocal(params)
        }
    }
}
