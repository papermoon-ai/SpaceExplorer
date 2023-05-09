package com.papermoon.spaceapp.domain.model.launch

import com.papermoon.spaceapp.data.datasource.local.launch.model.LocalLaunch
import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription
import com.papermoon.spaceapp.domain.model.commons.toLocalObject
import org.joda.time.DateTime

data class Launch(
    val name: String,
    val launchDate: DateTime,
    val launchServiceProvider: String,
    val images: List<ImageWithDescription>,
    val pad: Pad,
    val mission: Mission?
)

fun Launch.toLocalObject(): LocalLaunch {
    return LocalLaunch(
        name,
        launchDate.toDate(),
        launchServiceProvider,
        images.map { it.toLocalObject() },
        pad.toLocalObject(),
        mission?.toLocalObject()
    )
}

fun List<Launch>.toLocalObject(): List<LocalLaunch> {
    return this.map { it.toLocalObject() }
}
