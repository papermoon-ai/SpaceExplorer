package com.papermoon.spaceapp.data.datasource.local.launch.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.papermoon.spaceapp.data.datasource.local.launch.model.LocalPad

class LocalPadConverter {
    @TypeConverter
    fun toPad(json: String): LocalPad {
        val gson = Gson()
        val type = object : TypeToken<LocalPad>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromPad(pad: LocalPad): String {
        val gson = Gson()
        val type = object : TypeToken<LocalPad>() {}.type
        return gson.toJson(pad, type)
    }
}
