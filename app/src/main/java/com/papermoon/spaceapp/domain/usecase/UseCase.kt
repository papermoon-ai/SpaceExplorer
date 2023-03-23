package com.papermoon.spaceapp.domain.usecase

import com.papermoon.spaceapp.domain.resource.Resource

interface UseCase<T, P> {
    suspend fun execute(params: P): Resource<T>
}
