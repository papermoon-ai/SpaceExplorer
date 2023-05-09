package com.papermoon.spaceapp.data

import com.papermoon.spaceapp.data.datasource.local.astronaut.dao.AstronautDao
import com.papermoon.spaceapp.data.datasource.local.astronaut.model.toDomainObject
import com.papermoon.spaceapp.data.datasource.remote.astronaut.AstronautApiService
import com.papermoon.spaceapp.data.datasource.remote.astronaut.model.toDomainObject
import com.papermoon.spaceapp.domain.model.astronaut.Astronaut
import com.papermoon.spaceapp.domain.model.astronaut.toLocalObject
import com.papermoon.spaceapp.domain.repository.AstronautRepository
import com.papermoon.spaceapp.domain.resource.Resource

class AstronautRepositoryImpl(
    private val astronautApiService: AstronautApiService,
    private val astronautDao: AstronautDao
) : AstronautRepository {

    override suspend fun getAstronautsFromNetwork(): Resource<List<Astronaut>> {
        return try {
            val astronauts = astronautApiService.getAstronauts().toDomainObject()
            Resource.Success(astronauts)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getAstronautsFromLocal(): Resource<List<Astronaut>> {
        return try {
            val astronauts = astronautDao.getAll().toDomainObject()
            Resource.Success(astronauts)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun saveAstronautsToLocal(astronauts: List<Astronaut>): Resource<Unit> {
        return try {
            astronautDao.insertAll(astronauts.toLocalObject())
            Resource.Success(Unit)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}
