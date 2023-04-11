package com.papermoon.spaceapp.data.datasource.remote.celestialBody.model

import com.papermoon.spaceapp.data.datasource.remote.model.NetworkImageWithDescription
import com.papermoon.spaceapp.data.datasource.remote.model.toDomainObject
import com.papermoon.spaceapp.domain.model.celestialbody.CelestialBody

data class NetworkCelestialBody(
    val id: String,
    val name: String,
    val englishName: String,
    val discoverDate: String?,
    val discoveredBy: String?,
    val satelliteCount: Int,
    val description: String,
    val shortDescription: String,
    val images: List<NetworkImageWithDescription>,
    val characteristics: NetworkPhysicCharacteristics
)

fun NetworkCelestialBody.toDomainObject(): CelestialBody {
    return CelestialBody(
        name,
        englishName,
        discoverDate,
        discoveredBy,
        satelliteCount,
        description,
        shortDescription,
        images.map { it.toDomainObject() },
        characteristics.toDomainObject()
    )
}

fun List<NetworkCelestialBody>.toDomainObject(): List<CelestialBody> {
    return this.map { it.toDomainObject() }
}
