package com.example.registromedidores.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.LocalGasStation
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.registromedidores.data.db.ReadingEntity
import com.example.registromedidores.ui.viewmodel.ReadingViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ListScreen(
    viewModel: ReadingViewModel,
    onAddClick: () -> Unit
) {
    val readings by viewModel.readings.collectAsState()


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.surface,
            floatingActionButton = {
                FloatingActionButton(onClick = onAddClick) {
                    Text("+")
                }
            }
        ) { padding ->

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                if (readings.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp)
                        ) {
                            Text(
                                text = "Aún no hay mediciones.\nPresiona + para agregar.",
                                modifier = Modifier.align(Alignment.Center),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                } else {
                    items(readings) { item ->
                        ReadingRow(item)
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
private fun ReadingRow(item: ReadingEntity) {
    // Layout tipo “tabla” como la referencia:
    // [icon + TYPE] [VALUE centrado] [DATE derecha]
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Columna izquierda: icono + tipo (ancho fijo)
        Row(
            modifier = Modifier.width(110.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = meterIcon(item.type),
                contentDescription = item.type
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = item.type.uppercase(),
                fontWeight = FontWeight.Medium
            )
        }

        // Columna centro: valor (ancho fijo y centrado)
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = formatNumber(item.value))
        }

        // Columna derecha: fecha (ancho fijo, alineado a la derecha)
        Box(
            modifier = Modifier.width(110.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(text = item.date)
        }
    }
}

private fun meterIcon(type: String): ImageVector {
    return when (type.uppercase()) {
        "AGUA", "WATER" -> Icons.Outlined.WaterDrop
        "LUZ", "LIGHT" -> Icons.Outlined.Bolt
        "GAS" -> Icons.Outlined.LocalGasStation
        else -> Icons.Outlined.WaterDrop
    }
}

private fun formatNumber(value: Int): String {
    val nf = NumberFormat.getInstance(Locale("es", "CL"))
    return nf.format(value)
}


