package com.papermoon.spaceapp.data.datasource.local.launch.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.papermoon.spaceapp.data.datasource.local.common.model.LocalImageWithDescription
import com.papermoon.spaceapp.data.datasource.local.common.model.toDomainObject
import com.papermoon.spaceapp.data.datasource.local.launch.converter.LocalMissionConverter
import com.papermoon.spaceapp.data.datasource.local.launch.converter.LocalPadConverter
import com.papermoon.spaceapp.domain.model.launch.Launch
import org.joda.time.DateTime
import java.util.*

@Entity(tableName = "launch")
data class LocalLaunch(
    @PrimaryKey
    val name: String,
    val launchDate: Date,
    val launchServiceProvider: String,
    val images: List<LocalImageWithDescription>,
    @field:TypeConverters(LocalPadConverter::class)
    val pad: LocalPad,
    @field:TypeConverters(LocalMissionConverter::class)
    val mission: LocalMission?
)

fun LocalLaunch.toDomainObject(): Launch {
    return Launch(
        name,
        DateTime(launchDate),
        launchServiceProvider,
        images.map { it.toDomainObject() },
        pad.toDomainObject(),
        mission?.toDomainObject()
    )
}

fun List<LocalLaunch>.toDomainObject(): List<Launch> {
    return this.map { it.toDomainObject() }
}
