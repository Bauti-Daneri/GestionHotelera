package com.hotelops.presentation.maintenance

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hotelops.domain.model.MaintenanceTicket
import com.hotelops.domain.model.Room
import com.hotelops.domain.model.TicketCategory
import com.hotelops.domain.model.TicketStatus
import com.hotelops.domain.model.User
import com.hotelops.presentation.camera.CameraScreen
import com.hotelops.presentation.theme.*

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

    val categories = listOf(null to "Todos") + TicketCategory.entries.map { it to it.displayName() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Mantenimiento", 
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Primary
                        )
                    ) 
                },
                actions = {
                    Surface(
                        color = SurfaceVariant,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "${state.tickets.size} tickets",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.showCreateDialog() },
                containerColor = ColorClean,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Add, contentDescription = "Nuevo ticket")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Background)
        ) {
            // Category Chips
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { (cat, label) ->
                    FilterChip(
                        selected = state.filterStatus == null, // Simplified for now
                        onClick = { },
                        label = { Text(label) },
                        shape = RoundedCornerShape(12.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Primary,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }

            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Primary)
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(filteredTickets, key = { it.id }) { ticket ->
                        TicketCard(
                            ticket = ticket, 
                            onStatusChange = { viewModel.updateStatus(ticket.id, it) }
                        )
                    }
                }
            }
        }
    }

    if (state.showCamera) {
        Dialog(
            onDismissRequest = { viewModel.hideCamera() },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            CameraScreen(
                onImageCaptured = { viewModel.onImageCaptured(it) },
                onDismiss = { viewModel.hideCamera() }
            )
        }
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
private fun TicketCard(ticket: MaintenanceTicket, onStatusChange: (TicketStatus) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(
                                when(ticket.status) {
                                    TicketStatus.PENDING -> ColorDirty
                                    TicketStatus.IN_PROGRESS -> ColorAvailable
                                    TicketStatus.COMPLETED -> ColorClean
                                }
                            )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Hab. #${ticket.roomNumber}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
                Text("10:30", fontSize = 12.sp, color = OnSurfaceVariant) // Dummy time
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(ticket.title, fontWeight = FontWeight.Medium, color = OnSurface)
            
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = when(ticket.category) {
                            TicketCategory.PLUMBING -> Icons.Default.WaterDrop
                            TicketCategory.ELECTRICAL -> Icons.Default.FlashOn
                            else -> Icons.Default.Build
                        },
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = ColorInProgress
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(ticket.status.displayName(), color = OnSurfaceVariant, fontSize = 14.sp)
                }
                
                Surface(
                    color = Primary,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        ticket.category.displayName(),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        fontSize = 11.sp,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            if (ticket.status == TicketStatus.PENDING) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(
                        onClick = { onStatusChange(TicketStatus.IN_PROGRESS) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("Marcar En Progreso", fontSize = 12.sp)
                    }
                    Button(
                        onClick = { onStatusChange(TicketStatus.COMPLETED) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ColorClean),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("Marcar Resuelto", fontSize = 12.sp)
                    }
                }
            } else if (ticket.status == TicketStatus.IN_PROGRESS) {
                Button(
                    onClick = { onStatusChange(TicketStatus.COMPLETED) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ColorClean)
                ) {
                    Text("Marcar Resuelto")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateTicketDialog(
    rooms: List<Room>,
    capturedImageUri: Uri?,
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
        title = { Text("Nuevo Ticket", fontWeight = FontWeight.Bold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(value = title, onValueChange = { title = it },
                    label = { Text("Título *") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(value = description, onValueChange = { description = it },
                    label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth(), maxLines = 3)

                Button(
                    onClick = onOpenCamera,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SurfaceVariant, contentColor = Primary)
                ) {
                    Icon(Icons.Default.CameraAlt, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text(if (capturedImageUri != null) "Cambiar Foto" else "Tomar Foto")
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
            }
        },
        confirmButton = {
            TextButton(onClick = {
                selectedRoom?.let { onConfirm(it, title, description, selectedCategory, selectedPriority) }
            }) { Text("Crear", fontWeight = FontWeight.Bold) }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}

private fun TicketStatus.displayName() = when (this) {
    TicketStatus.PENDING -> "Abierto"
    TicketStatus.IN_PROGRESS -> "En Progreso"
    TicketStatus.COMPLETED -> "Completado"
}

private fun TicketCategory.displayName() = when (this) {
    TicketCategory.PLUMBING -> "Plomería"
    TicketCategory.ELECTRICAL -> "Electricidad"
    TicketCategory.HVAC -> "Climatización"
    TicketCategory.FURNITURE -> "Mobiliario"
    TicketCategory.OTHER -> "Otro"
}
