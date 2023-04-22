package com.papermoon.spaceapp.domain.model.celestialbody

import android.net.Uri
import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription

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
