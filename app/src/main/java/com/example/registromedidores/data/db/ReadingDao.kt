package com.example.registromedidores.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadingDao {

    @Query("SELECT * FROM readings ORDER BY id DESC")
    fun getAll(): Flow<List<ReadingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reading: ReadingEntity)
}
