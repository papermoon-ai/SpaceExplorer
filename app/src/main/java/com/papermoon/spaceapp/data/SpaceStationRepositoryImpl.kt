package com.papermoon.spaceapp.data

import com.papermoon.spaceapp.data.datasource.remote.spacestation.SpaceStationApiService
import com.papermoon.spaceapp.data.datasource.remote.spacestation.model.asDomainObject
import com.papermoon.spaceapp.domain.model.SpaceStation
import com.papermoon.spaceapp.domain.repository.SpaceStationRepository
import com.papermoon.spaceapp.domain.resource.Resource

class SpaceStationRepositoryImpl(
    private val spaceStationApiService: SpaceStationApiService
) : SpaceStationRepository {

    override suspend fun getSpaceStationsFromNetwork(): Resource<List<SpaceStation>> {
        return try {
            val spaceStations = spaceStationApiService.getSpaceStations().asDomainObject()
            Resource.Success(spaceStations)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getActiveSpaceStationsFromNetwork(): Resource<List<SpaceStation>> {
        return try {
            val spaceStations = spaceStationApiService.getActiveSpaceStations().asDomainObject()
            Resource.Success(spaceStations)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}
