package com.papermoon.spaceapp.domain.model.launch

import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription
import org.joda.time.DateTime

data class Launch(
    val name: String,
    val launchDate: DateTime,
    val launchServiceProvider: String,
    val images: List<ImageWithDescription>,
    val pad: Pad,
    val mission: Mission?
)
