package com.example.gestionhotelera.ui.housekeeping

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gestionhotelera.domain.model.Room
import com.example.gestionhotelera.domain.model.RoomStatus
import com.example.gestionhotelera.domain.model.TicketCategory
import com.example.gestionhotelera.ui.components.*
import com.example.gestionhotelera.ui.theme.DestructiveRed
import com.example.gestionhotelera.ui.theme.PrimaryBlue
import com.example.gestionhotelera.ui.theme.SuccessGreen
import com.example.gestionhotelera.ui.theme.WarningOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HousekeepingScreen(
    viewModel: HousekeepingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var selectedRoom by remember { mutableStateOf<Room?>(null) }
    var showReportMaintenance by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Limpieza") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryBlue,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            HousekeepingContent(
                uiState = uiState,
                onRoomClick = { selectedRoom = it }
            )
        }
    }

    selectedRoom?.let { room ->
        if (showReportMaintenance) {
            ReportMaintenanceBottomSheet(
                onDismiss = { showReportMaintenance = false },
                onReport = { category, description ->
                    viewModel.reportMaintenance(room.id, category, description)
                    showReportMaintenance = false
                    selectedRoom = null
                }
            )
        } else {
            RoomDetailBottomSheet(
                room = room,
                onDismiss = { selectedRoom = null },
                onStatusChange = { status ->
                    viewModel.updateRoomStatus(room.id, status)
                    selectedRoom = null
                },
                onReportMaintenance = { showReportMaintenance = true }
            )
        }
    }
}

@Composable
fun HousekeepingContent(
    uiState: HousekeepingUiState,
    onRoomClick: (Room) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatusMetricCard(
                    title = "Sucias",
                    value = uiState.dirtyCount.toString(),
                    color = DestructiveRed,
                    modifier = Modifier.weight(1f)
                )
                StatusMetricCard(
                    title = "En Proceso",
                    value = uiState.inProgressCount.toString(),
                    color = PrimaryBlue,
                    modifier = Modifier.weight(1f)
                )
                StatusMetricCard(
                    title = "Limpias",
                    value = uiState.cleanCount.toString(),
                    color = SuccessGreen,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        items(uiState.rooms) { room ->
            RoomCard(room = room, onClick = { onRoomClick(room) })
        }
    }
}

@Composable
fun StatusMetricCard(
    title: String,
    value: String,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = value, style = MaterialTheme.typography.headlineMedium, color = color)
            Text(text = title, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomDetailBottomSheet(
    room: Room,
    onDismiss: () -> Unit,
    onStatusChange: (RoomStatus) -> Unit,
    onReportMaintenance: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Habitación ${room.number}", style = MaterialTheme.typography.titleLarge)
            
            Text("Estado de la Habitación", style = MaterialTheme.typography.labelLarge)
            
            Button(
                onClick = { onStatusChange(RoomStatus.DIRTY) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = DestructiveRed.copy(alpha = 0.1f), contentColor = DestructiveRed)
            ) { Text("Sucia / Requiere Limpieza") }
            
            Button(
                onClick = { onStatusChange(RoomStatus.IN_PROGRESS) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue.copy(alpha = 0.1f), contentColor = PrimaryBlue)
            ) { Text("En Proceso de Limpieza") }
            
            Button(
                onClick = { onStatusChange(RoomStatus.CLEAN) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen.copy(alpha = 0.1f), contentColor = SuccessGreen)
            ) { Text("Limpia / Disponible") }
            
            Divider()
            
            TextButton(
                onClick = onReportMaintenance,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Rounded.ReportProblem, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Reportar Problema de Mantenimiento")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportMaintenanceBottomSheet(
    onDismiss: () -> Unit,
    onReport: (TicketCategory, String) -> Unit
) {
    var category by remember { mutableStateOf(TicketCategory.PLUMBING) }
    var description by remember { mutableStateOf("") }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Reportar Problema", style = MaterialTheme.typography.titleLarge)
            
            Text("Categoría", style = MaterialTheme.typography.labelLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TicketCategory.values().forEach { cat ->
                    FilterChip(
                        selected = category == cat,
                        onClick = { category = cat },
                        label = { Text(cat.name) }
                    )
                }
            }
            
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción del problema") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            
            Button(
                onClick = { onReport(category, description) },
                modifier = Modifier.fillMaxWidth(),
                enabled = description.isNotBlank()
            ) {
                Text("Crear Ticket")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
