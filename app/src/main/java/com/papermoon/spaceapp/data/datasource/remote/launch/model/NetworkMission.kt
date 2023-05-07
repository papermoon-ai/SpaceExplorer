package com.papermoon.spaceapp.data.datasource.remote.launch.model

import com.papermoon.spaceapp.domain.model.launch.Mission

data class NetworkMission(
    val name: String,
    val description: String
)

fun NetworkMission.asDomainObject(): Mission {
    return Mission(name, description)
}
