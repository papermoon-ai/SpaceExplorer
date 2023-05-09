package com.papermoon.spaceapp.data.datasource.local.celestialBody.model

import com.papermoon.spaceapp.domain.model.celestialbody.PhysicCharacteristics

data class LocalPhysicCharacteristics(
    val gravity: Double,
    val density: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val avgOrbitalSpeed: Double,
    val area: Double,
    val rotationAroundAxis: LocalPeriod,
    val rotationAroundSun: LocalPeriod
)

fun LocalPhysicCharacteristics.toDomainObject(): PhysicCharacteristics {
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
