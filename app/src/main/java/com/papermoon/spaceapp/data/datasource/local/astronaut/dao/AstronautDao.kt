package com.papermoon.spaceapp.data.datasource.local.astronaut.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.papermoon.spaceapp.data.datasource.local.astronaut.model.LocalAstronaut

@Dao
interface AstronautDao {
    @Query("SELECT * FROM astronaut")
    fun getAll(): List<LocalAstronaut>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(astronauts: List<LocalAstronaut>)

    @Query("DELETE FROM astronaut")
    fun deleteAll()
}
