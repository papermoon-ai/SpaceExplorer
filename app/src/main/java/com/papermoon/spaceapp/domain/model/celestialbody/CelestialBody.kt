package com.papermoon.spaceapp.domain.model.celestialbody

import com.papermoon.spaceapp.data.datasource.local.celestialBody.model.LocalCelestialBody
import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription
import com.papermoon.spaceapp.domain.model.commons.toLocalObject
import java.io.Serializable

data class CelestialBody(
    val name: String,
    val englishName: String,
    val discoverDate: String?,
    val discoveredBy: String?,
    val satelliteCount: Int,
    val description: String,
    val shortDescription: String,
    val wikiUrl: String,
    val images: List<ImageWithDescription>,
    val characteristics: PhysicCharacteristics
) : Serializable

fun CelestialBody.toLocalObject(): LocalCelestialBody {
    return LocalCelestialBody(
        name,
        englishName,
        discoverDate,
        discoveredBy,
        satelliteCount,
        description,
        shortDescription,
        wikiUrl,
        images.map { it.toLocalObject() },
        characteristics.toLocalObject()
    )
}

fun List<CelestialBody>.toLocalObject(): List<LocalCelestialBody> {
    return this.map { it.toLocalObject() }
}
