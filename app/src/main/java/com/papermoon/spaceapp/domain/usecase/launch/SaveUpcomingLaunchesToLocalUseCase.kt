package com.papermoon.spaceapp.domain.usecase.launch

import com.papermoon.spaceapp.domain.model.launch.Launch
import com.papermoon.spaceapp.domain.repository.LaunchRepository
import com.papermoon.spaceapp.domain.resource.Resource
import com.papermoon.spaceapp.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveUpcomingLaunchesToLocalUseCase(
    private val launchRepository: LaunchRepository
) : UseCase<Unit, List<Launch>> {
    override suspend fun execute(params: List<Launch>): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            launchRepository.saveUpcomingLaunchesToLocal(params)
        }
    }
}
