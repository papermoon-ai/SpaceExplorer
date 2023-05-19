package com.papermoon.spaceapp.data.datasource.remote.event.model

import com.google.gson.annotations.SerializedName
import com.papermoon.spaceapp.data.datasource.remote.commons.model.NetworkImageWithDescription
import com.papermoon.spaceapp.data.datasource.remote.commons.model.toDomainObject
import com.papermoon.spaceapp.domain.model.event.Event
import org.joda.time.DateTime
import java.util.*

data class NetworkEvent(
    val name: String,
    val description: String,
    val location: String,
    val date: Date,
    val type: String,
    val images: List<NetworkImageWithDescription>,
    @SerializedName("video_url")
    val videoUrl: String?,
    @SerializedName("news_url")
    val newsUrl: String?,
)

fun NetworkEvent.toDomainObject(): Event {
    return Event(
        name,
        description,
        location,
        DateTime(date),
        type,
        images.map { it.toDomainObject() },
        videoUrl,
        newsUrl
    )
}

fun List<NetworkEvent>.toDomainObject(): List<Event> {
    return map { it.toDomainObject() }
}
