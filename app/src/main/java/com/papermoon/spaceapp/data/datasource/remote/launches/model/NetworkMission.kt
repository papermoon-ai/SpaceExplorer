package com.papermoon.spaceapp.data.datasource.remote.launches.model

import com.papermoon.spaceapp.domain.model.launch.Mission

data class NetworkMission(
    val name: String,
    val description: String
)

fun NetworkMission.asDomainObject(): Mission {
    return Mission(name, description)
}
