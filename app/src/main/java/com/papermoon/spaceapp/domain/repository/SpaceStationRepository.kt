package com.papermoon.spaceapp.domain.repository

import com.papermoon.spaceapp.domain.model.SpaceStation
import com.papermoon.spaceapp.domain.resource.Resource

interface SpaceStationRepository {
    suspend fun getSpaceStationsFromNetwork(): Resource<List<SpaceStation>>
    suspend fun getActiveSpaceStationsFromNetwork(): Resource<List<SpaceStation>>
}
