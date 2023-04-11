package com.papermoon.spaceapp.data.datasource.remote.astronaut.model

import androidx.core.net.toUri
import com.google.gson.annotations.SerializedName
import com.papermoon.spaceapp.domain.model.astronaut.Astronaut
import org.joda.time.DateTime
import java.util.*

data class NetworkAstronaut(
    val name: String,
    val spacecraft: String,
    @SerializedName("date_of_birth")
    val dateOfBirth: Date,
    @SerializedName("profile_image")
    val profileImage: String?,
    val bio: String,
    val nationality: String,
    @SerializedName("wiki")
    val wikiUrl: String?,
    @SerializedName("first_flight")
    val firstFlight: Date,
    @SerializedName("late_flight")
    val lastFlight: Date
)

fun NetworkAstronaut.asDomainObject(): Astronaut {
    return Astronaut(
        name,
        spacecraft,
        DateTime(dateOfBirth),
        profileImage?.toUri(),
        bio,
        nationality,
        wikiUrl?.toUri(),
        DateTime(firstFlight),
        DateTime(lastFlight)
    )
}

fun List<NetworkAstronaut>.asDomainObject(): List<Astronaut> {
    return this.map { it.asDomainObject() }
}