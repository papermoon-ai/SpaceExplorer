package com.papermoon.spaceapp.data.datasource.remote.astronaut.model

import com.google.gson.annotations.SerializedName
import com.papermoon.spaceapp.data.datasource.remote.commons.model.NetworkImageWithDescription
import com.papermoon.spaceapp.data.datasource.remote.commons.model.toDomainObject
import com.papermoon.spaceapp.domain.model.astronaut.Astronaut
import org.joda.time.DateTime
import java.util.*

data class NetworkAstronaut(
    val name: String,
    val spacecraft: String,
    @SerializedName("date_of_birth")
    val dateOfBirth: Date,
    val images: List<NetworkImageWithDescription>,
    val bio: String,
    @SerializedName("nationality")
    val country: String,
    @SerializedName("wiki")
    val wikiUrl: String?,
    @SerializedName("first_flight")
    val firstFlight: Date,
    @SerializedName("late_flight")
    val lastFlight: Date
)

fun NetworkAstronaut.toDomainObject(): Astronaut {
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

fun List<NetworkAstronaut>.toDomainObject(): List<Astronaut> {
    return this.map { it.toDomainObject() }
}
