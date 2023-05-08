package com.papermoon.spaceapp.domain.model.astronaut

import android.net.Uri
import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription
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
