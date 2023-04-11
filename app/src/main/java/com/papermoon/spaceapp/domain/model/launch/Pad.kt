package com.papermoon.spaceapp.domain.model.launch

import android.net.Uri

data class Pad(
    val name: String,
    val location: String,
    val mapUrl: Uri?,
    val wikiUrl: Uri?
)
