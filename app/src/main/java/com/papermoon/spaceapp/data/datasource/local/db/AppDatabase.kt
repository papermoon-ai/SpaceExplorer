package com.papermoon.spaceapp.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.papermoon.spaceapp.data.datasource.local.astronaut.dao.AstronautDao
import com.papermoon.spaceapp.data.datasource.local.astronaut.model.LocalAstronaut
import com.papermoon.spaceapp.data.datasource.local.celestialBody.dao.CelestialBodyDao
import com.papermoon.spaceapp.data.datasource.local.celestialBody.model.LocalCelestialBody
import com.papermoon.spaceapp.data.datasource.local.common.converter.DateConverter
import com.papermoon.spaceapp.data.datasource.local.common.converter.LocalImageWithDescriptionConverter
import com.papermoon.spaceapp.data.datasource.local.launch.dao.LaunchDao
import com.papermoon.spaceapp.data.datasource.local.launch.model.LocalLaunch

@Database(
    entities = [LocalCelestialBody::class, LocalAstronaut::class, LocalLaunch::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class, LocalImageWithDescriptionConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun celestialBodyDao(): CelestialBodyDao
    abstract fun astronautDao(): AstronautDao
    abstract fun launchDao(): LaunchDao
}
