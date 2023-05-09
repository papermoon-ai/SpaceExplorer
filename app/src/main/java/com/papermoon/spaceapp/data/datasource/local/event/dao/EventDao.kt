package com.papermoon.spaceapp.data.datasource.local.event.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.papermoon.spaceapp.data.datasource.local.event.model.LocalEvent

@Dao
interface EventDao {

    @Query("SELECT * FROM event")
    fun getAll(): List<LocalEvent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(events: List<LocalEvent>)
}
