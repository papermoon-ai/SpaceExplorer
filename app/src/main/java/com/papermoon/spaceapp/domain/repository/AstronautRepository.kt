package com.papermoon.spaceapp.domain.repository

import com.papermoon.spaceapp.domain.model.astronaut.Astronaut
import com.papermoon.spaceapp.domain.resource.Resource

interface AstronautRepository {
    suspend fun getAstronautsFromNetwork(): Resource<List<Astronaut>>
    suspend fun getAstronautsFromLocal(): Resource<List<Astronaut>>
    suspend fun saveAstronautsToLocal(astronauts: List<Astronaut>): Resource<Unit>
    suspend fun deleteAllFromLocal(): Resource<Unit>
}
