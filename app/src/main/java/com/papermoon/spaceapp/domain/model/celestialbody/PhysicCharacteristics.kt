package com.papermoon.spaceapp.domain.model.celestialbody

data class PhysicCharacteristics(
    val gravity: Double,
    val density: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val avgOrbitalSpeed: Double,
    val area: Double,
    val rotationAroundAxis: Period,
    val rotationAroundSun: Period
)
