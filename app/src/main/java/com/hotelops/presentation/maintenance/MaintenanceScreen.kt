package com.hotelops.presentation.maintenance

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hotelops.domain.model.MaintenanceTicket
import com.hotelops.domain.model.Room
import com.hotelops.domain.model.TicketCategory
import com.hotelops.domain.model.TicketStatus
import com.hotelops.domain.model.User
import com.hotelops.presentation.camera.CameraScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaintenanceScreen(
    currentUser: User?,
    viewModel: MaintenanceViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(currentUser?.hotelId) {
        currentUser?.hotelId?.let { viewModel.loadData(it) }
    }

    val filteredTickets = if (state.filterStatus != null)
        state.tickets.filter { it.status == state.filterStatus }
    else state.tickets

    val statusFilters = listOf(null to "Todos") + TicketStatus.entries.map { it to it.displayName() }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Mantenimiento") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )

        // Stats
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatCard("Pendientes", state.tickets.count { it.status == TicketStatus.PENDING },
                MaterialTheme.colorScheme.errorContainer, Modifier.weight(1f))
            StatCard("En progreso", state.tickets.count { it.status == TicketStatus.IN_PROGRESS },
                MaterialTheme.colorScheme.secondaryContainer, Modifier.weight(1f))
            StatCard("Completados", state.tickets.count { it.status == TicketStatus.COMPLETED },
                MaterialTheme.colorScheme.tertiaryContainer, Modifier.weight(1f))
        }

        // Filter chips
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(statusFilters) { (status, label) ->
                FilterChip(
                    selected = state.filterStatus == status,
                    onClick = { viewModel.setFilter(status) },
                    label = { Text(label) }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredTickets, key = { it.id }) { ticket ->
                        TicketCard(ticket = ticket, onStatusChange = { viewModel.updateStatus(ticket.id, it) })
                    }
                }
            }
            FloatingActionButton(
                onClick = { viewModel.showCreateDialog() },
                modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Nuevo ticket")
            }
        }
    }

    if (state.showCamera) {
        CameraScreen(
            onImageCaptured = { viewModel.onImageCaptured(it) },
            onDismiss = { viewModel.hideCamera() }
        )
    }

    if (state.showCreateDialog) {
        CreateTicketDialog(
            rooms = state.rooms,
            capturedImageUri = state.capturedImageUri,
            onOpenCamera = { viewModel.showCamera() },
            onDismiss = { viewModel.hideDialog() },
            onConfirm = { room, title, desc, category, priority ->
                viewModel.createTicket(
                    hotelId = currentUser?.hotelId ?: "",
                    room = room,
                    title = title,
                    description = desc,
                    category = category,
                    priority = priority,
                    reportedByName = currentUser?.name ?: "",
                    reportedById = currentUser?.id ?: ""
                )
            }
        )
    }
}

@Composable
private fun StatCard(label: String, count: Int, containerColor: androidx.compose.ui.graphics.Color, modifier: Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = containerColor)) {
        Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(count.toString(), style = MaterialTheme.typography.headlineSmall)
            Text(label, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
private fun TicketCard(ticket: MaintenanceTicket, onStatusChange: (TicketStatus) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val priorityColor = when (ticket.priority) {
        "ALTA" -> MaterialTheme.colorScheme.error
        "MEDIA" -> MaterialTheme.colorScheme.secondary
        else -> MaterialTheme.colorScheme.outline
    }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(ticket.title, style = MaterialTheme.typography.titleSmall, modifier = Modifier.weight(1f))
                Box {
                    AssistChip(onClick = { expanded = true }, label = { Text(ticket.status.displayName()) })
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        TicketStatus.entries.filter { it != ticket.status }.forEach { status ->
                            DropdownMenuItem(
                                text = { Text(status.displayName()) },
                                onClick = { onStatusChange(status); expanded = false }
                            )
                        }
                    }
                }
            }
            ticket.imageUrl?.let { url ->
                AsyncImage(
                    model = url,
                    contentDescription = "Evidencia",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
            }
            Text(ticket.description, style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                SuggestionChip(onClick = {}, label = { Text("Hab. ${ticket.roomNumber}") })
                SuggestionChip(onClick = {}, label = { Text(ticket.category.displayName()) })
                Text(ticket.priority, color = priorityColor, style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.align(Alignment.CenterVertically))
            }
            Text("Reportado por: ${ticket.reportedByName}", style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateTicketDialog(
    rooms: List<Room>,
    capturedImageUri: android.net.Uri?,
    onOpenCamera: () -> Unit,
    onDismiss: () -> Unit,
    onConfirm: (Room, String, String, TicketCategory, String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedRoom by remember { mutableStateOf(rooms.firstOrNull()) }
    var selectedCategory by remember { mutableStateOf(TicketCategory.OTHER) }
    var selectedPriority by remember { mutableStateOf("MEDIA") }
    var roomExpanded by remember { mutableStateOf(false) }
    var categoryExpanded by remember { mutableStateOf(false) }
    var priorityExpanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nuevo Ticket") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = title, onValueChange = { title = it },
                    label = { Text("Título *") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(value = description, onValueChange = { description = it },
                    label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth(), maxLines = 3)

                // Cámara
                if (capturedImageUri != null) {
                    AsyncImage(
                        model = capturedImageUri,
                        contentDescription = "Foto capturada",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .clip(MaterialTheme.shapes.small)
                            .clickable { onOpenCamera() },
                        contentScale = ContentScale.Crop
                    )
                } else {
                    OutlinedButton(
                        onClick = onOpenCamera,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.CameraAlt, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Tomar Foto")
                    }
                }

                ExposedDropdownMenuBox(expanded = roomExpanded, onExpandedChange = { roomExpanded = it }) {
                    OutlinedTextField(
                        value = selectedRoom?.let { "Hab. ${it.roomNumber}" } ?: "Seleccionar habitación",
                        onValueChange = {}, readOnly = true,
                        label = { Text("Habitación") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(roomExpanded) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(expanded = roomExpanded, onDismissRequest = { roomExpanded = false }) {
                        rooms.forEach { room ->
                            DropdownMenuItem(
                                text = { Text("Hab. ${room.roomNumber} - Piso ${room.floor}") },
                                onClick = { selectedRoom = room; roomExpanded = false }
                            )
                        }
                    }
                }

                ExposedDropdownMenuBox(expanded = categoryExpanded, onExpandedChange = { categoryExpanded = it }) {
                    OutlinedTextField(
                        value = selectedCategory.displayName(), onValueChange = {}, readOnly = true,
                        label = { Text("Categoría") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(categoryExpanded) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(expanded = categoryExpanded, onDismissRequest = { categoryExpanded = false }) {
                        TicketCategory.entries.forEach { cat ->
                            DropdownMenuItem(text = { Text(cat.displayName()) },
                                onClick = { selectedCategory = cat; categoryExpanded = false })
                        }
                    }
                }

                ExposedDropdownMenuBox(expanded = priorityExpanded, onExpandedChange = { priorityExpanded = it }) {
                    OutlinedTextField(
                        value = selectedPriority, onValueChange = {}, readOnly = true,
                        label = { Text("Prioridad") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(priorityExpanded) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(expanded = priorityExpanded, onDismissRequest = { priorityExpanded = false }) {
                        listOf("ALTA", "MEDIA", "BAJA").forEach { p ->
                            DropdownMenuItem(text = { Text(p) }, onClick = { selectedPriority = p; priorityExpanded = false })
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                selectedRoom?.let { onConfirm(it, title, description, selectedCategory, selectedPriority) }
            }) { Text("Crear") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}

private fun TicketStatus.displayName() = when (this) {
    TicketStatus.PENDING -> "Pendiente"
    TicketStatus.IN_PROGRESS -> "En progreso"
    TicketStatus.COMPLETED -> "Completado"
}

private fun TicketCategory.displayName() = when (this) {
    TicketCategory.PLUMBING -> "Plomería"
    TicketCategory.ELECTRICAL -> "Eléctrico"
    TicketCategory.HVAC -> "Climatización"
    TicketCategory.FURNITURE -> "Mobiliario"
    TicketCategory.OTHER -> "Otro"
}
