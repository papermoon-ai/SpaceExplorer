package com.papermoon.spaceapp.domain.repository

import com.papermoon.spaceapp.domain.model.celestialbody.CelestialBody
import com.papermoon.spaceapp.domain.resource.Resource

interface CelestialBodyRepository {
    suspend fun getPlanetsFromNetwork(): Resource<List<CelestialBody>>
    suspend fun getPlanetsFromLocal(): Resource<List<CelestialBody>>
    suspend fun savePlanetsToLocal(planets: List<CelestialBody>): Resource<Unit>
    suspend fun deleteAllFromLocal(): Resource<Unit>
}
