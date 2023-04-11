package com.papermoon.spaceapp.data

import com.papermoon.spaceapp.data.datasource.remote.launches.LaunchApiService
import com.papermoon.spaceapp.data.datasource.remote.launches.model.asDomainObject
import com.papermoon.spaceapp.domain.model.launch.Launch
import com.papermoon.spaceapp.domain.repository.LaunchRepository
import com.papermoon.spaceapp.domain.resource.Resource

class LaunchRepositoryImpl(
    private val launchApiService: LaunchApiService
) : LaunchRepository {
    override suspend fun getUpcomingLaunches(): Resource<List<Launch>> {
        return try {
            val launches = launchApiService.getUpcomingLaunches().asDomainObject()
            Resource.Success(launches)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}
