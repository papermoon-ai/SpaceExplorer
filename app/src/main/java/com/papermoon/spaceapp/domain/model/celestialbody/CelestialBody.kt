package com.papermoon.spaceapp.domain.model.celestialbody

import android.net.Uri
import com.papermoon.spaceapp.data.datasource.local.celestialBody.model.LocalCelestialBody
import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription
import com.papermoon.spaceapp.domain.model.commons.toLocalObject

data class CelestialBody(
    val name: String,
    val englishName: String,
    val discoverDate: String?,
    val discoveredBy: String?,
    val satelliteCount: Int,
    val description: String,
    val shortDescription: String,
    val wikiUrl: Uri,
    val images: List<ImageWithDescription>,
    val characteristics: PhysicCharacteristics
)

fun CelestialBody.toLocalObject(): LocalCelestialBody {
    return LocalCelestialBody(
        name,
        englishName,
        discoverDate,
        discoveredBy,
        satelliteCount,
        description,
        shortDescription,
        wikiUrl.toString(),
        images.map { it.toLocalObject() },
        characteristics.toLocalObject()
    )
}

fun List<CelestialBody>.toLocalObject(): List<LocalCelestialBody> {
    return this.map { it.toLocalObject() }
}
