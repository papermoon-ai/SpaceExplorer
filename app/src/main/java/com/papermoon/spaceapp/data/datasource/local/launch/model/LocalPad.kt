package com.papermoon.spaceapp.data.datasource.local.launch.model

import androidx.core.net.toUri
import com.papermoon.spaceapp.domain.model.launch.Pad

data class LocalPad(
    val name: String,
    val location: String,
    val mapUrl: String?,
    val wikiUrl: String?
)

fun LocalPad.toDomainObject(): Pad {
    return Pad(name, location, mapUrl?.toUri(), wikiUrl?.toUri())
}
