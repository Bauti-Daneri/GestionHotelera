package com.example.gestionhotelera.ui.roomservice

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.gestionhotelera.ui.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOrderBottomSheet(
    onDismiss: () -> Unit,
    onCreate: (String, String, Double) -> Unit
) {
    var roomId by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Nuevo Pedido", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            
            OutlinedTextField(
                value = roomId,
                onValueChange = { roomId = it },
                label = { Text("Número de Habitación") },
                placeholder = { Text("Ej: 101") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción del pedido") },
                placeholder = { Text("Ej: Cena completa, Bebidas, etc.") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Precio Total") },
                placeholder = { Text("0.00") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                prefix = { Text("$ ") }
            )
            
            Button(
                onClick = { 
                    val priceDouble = price.toDoubleOrNull() ?: 0.0
                    onCreate(roomId, description, priceDouble)
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = roomId.isNotBlank() && description.isNotBlank() && price.toDoubleOrNull() != null,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
            ) {
                Text("Crear Pedido")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
