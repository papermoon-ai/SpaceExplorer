package com.papermoon.spaceapp.data

import com.papermoon.spaceapp.data.datasource.local.spacestation.dao.SpaceStationDao
import com.papermoon.spaceapp.data.datasource.local.spacestation.model.toDomainObject
import com.papermoon.spaceapp.data.datasource.remote.spacestation.SpaceStationApiService
import com.papermoon.spaceapp.data.datasource.remote.spacestation.model.toDomainObject
import com.papermoon.spaceapp.domain.model.spacestation.SpaceStation
import com.papermoon.spaceapp.domain.model.spacestation.toLocalObject
import com.papermoon.spaceapp.domain.repository.SpaceStationRepository
import com.papermoon.spaceapp.domain.resource.Resource

class SpaceStationRepositoryImpl(
    private val spaceStationApiService: SpaceStationApiService,
    private val spaceStationDao: SpaceStationDao
) : SpaceStationRepository {

    override suspend fun getSpaceStationsFromNetwork(): Resource<List<SpaceStation>> {
        return try {
            val spaceStations = spaceStationApiService.getSpaceStations().toDomainObject()
            Resource.Success(spaceStations)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getSpaceStationsFromLocal(): Resource<List<SpaceStation>> {
        return try {
            val spaceStations = spaceStationDao.getAll().toDomainObject()
            Resource.Success(spaceStations)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun saveSpaceStationsToLocal(stations: List<SpaceStation>): Resource<Unit> {
        return try {
            spaceStationDao.insertAll(stations.toLocalObject())
            Resource.Success(Unit)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}
