package com.example.registromedidores.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registromedidores.data.db.ReadingEntity
import com.example.registromedidores.data.db.ReadingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReadingViewModel(
    private val repository: ReadingRepository
) : ViewModel() {

    val readings: StateFlow<List<ReadingEntity>> =
        repository.getAllReadings()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun insertReading(reading: ReadingEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertReading(reading)
        }
    }

    fun addReading(type: String, value: Int) {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        insertReading(
            ReadingEntity(
                type = type,
                value = value,
                date = today
            )
        )
    }
}

