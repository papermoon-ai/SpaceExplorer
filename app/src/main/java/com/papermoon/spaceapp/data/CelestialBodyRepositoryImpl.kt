package com.papermoon.spaceapp.data

import com.papermoon.spaceapp.data.datasource.remote.celestialBody.CelestialBodyApiService
import com.papermoon.spaceapp.data.datasource.remote.celestialBody.model.asDomainObject
import com.papermoon.spaceapp.domain.model.CelestialBody
import com.papermoon.spaceapp.domain.repository.CelestialBodyRepository
import com.papermoon.spaceapp.domain.resource.Resource

class CelestialBodyRepositoryImpl(
    private val celestialBodyApiService: CelestialBodyApiService
) : CelestialBodyRepository {
    override suspend fun getPlanets(): Resource<List<CelestialBody>> {
        return try {
            val planets = celestialBodyApiService.getPlanets().asDomainObject()
            Resource.Success(planets)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}
