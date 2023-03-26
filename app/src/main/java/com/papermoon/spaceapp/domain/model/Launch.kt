package com.papermoon.spaceapp.domain.model

import android.net.Uri
import org.joda.time.DateTime

data class Launch (
    val name: String,
    val launchDate: DateTime,
    val launchServiceProvider: String,
    val imageUrl: Uri,
    val pad: Pad,
    val mission: Mission
)
