package com.papermoon.spaceapp.data.datasource.remote.celestialBody.model

import android.net.Uri
import com.papermoon.spaceapp.domain.model.CelestialBody

data class NetworkCelestialBody(
    val id: String,
    val name: String,
    val englishName: String,
    val discoverDate: String?,
    val discoveredBy: String,
    val gravity: String,
    val density: String,
    val description: String,
    val shortDescription: String,
    val imageUrl: String
)

fun NetworkCelestialBody.asDomainObject(): CelestialBody {
    return CelestialBody(
        name,
        englishName,
        discoverDate,
        discoveredBy,
        gravity,
        density,
        description,
        shortDescription,
        Uri.parse(imageUrl)
    )
}

fun List<NetworkCelestialBody>.asDomainObject(): List<CelestialBody> {
    return this.map { it.asDomainObject() }
}
