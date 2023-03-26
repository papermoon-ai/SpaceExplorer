package com.papermoon.spaceapp.data.datasource.remote.launches

import com.papermoon.spaceapp.data.datasource.remote.launches.model.NetworkLaunch
import retrofit2.http.GET

interface LaunchApiService {
    @GET("/launch/upcoming")
    suspend fun getUpcomingLaunches(): List<NetworkLaunch>
}
