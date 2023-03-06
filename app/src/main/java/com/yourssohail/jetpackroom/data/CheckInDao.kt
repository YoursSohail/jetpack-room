package com.yourssohail.jetpackroom.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CheckInDao {
    @Query("SELECT * FROM CheckIn")
    fun getAllCheckIns(): List<CheckIn>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCheckIn(checkIn: CheckIn)
}