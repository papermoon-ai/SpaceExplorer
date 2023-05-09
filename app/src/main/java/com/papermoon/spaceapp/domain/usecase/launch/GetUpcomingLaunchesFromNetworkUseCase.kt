package com.papermoon.spaceapp.domain.usecase.launch

import com.papermoon.spaceapp.domain.model.launch.Launch
import com.papermoon.spaceapp.domain.repository.LaunchRepository
import com.papermoon.spaceapp.domain.resource.Resource
import com.papermoon.spaceapp.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUpcomingLaunchesFromNetworkUseCase(
    private val networkLaunchRepository: LaunchRepository
) : UseCase<List<Launch>, Unit> {
    override suspend fun execute(params: Unit): Resource<List<Launch>> {
        return withContext(Dispatchers.IO) {
            networkLaunchRepository.getUpcomingLaunches()
        }
    }
}
