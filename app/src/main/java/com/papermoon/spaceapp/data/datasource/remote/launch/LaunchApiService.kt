package com.papermoon.spaceapp.data.datasource.remote.launch

import com.papermoon.spaceapp.data.datasource.remote.launch.model.NetworkLaunch
import retrofit2.http.GET

interface LaunchApiService {
    @GET("/launch/upcoming")
    suspend fun getUpcomingLaunches(): List<NetworkLaunch>
}
