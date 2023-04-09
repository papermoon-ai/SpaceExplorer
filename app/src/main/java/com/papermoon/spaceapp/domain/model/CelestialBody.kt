package com.papermoon.spaceapp.domain.model

import android.net.Uri

data class CelestialBody(
    val name: String,
    val englishName: String,
    val discoverDate: String?,
    val discoveredBy: String?,
    val satelliteCount: Int,
    val description: String,
    val shortDescription: String,
    val imageUrls: List<Uri>,
    val characteristics: PhysicCharacteristics
)
