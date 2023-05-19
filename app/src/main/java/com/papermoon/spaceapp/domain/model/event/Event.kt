package com.papermoon.spaceapp.domain.model.event

import com.papermoon.spaceapp.data.datasource.local.event.model.LocalEvent
import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription
import com.papermoon.spaceapp.domain.model.commons.toLocalObject
import org.joda.time.DateTime
import java.io.Serializable

data class Event(
    val name: String,
    val description: String,
    val location: String?,
    val date: DateTime,
    val type: String,
    val images: List<ImageWithDescription>,
    val videoUrl: String?,
    val newsUrl: String?,
) : Serializable

fun Event.toLocalObject(): LocalEvent {
    return LocalEvent(
        name,
        description,
        location,
        date.toDate(),
        type,
        images.map { it.toLocalObject() },
        videoUrl,
        newsUrl
    )
}

fun List<Event>.toLocalObject(): List<LocalEvent> {
    return this.map { it.toLocalObject() }
}
