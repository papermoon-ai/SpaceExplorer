package com.papermoon.spaceapp.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.papermoon.spaceapp.data.datasource.local.celestialBody.dao.CelestialBodyDao
import com.papermoon.spaceapp.data.datasource.local.celestialBody.model.LocalCelestialBody

@Database(entities = [LocalCelestialBody::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun celestialBodyDao(): CelestialBodyDao
}
