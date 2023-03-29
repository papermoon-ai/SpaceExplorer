package com.papermoon.spaceapp.data.datasource.remote.celestialBody

import com.papermoon.spaceapp.data.datasource.remote.celestialBody.model.NetworkCelestialBody
import retrofit2.http.GET

interface CelestialBodyApiService {
    @GET("/celestialBody")
    suspend fun getPlanets(): List<NetworkCelestialBody>
}
