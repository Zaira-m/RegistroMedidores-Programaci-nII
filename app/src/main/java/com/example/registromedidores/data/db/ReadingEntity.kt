package com.example.registromedidores.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "readings")
data class ReadingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String,   // "WATER" | "LIGHT" | "GAS"
    val value: Int,
    val date: String    // "YYYY-MM-DD"
)
