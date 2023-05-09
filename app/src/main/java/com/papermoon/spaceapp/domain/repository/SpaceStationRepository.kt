package com.papermoon.spaceapp.domain.repository

import com.papermoon.spaceapp.domain.model.spacestation.SpaceStation
import com.papermoon.spaceapp.domain.resource.Resource

interface SpaceStationRepository {
    suspend fun getSpaceStationsFromNetwork(): Resource<List<SpaceStation>>
    suspend fun getSpaceStationsFromLocal(): Resource<List<SpaceStation>>
    suspend fun saveSpaceStationsToLocal(stations: List<SpaceStation>): Resource<Unit>
}
