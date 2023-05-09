package com.papermoon.spaceapp.data.datasource.local.event.model

import androidx.core.net.toUri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.papermoon.spaceapp.data.datasource.local.common.model.LocalImageWithDescription
import com.papermoon.spaceapp.data.datasource.local.common.model.toDomainObject
import com.papermoon.spaceapp.domain.model.event.Event
import org.joda.time.DateTime
import java.util.*

@Entity(tableName = "event")
data class LocalEvent(
    @PrimaryKey
    val name: String,
    val description: String,
    val location: String,
    val date: Date,
    val type: String,
    val images: List<LocalImageWithDescription>,
    val videoUrl: String?,
    val newsUrl: String?,
)

fun LocalEvent.toDomainObject(): Event {
    return Event(
        name,
        description,
        location,
        DateTime(date),
        type,
        images.map { it.toDomainObject() },
        videoUrl?.toUri(),
        newsUrl?.toUri()
    )
}

fun List<LocalEvent>.toDomainObject(): List<Event> {
    return this.map { it.toDomainObject() }
}
