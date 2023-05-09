package com.papermoon.spaceapp.data.datasource.local.launch.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.papermoon.spaceapp.data.datasource.local.launch.model.LocalMission

class LocalMissionConverter {
    @TypeConverter
    fun toMission(json: String): LocalMission {
        val gson = Gson()
        val type = object : TypeToken<LocalMission>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromMission(mission: LocalMission): String {
        val gson = Gson()
        val type = object : TypeToken<LocalMission>() {}.type
        return gson.toJson(mission, type)
    }
}
