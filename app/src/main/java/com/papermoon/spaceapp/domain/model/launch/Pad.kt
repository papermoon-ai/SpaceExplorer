package com.papermoon.spaceapp.domain.model.launch

import android.net.Uri
import com.papermoon.spaceapp.data.datasource.local.launch.model.LocalPad

data class Pad(
    val name: String,
    val location: String,
    val mapUrl: Uri?,
    val wikiUrl: Uri?
)

fun Pad.toLocalObject(): LocalPad {
    return LocalPad(name, location, mapUrl.toString(), wikiUrl.toString())
}
