package com.papermoon.spaceapp.data

import com.papermoon.spaceapp.data.datasource.local.celestialBody.dao.CelestialBodyDao
import com.papermoon.spaceapp.data.datasource.local.celestialBody.model.toDomainObject
import com.papermoon.spaceapp.data.datasource.remote.celestialBody.CelestialBodyApiService
import com.papermoon.spaceapp.data.datasource.remote.celestialBody.model.toDomainObject
import com.papermoon.spaceapp.domain.model.celestialbody.CelestialBody
import com.papermoon.spaceapp.domain.model.celestialbody.toLocalObject
import com.papermoon.spaceapp.domain.repository.CelestialBodyRepository
import com.papermoon.spaceapp.domain.resource.Resource

class CelestialBodyRepositoryImpl(
    private val celestialBodyApiService: CelestialBodyApiService,
    private val celestialBodyDao: CelestialBodyDao
) : CelestialBodyRepository {
    override suspend fun getPlanetsFromNetwork(): Resource<List<CelestialBody>> {
        return try {
            val planets = celestialBodyApiService.getPlanets().toDomainObject()
            Resource.Success(planets)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getPlanetsFromLocal(): Resource<List<CelestialBody>> {
        return try {
            val planets = celestialBodyDao.getAll().toDomainObject()
            Resource.Success(planets)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun savePlanetsToLocal(planets: List<CelestialBody>): Resource<Unit> {
        return try {
            celestialBodyDao.insertAll(planets.toLocalObject())
            return Resource.Success(Unit)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}
