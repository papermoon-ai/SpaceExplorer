package com.papermoon.spaceapp.domain.model.spacestation

import android.net.Uri
import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription
import org.joda.time.DateTime

data class SpaceStation(
    var name: String,
    var founded: DateTime,
    var description: String,
    var images: List<ImageWithDescription>,
    var isActive: Boolean,
    var owners: List<String>,
    var wikiUrl: Uri?
)
