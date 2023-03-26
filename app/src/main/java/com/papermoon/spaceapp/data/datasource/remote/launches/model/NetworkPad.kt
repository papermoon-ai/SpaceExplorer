package com.papermoon.spaceapp.data.datasource.remote.launches.model

import android.net.Uri
import com.google.gson.annotations.SerializedName
import com.papermoon.spaceapp.domain.model.Pad

data class NetworkPad(
    val name: String,
    val location: String,
    @SerializedName("map_url")
    val mapUrl: String,
    @SerializedName("wiki_url")
    val wikiUrl: String
)

fun NetworkPad.asDomainObject(): Pad {
    return Pad(name, location, Uri.parse(mapUrl), Uri.parse(wikiUrl))
}