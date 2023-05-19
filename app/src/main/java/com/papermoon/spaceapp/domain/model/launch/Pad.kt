package com.papermoon.spaceapp.domain.model.launch

import com.papermoon.spaceapp.data.datasource.local.launch.model.LocalPad
import java.io.Serializable

data class Pad(
    val name: String,
    val location: String,
    val mapUrl: String?,
    val wikiUrl: String?
) : Serializable

fun Pad.toLocalObject(): LocalPad {
    return LocalPad(name, location, mapUrl, wikiUrl)
}
