package com.papermoon.spaceapp.data.datasource.remote.spacestation.model

import android.net.Uri
import com.google.gson.annotations.SerializedName
import com.papermoon.spaceapp.domain.model.SpaceStation
import org.joda.time.DateTime
import java.util.*

data class NetworkSpaceStation(
    var name: String,
    var founded: Date,
    var description: String,
    @SerializedName("image_url")
    var imageUrl: String,
    var isActive: Boolean,
    var owners: List<NetworkAgency>
)

fun NetworkSpaceStation.asDomainObject(): SpaceStation {
    return SpaceStation(
        name,
        DateTime(founded),
        description,
        Uri.parse(imageUrl),
        isActive,
        owners.map { it.name })
}

fun List<NetworkSpaceStation>.asDomainObject(): List<SpaceStation> {
    return this.map { it.asDomainObject() }
}
