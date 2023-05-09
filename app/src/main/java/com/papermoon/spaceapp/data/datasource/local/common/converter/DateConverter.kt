package com.papermoon.spaceapp.data.datasource.local.common.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class DateConverter {
    @TypeConverter
    fun toDate(json: String): Date {
        val gson = Gson()
        val type = object : TypeToken<Date>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromDate(dateTime: Date): String {
        val gson = Gson()
        val type = object : TypeToken<Date>() {}.type
        return gson.toJson(dateTime, type)
    }
}
