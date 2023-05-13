package com.papermoon.spaceapp.domain.usecase.launch

import com.papermoon.spaceapp.domain.repository.LaunchRepository
import com.papermoon.spaceapp.domain.resource.Resource
import com.papermoon.spaceapp.domain.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteLaunchesFromLocalUseCase(
    private val launchRepository: LaunchRepository
) : UseCase<Unit, Unit> {
    override suspend fun execute(params: Unit): Resource<Unit> {
        return withContext(Dispatchers.IO) {
            launchRepository.deleteAllFromLocal()
        }
    }
}
