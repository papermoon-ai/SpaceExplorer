package com.papermoon.spaceapp.data

import com.papermoon.spaceapp.data.datasource.local.launch.dao.LaunchDao
import com.papermoon.spaceapp.data.datasource.local.launch.model.toDomainObject
import com.papermoon.spaceapp.data.datasource.remote.launch.LaunchApiService
import com.papermoon.spaceapp.data.datasource.remote.launch.model.toDomainObject
import com.papermoon.spaceapp.domain.model.launch.Launch
import com.papermoon.spaceapp.domain.model.launch.toLocalObject
import com.papermoon.spaceapp.domain.repository.LaunchRepository
import com.papermoon.spaceapp.domain.resource.Resource

class LaunchRepositoryImpl(
    private val launchApiService: LaunchApiService,
    private val launchDao: LaunchDao
) : LaunchRepository {
    override suspend fun getUpcomingLaunchesFromNetwork(): Resource<List<Launch>> {
        return try {
            val launches = launchApiService.getUpcomingLaunches().toDomainObject()
            Resource.Success(launches)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun getUpcomingLaunchesFromLocal(): Resource<List<Launch>> {
        return try {
            val launches = launchDao.getAll().toDomainObject()
            Resource.Success(launches)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun saveUpcomingLaunchesToLocal(launches: List<Launch>): Resource<Unit> {
        return try {
            launchDao.insertAll(launches.toLocalObject())
            Resource.Success(Unit)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}
