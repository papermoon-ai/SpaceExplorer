package com.papermoon.spaceapp.data.datasource.local.celestialBody.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.papermoon.spaceapp.data.datasource.local.celestialBody.model.LocalPhysicCharacteristics

class LocalPhysicCharacteristicsConverter {
    @TypeConverter
    fun toCharacteristics(json: String): LocalPhysicCharacteristics {
        val gson = Gson()
        val type = object : TypeToken<LocalPhysicCharacteristics>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromCharacteristics(physicCharacteristics: LocalPhysicCharacteristics): String {
        val gson = Gson()
        val type = object : TypeToken<LocalPhysicCharacteristics>() {}.type
        return gson.toJson(physicCharacteristics, type)
    }
}
