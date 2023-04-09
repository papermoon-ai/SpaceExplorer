package com.papermoon.spaceapp.data.datasource.remote.celestialBody.model

import androidx.core.net.toUri
import com.papermoon.spaceapp.domain.model.CelestialBody

data class NetworkCelestialBody(
    val id: String,
    val name: String,
    val englishName: String,
    val discoverDate: String?,
    val discoveredBy: String?,
    val satelliteCount: Int,
    val description: String,
    val shortDescription: String,
    val imageUrls: List<String>,
    val characteristics: NetworkPhysicCharacteristics
)

fun NetworkCelestialBody.asDomainObject(): CelestialBody {
    return CelestialBody(
        name,
        englishName,
        discoverDate,
        discoveredBy,
        satelliteCount,
        description,
        shortDescription,
        imageUrls.map { it.toUri() },
        characteristics.toDomainObject()
    )
}

fun List<NetworkCelestialBody>.asDomainObject(): List<CelestialBody> {
    return this.map { it.asDomainObject() }
}
