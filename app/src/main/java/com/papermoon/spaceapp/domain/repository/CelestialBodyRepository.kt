package com.papermoon.spaceapp.domain.repository

import com.papermoon.spaceapp.domain.model.celestialbody.CelestialBody
import com.papermoon.spaceapp.domain.resource.Resource

interface CelestialBodyRepository {
    suspend fun getPlanets(): Resource<List<CelestialBody>>
}
