package com.example.gestionhotelera.ui.admin.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.gestionhotelera.domain.model.RoomStatus
import com.example.gestionhotelera.domain.model.displayName

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun RoomBottomSheet(
    onDismiss: () -> Unit,
    onSave: (String, String, String, RoomStatus) -> Unit
) {
    var number by remember { mutableStateOf("") }
    var floor by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("Single") }
    var status by remember { mutableStateOf(RoomStatus.CLEAN) }
    val types = listOf("Single", "Double", "Suite")

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Nueva Habitación", style = MaterialTheme.typography.titleLarge)
            
            OutlinedTextField(
                value = number,
                onValueChange = { number = it },
                label = { Text("Número de Habitación") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            
            OutlinedTextField(
                value = floor,
                onValueChange = { floor = it },
                label = { Text("Piso") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Text("Tipo", style = MaterialTheme.typography.labelLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                types.forEach { t ->
                    FilterChip(
                        selected = type == t,
                        onClick = { type = t },
                        label = { Text(t) }
                    )
                }
            }

            Text("Estado Inicial", style = MaterialTheme.typography.labelLarge)
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                RoomStatus.entries.forEach { s ->
                    FilterChip(
                        selected = status == s,
                        onClick = { status = s },
                        label = { 
                            Text(
                                text = if (s == RoomStatus.MAINTENANCE) "Mant." else s.displayName,
                                maxLines = 1
                            ) 
                        }
                    )
                }
            }

            Button(
                onClick = { onSave(number, floor, type, status) },
                modifier = Modifier.fillMaxWidth(),
                enabled = number.isNotBlank() && floor.isNotBlank()
            ) {
                Text("Guardar")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
