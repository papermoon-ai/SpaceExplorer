package com.papermoon.spaceapp.data.datasource.remote.astronaut

import com.papermoon.spaceapp.data.datasource.remote.astronaut.model.NetworkAstronaut
import retrofit2.http.GET

interface AstronautApiService {
    @GET("/astronaut")
    suspend fun getAstronauts(): List<NetworkAstronaut>
}