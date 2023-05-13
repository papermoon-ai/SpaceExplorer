package com.papermoon.spaceapp.domain.repository

import com.papermoon.spaceapp.domain.model.launch.Launch
import com.papermoon.spaceapp.domain.resource.Resource

interface LaunchRepository {
    suspend fun getUpcomingLaunchesFromNetwork(): Resource<List<Launch>>
    suspend fun getUpcomingLaunchesFromLocal(): Resource<List<Launch>>
    suspend fun saveUpcomingLaunchesToLocal(launches: List<Launch>): Resource<Unit>
    suspend fun deleteAllFromLocal(): Resource<Unit>
}
