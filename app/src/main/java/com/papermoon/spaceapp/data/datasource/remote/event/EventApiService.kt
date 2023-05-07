package com.papermoon.spaceapp.data.datasource.remote.event

import com.papermoon.spaceapp.data.datasource.remote.event.model.NetworkEvent
import retrofit2.http.GET

interface EventApiService {
    @GET("/event/upcoming")
    suspend fun getUpcomingEvents(): List<NetworkEvent>
}
