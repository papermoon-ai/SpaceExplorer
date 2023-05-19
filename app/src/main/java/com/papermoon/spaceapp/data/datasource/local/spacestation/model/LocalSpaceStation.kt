package com.papermoon.spaceapp.data.datasource.local.spacestation.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.papermoon.spaceapp.data.datasource.local.common.model.LocalImageWithDescription
import com.papermoon.spaceapp.data.datasource.local.common.model.toDomainObject
import com.papermoon.spaceapp.data.datasource.local.spacestation.converter.ListOfStringConverter
import com.papermoon.spaceapp.domain.model.spacestation.SpaceStation
import org.joda.time.DateTime
import java.util.*

@Entity(tableName = "space_station")
data class LocalSpaceStation(
    @PrimaryKey
    var name: String,
    var founded: Date,
    var description: String,
    var images: List<LocalImageWithDescription>,
    var isActive: Boolean,
    @field:TypeConverters(ListOfStringConverter::class)
    var owners: List<String>,
    var wikiUrl: String?
)

fun LocalSpaceStation.toDomainObject(): SpaceStation {
    return SpaceStation(
        name,
        DateTime(founded),
        description,
        images.map { it.toDomainObject() },
        isActive,
        owners,
        wikiUrl
    )
}

fun List<LocalSpaceStation>.toDomainObject(): List<SpaceStation> {
    return this.map { it.toDomainObject() }
}
