package com.papermoon.spaceapp.domain.model.commons

import com.papermoon.spaceapp.data.datasource.local.common.model.LocalImageWithDescription

data class ImageWithDescription(
    val imageUrl: String,
    val description: String?
)

fun ImageWithDescription.toLocalObject(): LocalImageWithDescription {
    return LocalImageWithDescription(imageUrl, description)
}
