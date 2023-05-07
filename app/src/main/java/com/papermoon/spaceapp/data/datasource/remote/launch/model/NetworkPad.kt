package com.papermoon.spaceapp.data.datasource.remote.launch.model

import androidx.core.net.toUri
import com.google.gson.annotations.SerializedName
import com.papermoon.spaceapp.domain.model.launch.Pad

data class NetworkPad(
    val name: String,
    val location: String,
    @SerializedName("map_url")
    val mapUrl: String?,
    @SerializedName("wiki_url")
    val wikiUrl: String?
)

fun NetworkPad.asDomainObject(): Pad {
    return Pad(name, location, mapUrl?.toUri(), wikiUrl?.toUri())
}