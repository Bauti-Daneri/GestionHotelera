package com.example.gestionhotelera.ui.maintenance.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.gestionhotelera.domain.model.TicketCategory
import com.example.gestionhotelera.domain.model.displayName
import com.example.gestionhotelera.ui.camera.CameraScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTicketBottomSheet(
    onDismiss: () -> Unit,
    onCreate: (String, TicketCategory, String, String?) -> Unit
) {
    var roomId by remember { mutableStateOf("") }
    var category by remember { mutableStateOf(TicketCategory.PLUMBING) }
    var description by remember { mutableStateOf("") }
    var capturedUri by remember { mutableStateOf<String?>(null) }
    var showCamera by remember { mutableStateOf(false) }

    if (showCamera) {
        Box(modifier = Modifier.fillMaxSize()) {
            CameraScreen(
                onImageCaptured = { uri ->
                    capturedUri = uri.toString()
                    showCamera = false
                },
                onError = { /* Handle error */ }
            )
            IconButton(
                onClick = { showCamera = false },
                modifier = Modifier.padding(16.dp),
                colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add, 
                    contentDescription = "Cerrar", 
                    modifier = Modifier.rotate(45f)
                )
            }
        }
        return
    }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Nuevo Reporte de Mantenimiento", style = MaterialTheme.typography.titleLarge)
            
            OutlinedTextField(
                value = roomId,
                onValueChange = { roomId = it },
                label = { Text("Número de Habitación") },
                placeholder = { Text("Ej: 203") },
                modifier = Modifier.fillMaxWidth()
            )

            Text("Categoría", style = MaterialTheme.typography.labelLarge)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TicketCategory.values().forEach { cat ->
                    FilterChip(
                        selected = category == cat,
                        onClick = { category = cat },
                        label = { Text(cat.displayName) }
                    )
                }
            }
            
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción del Problema") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            // Sección de Foto
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Evidencia Fotográfica", style = MaterialTheme.typography.labelLarge)
                if (capturedUri != null) {
                    Box(modifier = Modifier.size(100.dp)) {
                        coil3.compose.AsyncImage(
                            model = capturedUri,
                            contentDescription = "Preview",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(MaterialTheme.shapes.medium),
                            contentScale = ContentScale.Crop
                        )
                        IconButton(
                            onClick = { capturedUri = null },
                            modifier = Modifier.align(Alignment.TopEnd).size(24.dp),
                            colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Add, 
                                contentDescription = "Eliminar", 
                                modifier = Modifier.rotate(45f).scale(0.7f)
                            )
                        }
                    }
                } else {
                    OutlinedButton(
                        onClick = { showCamera = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Rounded.CameraAlt, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Tomar Foto")
                    }
                }
            }
            
            Button(
                onClick = { onCreate(roomId, category, description, capturedUri) },
                modifier = Modifier.fillMaxWidth(),
                enabled = roomId.isNotBlank() && description.isNotBlank()
            ) {
                Text("Crear Ticket")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
