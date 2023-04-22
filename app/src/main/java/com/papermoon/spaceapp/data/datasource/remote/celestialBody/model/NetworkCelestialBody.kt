package com.papermoon.spaceapp.data.datasource.remote.celestialBody.model

import androidx.core.net.toUri
import com.papermoon.spaceapp.data.datasource.remote.commons.model.NetworkImageWithDescription
import com.papermoon.spaceapp.data.datasource.remote.commons.model.toDomainObject
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
    val wikiUrl: String,
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
        wikiUrl.toUri(),
        images.map { it.toDomainObject() },
        characteristics.toDomainObject()
    )
}

fun List<NetworkCelestialBody>.toDomainObject(): List<CelestialBody> {
    return this.map { it.toDomainObject() }
}
