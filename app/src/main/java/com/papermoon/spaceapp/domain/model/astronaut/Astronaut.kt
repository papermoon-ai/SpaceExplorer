package com.papermoon.spaceapp.domain.model.astronaut

import com.papermoon.spaceapp.data.datasource.local.astronaut.model.LocalAstronaut
import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription
import com.papermoon.spaceapp.domain.model.commons.toLocalObject
import org.joda.time.DateTime
import java.io.Serializable

data class Astronaut(
    val name: String,
    val spacecraft: String,
    val dateOfBirth: DateTime,
    val images: List<ImageWithDescription>,
    val bio: String,
    val country: String,
    val wikiUrl: String?,
    val firstFlight: DateTime,
    val lastFlight: DateTime
) : Serializable

fun Astronaut.toLocalObject(): LocalAstronaut {
    return LocalAstronaut(
        name,
        spacecraft,
        dateOfBirth.toDate(),
        images.map { it.toLocalObject() },
        bio,
        country,
        wikiUrl,
        firstFlight.toDate(),
        lastFlight.toDate()
    )
}

fun List<Astronaut>.toLocalObject(): List<LocalAstronaut> {
    return this.map { it.toLocalObject() }
}
