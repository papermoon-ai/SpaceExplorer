package com.papermoon.spaceapp.domain.model

import android.net.Uri

data class CelestialBody(
    val name: String,
    val englishName: String,
    val discoverDate: String?,
    val discoveredBy: String,
    val gravity: String,
    val density: String,
    val description: String,
    val shortDescription: String,
    val imageUrl: Uri
)
