package com.papermoon.spaceapp.domain.model.astronaut

import android.net.Uri
import com.papermoon.spaceapp.data.datasource.local.astronaut.model.LocalAstronaut
import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription
import com.papermoon.spaceapp.domain.model.commons.toLocalObject
import org.joda.time.DateTime

data class Astronaut(
    val name: String,
    val spacecraft: String,
    val dateOfBirth: DateTime,
    val images: List<ImageWithDescription>,
    val bio: String,
    val country: String,
    val wikiUrl: Uri?,
    val firstFlight: DateTime,
    val lastFlight: DateTime
)

fun Astronaut.toLocalObject(): LocalAstronaut {
    return LocalAstronaut(
        name,
        spacecraft,
        dateOfBirth.toDate(),
        images.map { it.toLocalObject() },
        bio,
        country,
        wikiUrl.toString(),
        firstFlight.toDate(),
        lastFlight.toDate()
    )
}

fun List<Astronaut>.toLocalObject(): List<LocalAstronaut> {
    return this.map { it.toLocalObject() }
}
