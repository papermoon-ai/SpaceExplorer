package com.papermoon.spaceapp.data.datasource.local.launch.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.papermoon.spaceapp.data.datasource.local.launch.model.LocalLaunch

@Dao
interface LaunchDao {
    @Query("SELECT * FROM launch")
    fun getAll(): List<LocalLaunch>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(launches: List<LocalLaunch>)
}
