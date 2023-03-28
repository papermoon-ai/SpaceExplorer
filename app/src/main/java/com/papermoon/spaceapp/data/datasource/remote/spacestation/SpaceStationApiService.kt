package com.papermoon.spaceapp.data.datasource.remote.spacestation

import com.papermoon.spaceapp.data.datasource.remote.spacestation.model.NetworkSpaceStation
import retrofit2.http.GET

interface SpaceStationApiService {
    @GET("/spacestation")
    suspend fun getSpaceStations(): List<NetworkSpaceStation>

    @GET("/spacestation/active")
    suspend fun getActiveSpaceStations(): List<NetworkSpaceStation>
}
