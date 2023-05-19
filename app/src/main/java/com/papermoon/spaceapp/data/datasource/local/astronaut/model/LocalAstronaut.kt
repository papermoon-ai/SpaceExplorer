package com.papermoon.spaceapp.data.datasource.local.astronaut.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.papermoon.spaceapp.data.datasource.local.common.converter.LocalImageWithDescriptionConverter
import com.papermoon.spaceapp.data.datasource.local.common.model.LocalImageWithDescription
import com.papermoon.spaceapp.data.datasource.local.common.model.toDomainObject
import com.papermoon.spaceapp.domain.model.astronaut.Astronaut
import org.joda.time.DateTime
import java.util.*

@Entity(tableName = "astronaut")
data class LocalAstronaut(
    @PrimaryKey
    val name: String,
    val spacecraft: String,
    val dateOfBirth: Date,
    @field:TypeConverters(LocalImageWithDescriptionConverter::class)
    val images: List<LocalImageWithDescription>,
    val bio: String,
    val country: String,
    val wikiUrl: String?,
    val firstFlight: Date,
    val lastFlight: Date
)

fun LocalAstronaut.toDomainObject(): Astronaut {
    return Astronaut(
        name,
        spacecraft,
        DateTime(dateOfBirth),
        images.map { it.toDomainObject() },
        bio,
        country,
        wikiUrl,
        DateTime(firstFlight),
        DateTime(lastFlight)
    )
}

fun List<LocalAstronaut>.toDomainObject(): List<Astronaut> {
    return this.map { it.toDomainObject() }
}
