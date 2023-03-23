package com.papermoon.spaceapp.data.datasource.remote.launches.model

import android.net.Uri
import com.google.gson.annotations.SerializedName
import com.papermoon.spaceapp.domain.model.Launch
import org.joda.time.DateTime
import java.util.*

data class NetworkLaunchEntity(
    val name: String,
    val location: String,
    @SerializedName("window_end")
    val launchDate: Date,
    @SerializedName("lsp_name")
    val launchServiceProvider: String,
    @SerializedName("image")
    val imageUrl: String
)

fun NetworkLaunchEntity.asDomainObject(): Launch {
    return Launch(name, location, DateTime(launchDate), launchServiceProvider, Uri.parse(imageUrl))
}

fun List<NetworkLaunchEntity>.asDomainObject(): List<Launch> {
    return this.map { it.asDomainObject() }
}
