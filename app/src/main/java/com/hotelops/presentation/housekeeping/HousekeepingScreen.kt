package com.hotelops.presentation.housekeeping

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hotelops.domain.model.Room
import com.hotelops.domain.model.RoomStatus
import com.hotelops.domain.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HousekeepingScreen(
    currentUser: User?,
    viewModel: HousekeepingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(currentUser?.hotelId) {
        currentUser?.hotelId?.let { viewModel.loadRooms(it) }
    }

    val filteredRooms = if (state.filterStatus != null)
        state.rooms.filter { it.status == state.filterStatus }
    else state.rooms

    val statusFilters = listOf(null to "Todas") + RoomStatus.entries.map { it to it.displayName() }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Limpieza") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )

        // Stats summary
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatCard("Sucias", state.rooms.count { it.status == RoomStatus.DIRTY },
                MaterialTheme.colorScheme.errorContainer, Modifier.weight(1f))
            StatCard("Limpiando", state.rooms.count { it.status == RoomStatus.CLEANING },
                MaterialTheme.colorScheme.secondaryContainer, Modifier.weight(1f))
            StatCard("Limpias", state.rooms.count { it.status == RoomStatus.CLEAN },
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

        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredRooms, key = { it.id }) { room ->
                    HousekeepingRoomCard(
                        room = room,
                        onStatusChange = { newStatus ->
                            viewModel.updateRoomStatus(room.id, newStatus, currentUser?.name ?: "")
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun StatCard(label: String, count: Int, containerColor: androidx.compose.ui.graphics.Color, modifier: Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = containerColor)) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(count.toString(), style = MaterialTheme.typography.headlineSmall)
            Text(label, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
private fun HousekeepingRoomCard(room: Room, onStatusChange: (RoomStatus) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val nextStatuses = when (room.status) {
        RoomStatus.DIRTY -> listOf(RoomStatus.CLEANING)
        RoomStatus.CLEANING -> listOf(RoomStatus.CLEAN, RoomStatus.INSPECTING)
        RoomStatus.INSPECTING -> listOf(RoomStatus.CLEAN, RoomStatus.DIRTY)
        RoomStatus.CLEAN -> listOf(RoomStatus.DIRTY)
        RoomStatus.OUT_OF_SERVICE -> listOf(RoomStatus.DIRTY)
    }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Habitación ${room.roomNumber}", style = MaterialTheme.typography.titleSmall)
                    Text("Piso ${room.floor} · ${room.type.name}", style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                }
                Box {
                    AssistChip(
                        onClick = { expanded = true },
                        label = { Text(room.status.displayName()) }
                    )
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        nextStatuses.forEach { status ->
                            DropdownMenuItem(
                                text = { Text("→ ${status.displayName()}") },
                                onClick = { onStatusChange(status); expanded = false }
                            )
                        }
                    }
                }
            }
            if (room.lastCleanedBy != null) {
                Text("Última limpieza: ${room.lastCleanedBy}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
            }
        }
    }
}

private fun RoomStatus.displayName() = when (this) {
    RoomStatus.DIRTY -> "Sucia"
    RoomStatus.CLEANING -> "En limpieza"
    RoomStatus.CLEAN -> "Limpia"
    RoomStatus.INSPECTING -> "Inspeccionando"
    RoomStatus.OUT_OF_SERVICE -> "Fuera de servicio"
}
