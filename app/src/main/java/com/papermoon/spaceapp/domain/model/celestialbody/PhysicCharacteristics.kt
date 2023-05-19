package com.papermoon.spaceapp.domain.model.celestialbody

import com.papermoon.spaceapp.data.datasource.local.celestialBody.model.LocalPhysicCharacteristics
import java.io.Serializable

data class PhysicCharacteristics(
    val gravity: Double,
    val density: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val avgOrbitalSpeed: Double,
    val area: Double,
    val rotationAroundAxis: Period,
    val rotationAroundSun: Period
) : Serializable

fun PhysicCharacteristics.toLocalObject(): LocalPhysicCharacteristics {
    return LocalPhysicCharacteristics(
        gravity,
        density,
        minTemperature,
        maxTemperature,
        avgOrbitalSpeed,
        area,
        rotationAroundAxis.toLocalObject(),
        rotationAroundSun.toLocalObject()
    )
}
