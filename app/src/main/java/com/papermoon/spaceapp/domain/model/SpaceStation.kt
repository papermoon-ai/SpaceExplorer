package com.papermoon.spaceapp.domain.model

import android.net.Uri
import org.joda.time.DateTime

data class SpaceStation(
    var name: String,
    var founded: DateTime,
    var description: String,
    var imageUrl: Uri,
    var isActive: Boolean,
    var owners: List<String>,
    var wikiUrl: Uri?
)
