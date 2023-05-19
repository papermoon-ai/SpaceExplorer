package com.papermoon.spaceapp.domain.model.launch

import com.papermoon.spaceapp.data.datasource.local.launch.model.LocalMission
import java.io.Serializable

data class Mission(
    val name: String,
    val description: String
) : Serializable

fun Mission.toLocalObject(): LocalMission {
    return LocalMission(name, description)
}
