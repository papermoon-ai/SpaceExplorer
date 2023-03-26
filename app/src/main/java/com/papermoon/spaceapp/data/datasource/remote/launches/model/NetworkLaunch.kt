package com.papermoon.spaceapp.data.datasource.remote.launches.model

import android.net.Uri
import com.google.gson.annotations.SerializedName
import com.papermoon.spaceapp.domain.model.Launch
import org.joda.time.DateTime
import java.util.*

data class NetworkLaunch(
    val name: String,
    @SerializedName("window_end")
    val launchDate: Date,
    val launchServiceProvider: String,
    @SerializedName("image")
    val imageUrl: String,
    val pad: NetworkPad,
    val mission: NetworkMission
)

fun NetworkLaunch.asDomainObject(): Launch {
    return Launch(
        name,
        DateTime(launchDate),
        launchServiceProvider,
        Uri.parse(imageUrl),
        pad.asDomainObject(),
        mission.asDomainObject()
    )
}

fun List<NetworkLaunch>.asDomainObject(): List<Launch> {
    return this.map { it.asDomainObject() }
}
