package com.papermoon.spaceapp.data.datasource.local.celestialBody.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.papermoon.spaceapp.data.datasource.local.celestialBody.model.LocalCelestialBody

@Dao
interface CelestialBodyDao {

    @Query("SELECT * FROM celestial_body")
    fun getAll(): List<LocalCelestialBody>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(celestialBodies: List<LocalCelestialBody>)

    @Query("DELETE FROM celestial_body")
    fun deleteAll()
}
