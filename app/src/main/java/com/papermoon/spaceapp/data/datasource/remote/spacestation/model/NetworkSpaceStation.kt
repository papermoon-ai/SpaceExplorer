package com.papermoon.spaceapp.data.datasource.remote.spacestation.model

import androidx.core.net.toUri
import com.papermoon.spaceapp.data.datasource.remote.model.NetworkImageWithDescription
import com.papermoon.spaceapp.data.datasource.remote.model.toDomainObject
import com.papermoon.spaceapp.domain.model.spacestation.SpaceStation
import org.joda.time.DateTime
import java.util.*

data class NetworkSpaceStation(
    var name: String,
    var founded: Date,
    var description: String,
    var images: List<NetworkImageWithDescription>,
    var isActive: Boolean,
    var owners: List<NetworkAgency>,
    var wikiUrl: String?
)

fun NetworkSpaceStation.toDomainObject(): SpaceStation {
    return SpaceStation(
        name,
        DateTime(founded),
        description,
        images.map { it.toDomainObject() },
        isActive,
        owners.map { it.name },
        wikiUrl?.toUri()
    )
}

fun List<NetworkSpaceStation>.toDomainObject(): List<SpaceStation> {
    return this.map { it.toDomainObject() }
}
