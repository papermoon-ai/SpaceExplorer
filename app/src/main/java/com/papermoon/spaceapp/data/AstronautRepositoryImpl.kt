package com.papermoon.spaceapp.data

import com.papermoon.spaceapp.data.datasource.remote.astronaut.AstronautApiService
import com.papermoon.spaceapp.data.datasource.remote.astronaut.model.asDomainObject
import com.papermoon.spaceapp.domain.model.Astronaut
import com.papermoon.spaceapp.domain.repository.AstronautRepository
import com.papermoon.spaceapp.domain.resource.Resource

class AstronautRepositoryImpl(
    private val astronautApiService: AstronautApiService
) : AstronautRepository {

    override suspend fun getAstronautsFromNetwork(): Resource<List<Astronaut>> {
        return try {
            val astronauts = astronautApiService.getAstronauts().asDomainObject()
            Resource.Success(astronauts)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}
