package com.papermoon.spaceapp.data.datasource.local.celestialBody.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.papermoon.spaceapp.data.datasource.local.celestialBody.converter.LocalPhysicCharacteristicsConverter
import com.papermoon.spaceapp.data.datasource.local.common.model.LocalImageWithDescription
import com.papermoon.spaceapp.data.datasource.local.common.model.toDomainObject
import com.papermoon.spaceapp.domain.model.celestialbody.CelestialBody

@Entity(tableName = "celestial_body")
data class LocalCelestialBody(
    @PrimaryKey
    val name: String,
    @ColumnInfo("english_name")
    val englishName: String,
    @ColumnInfo("discover_date")
    val discoverDate: String?,
    @ColumnInfo("discovered_by")
    val discoveredBy: String?,
    @ColumnInfo("satellite_count")
    val satelliteCount: Int,
    val description: String,
    @ColumnInfo("short_description")
    val shortDescription: String,
    @ColumnInfo("wiki_url")
    val wikiUrl: String,
    val images: List<LocalImageWithDescription>,
    @field:TypeConverters(LocalPhysicCharacteristicsConverter::class)
    val characteristics: LocalPhysicCharacteristics
)

fun LocalCelestialBody.toDomainObject(): CelestialBody {
    return CelestialBody(
        name,
        englishName,
        discoverDate,
        discoveredBy,
        satelliteCount,
        description,
        shortDescription,
        wikiUrl,
        images.map { it.toDomainObject() },
        characteristics.toDomainObject()
    )
}

fun List<LocalCelestialBody>.toDomainObject(): List<CelestialBody> {
    return this.map { it.toDomainObject() }
}
