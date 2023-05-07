package com.papermoon.spaceapp.domain.model.event

import android.net.Uri
import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription
import org.joda.time.DateTime

data class Event(
    val name: String,
    val description: String,
    val location: String,
    val date: DateTime,
    val type: String,
    val images: List<ImageWithDescription>,
    val videoUrl: Uri?,
    val newsUrl: Uri?,
)
