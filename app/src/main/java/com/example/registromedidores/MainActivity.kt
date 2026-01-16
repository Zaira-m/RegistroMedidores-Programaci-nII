package com.example.registromedidores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.registromedidores.data.db.AppDatabase
import com.example.registromedidores.data.db.ReadingRepository
import com.example.registromedidores.ui.navigation.AppNav
import com.example.registromedidores.ui.theme.RegistroMedidoresTheme
import com.example.registromedidores.ui.viewmodel.ReadingViewModel
import com.example.registromedidores.ui.viewmodel.ReadingViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // BD + Repo + Factory
        val database = AppDatabase.getInstance(applicationContext)
        val repository = ReadingRepository(dao = database.readingDao())
        val factory = ReadingViewModelFactory(repository)

        setContent {
            RegistroMedidoresTheme {
                val vm: ReadingViewModel = viewModel(factory = factory)
                AppNav(viewModel = vm)
            }
        }
    }
}
