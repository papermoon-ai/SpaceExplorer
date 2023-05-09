package com.papermoon.spaceapp.domain.model.launch

import com.papermoon.spaceapp.data.datasource.local.launch.model.LocalMission

data class Mission(
    val name: String,
    val description: String
)

fun Mission.toLocalObject(): LocalMission {
    return LocalMission(name, description)
}
