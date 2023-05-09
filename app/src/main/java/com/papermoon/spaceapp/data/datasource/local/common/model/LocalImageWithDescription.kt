package com.papermoon.spaceapp.data.datasource.local.common.model

import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription

data class LocalImageWithDescription(
    val imageUrl: String,
    val description: String?
)

fun LocalImageWithDescription.toDomainObject(): ImageWithDescription {
    return ImageWithDescription(imageUrl, description)
}
