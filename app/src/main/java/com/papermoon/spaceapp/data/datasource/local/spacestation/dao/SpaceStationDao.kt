package com.papermoon.spaceapp.data.datasource.local.spacestation.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.papermoon.spaceapp.data.datasource.local.spacestation.model.LocalSpaceStation

@Dao
interface SpaceStationDao {

    @Query("SELECT * FROM space_station")
    fun getAll(): List<LocalSpaceStation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stations: List<LocalSpaceStation>)

    @Query("DELETE FROM space_station")
    fun deleteAll()
}
