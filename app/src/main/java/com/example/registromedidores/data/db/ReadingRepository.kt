package com.example.registromedidores.data.db

import kotlinx.coroutines.flow.Flow

class ReadingRepository(private val dao: ReadingDao) {

    fun getAllReadings(): Flow<List<ReadingEntity>> = dao.getAll()

    suspend fun insertReading(reading: ReadingEntity) {
        dao.insert(reading)
    }
}
