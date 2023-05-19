package com.papermoon.spaceapp.domain.model.commons

import com.papermoon.spaceapp.data.datasource.local.common.model.LocalImageWithDescription
import java.io.Serializable

data class ImageWithDescription(
    val imageUrl: String,
    val description: String?
) : Serializable

fun ImageWithDescription.toLocalObject(): LocalImageWithDescription {
    return LocalImageWithDescription(imageUrl, description)
}
