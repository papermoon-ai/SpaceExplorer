package com.papermoon.spaceapp.data.datasource.remote.model

import com.papermoon.spaceapp.domain.model.commons.ImageWithDescription

data class NetworkImageWithDescription(
    val imageUrl: String,
    val description: String?
)

fun NetworkImageWithDescription.toDomainObject(): ImageWithDescription {
    return ImageWithDescription(imageUrl, description)
}
