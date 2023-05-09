package com.papermoon.spaceapp.data.datasource.local.launch.model

import com.papermoon.spaceapp.domain.model.launch.Mission

data class LocalMission(
    val name: String,
    val description: String
)

fun LocalMission.toDomainObject(): Mission {
    return Mission(name, description)
}
