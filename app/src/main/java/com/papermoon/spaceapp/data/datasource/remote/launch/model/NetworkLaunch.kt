package com.papermoon.spaceapp.data.datasource.remote.launch.model

import com.google.gson.annotations.SerializedName
import com.papermoon.spaceapp.data.datasource.remote.commons.model.NetworkImageWithDescription
import com.papermoon.spaceapp.data.datasource.remote.commons.model.toDomainObject
import com.papermoon.spaceapp.domain.model.launch.Launch
import org.joda.time.DateTime
import java.util.*

data class NetworkLaunch(
    val name: String,
    @SerializedName("window_end")
    val launchDate: Date,
    val launchServiceProvider: String,
    val images: List<NetworkImageWithDescription>,
    val pad: NetworkPad,
    val mission: NetworkMission?
)

fun NetworkLaunch.asDomainObject(): Launch {
    return Launch(
        name,
        DateTime(launchDate),
        launchServiceProvider,
        images.map { it.toDomainObject() },
        pad.asDomainObject(),
        mission?.asDomainObject()
    )
}

fun List<NetworkLaunch>.asDomainObject(): List<Launch> {
    return this.map { it.asDomainObject() }
}
