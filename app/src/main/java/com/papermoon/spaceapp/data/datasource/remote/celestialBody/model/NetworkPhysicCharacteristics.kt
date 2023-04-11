package com.papermoon.spaceapp.data.datasource.remote.celestialBody.model

import com.papermoon.spaceapp.domain.model.celestialbody.PhysicCharacteristics

data class NetworkPhysicCharacteristics(
    val gravity: Double,
    val density: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val avgOrbitalSpeed: Double,
    val area: Double,
    val rotationAroundAxis: NetworkPeriod,
    val rotationAroundSun: NetworkPeriod
)

fun NetworkPhysicCharacteristics.toDomainObject(): PhysicCharacteristics {
    return PhysicCharacteristics(
        gravity,
        density,
        minTemperature,
        maxTemperature,
        avgOrbitalSpeed,
        area,
        rotationAroundAxis.toDomainObject(),
        rotationAroundSun.toDomainObject()
    )
}
