package com.example.registromedidores.ui.screens

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.registromedidores.ui.viewmodel.ReadingViewModel

@Composable
fun AddReadingScreen(
    viewModel: ReadingViewModel,
    onBack: () -> Unit
) {
    val today = remember {
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
    }

    var medidorText by remember { mutableStateOf("") }
    var fechaText by remember { mutableStateOf(today) }
    var tipo by remember { mutableStateOf("AGUA") } // como referencia

    //  Validación
    val medidorValue = medidorText.trim().toIntOrNull()
    val isMedidorValid = (medidorValue != null && medidorValue > 0)
    val canSave = isMedidorValid && tipo.isNotBlank()

    // Para mostrar error solo cuando el usuario ya escribió algo
    val showMedidorError = medidorText.isNotBlank() && !isMedidorValid

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Registro Medidor",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(14.dp)) {

                OutlinedTextField(
                    value = medidorText,
                    onValueChange = { newText ->
                        //  Solo deja dígitos (y vacío)
                        medidorText = newText.filter { it.isDigit() }
                    },
                    label = { Text("Medidor") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = showMedidorError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                if (showMedidorError) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Ingresa un valor mayor a 0",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = fechaText,
                    onValueChange = { fechaText = it },
                    label = { Text("Fecha") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Medidor de:",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(6.dp))

        RadioRow(text = "Agua", selected = tipo == "AGUA") { tipo = "AGUA" }
        RadioRow(text = "Luz", selected = tipo == "LUZ") { tipo = "LUZ" }
        RadioRow(text = "Gas", selected = tipo == "GAS") { tipo = "GAS" }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                // Ya es válido por canSave
                viewModel.addReading(tipo, medidorValue!!)
                onBack()
            },
            enabled = canSave,
            shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp),
            modifier = Modifier
                .wrapContentWidth()
                .height(48.dp)
                .padding(horizontal = 8.dp)
        ) {
            Text("Registrar medición")
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(onClick = onBack) {
            Text("Volver")
        }
    }
}

@Composable
private fun RadioRow(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(selected = selected, onClick = onSelect)
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onSelect)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text)
    }
}

