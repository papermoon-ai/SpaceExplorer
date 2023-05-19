package com.papermoon.spaceapp.domain.model.spacestation

import com.papermoon.spaceapp.data.datasource.local.spacestation.model.LocalSpaceStation
import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription
import com.papermoon.spaceapp.domain.model.commons.toLocalObject
import org.joda.time.DateTime
import java.io.Serializable

data class SpaceStation(
    var name: String,
    var founded: DateTime,
    var description: String,
    var images: List<ImageWithDescription>,
    var isActive: Boolean,
    var owners: List<String>,
    var wikiUrl: String?
) : Serializable

fun SpaceStation.toLocalObject(): LocalSpaceStation {
    return LocalSpaceStation(
        name,
        founded.toDate(),
        description,
        images.map { it.toLocalObject() },
        isActive,
        owners,
        wikiUrl
    )
}

fun List<SpaceStation>.toLocalObject(): List<LocalSpaceStation> {
    return this.map { it.toLocalObject() }
}
