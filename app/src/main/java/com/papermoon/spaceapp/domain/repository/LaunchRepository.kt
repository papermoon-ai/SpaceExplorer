package com.papermoon.spaceapp.domain.repository

import com.papermoon.spaceapp.domain.model.Launch
import com.papermoon.spaceapp.domain.resource.Resource

interface LaunchRepository {
    suspend fun getUpcomingLaunches(): Resource<List<Launch>>
}
