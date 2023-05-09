package com.papermoon.spaceapp.data.datasource.local.common.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.papermoon.spaceapp.data.datasource.local.common.model.LocalImageWithDescription

class LocalImageWithDescriptionConverter {
    @TypeConverter
    fun toListOfImages(json: String): List<LocalImageWithDescription> {
        val gson = Gson()
        val type = object : TypeToken<List<LocalImageWithDescription>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListOfImages(imageWithDescription: List<LocalImageWithDescription>): String {
        val gson = Gson()
        val type = object : TypeToken<List<LocalImageWithDescription>>() {}.type
        return gson.toJson(imageWithDescription, type)
    }
}
