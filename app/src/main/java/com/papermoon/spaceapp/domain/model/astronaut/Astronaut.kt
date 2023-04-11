package com.papermoon.spaceapp.domain.model.astronaut

import android.net.Uri
import org.joda.time.DateTime

data class Astronaut(
    val name: String,
    val spacecraft: String,
    val dateOfBirth: DateTime,
    val profileImage: Uri?,
    val bio: String,
    val nationality: String,
    val wikiUrl: Uri?,
    val firstFlight: DateTime,
    val lastFlight: DateTime
)
