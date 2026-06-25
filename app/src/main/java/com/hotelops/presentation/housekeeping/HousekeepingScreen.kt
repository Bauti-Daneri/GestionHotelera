package com.hotelops.presentation.housekeeping

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hotelops.domain.model.Room
import com.hotelops.domain.model.RoomStatus
import com.hotelops.domain.model.User
import com.hotelops.presentation.theme.*

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

    val statusFilters = listOf(null to "Todos") + RoomStatus.entries.map { it to it.displayName() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Housekeeping", 
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Primary
                        )
                    ) 
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Background)
        ) {
            // Stats Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                HousekeepingStatCard(
                    Modifier.weight(1f), 
                    state.rooms.count { it.status == RoomStatus.DIRTY }.toString(), 
                    "Sucias", 
                    ColorDirty
                )
                HousekeepingStatCard(
                    Modifier.weight(1f), 
                    state.rooms.count { it.status == RoomStatus.CLEANING }.toString(), 
                    "En Proceso", 
                    ColorInProgress
                )
                HousekeepingStatCard(
                    Modifier.weight(1f), 
                    state.rooms.count { it.status == RoomStatus.CLEAN }.toString(), 
                    "Limpias", 
                    ColorClean
                )
            }

            // Filter chips
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                items(statusFilters) { (status, label) ->
                    FilterChip(
                        selected = state.filterStatus == status,
                        onClick = { viewModel.setFilter(status) },
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
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredRooms, key = { it.id }) { room ->
                        HousekeepingRoomCard(
                            room = room, 
                            onStatusChange = { viewModel.updateRoomStatus(room.id, it, currentUser?.name ?: "") }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HousekeepingStatCard(modifier: Modifier, count: String, label: String, color: Color) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(24.dp)
                        .background(color, RoundedCornerShape(2.dp))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(count, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = OnSurface)
            }
            Text(label, fontSize = 12.sp, color = OnSurfaceVariant)
        }
    }
}

@Composable
private fun HousekeepingRoomCard(room: Room, onStatusChange: (RoomStatus) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(SurfaceVariant, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Bed, contentDescription = null, tint = Primary)
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("#${room.roomNumber}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    val statusColor = when (room.status) {
                        RoomStatus.DIRTY -> ColorDirty
                        RoomStatus.CLEANING -> ColorInProgress
                        RoomStatus.CLEAN -> ColorClean
                        RoomStatus.AVAILABLE -> ColorAvailable
                        else -> OnSurfaceVariant
                    }
                    
                    Surface(
                        color = statusColor,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            room.status.displayName(),
                            color = Color.White,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                }
                
                Text(
                    room.notes ?: "Desocupada",
                    fontSize = 14.sp,
                    color = OnSurfaceVariant
                )
                
                room.lastCleanedAt?.let {
                    Text(
                        "Última limpieza: ${java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault()).format(it)}",
                        fontSize = 11.sp,
                        color = OnSurfaceVariant.copy(alpha = 0.6f)
                    )
                }
            }
            
            Box {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = null, modifier = Modifier.size(16.dp), tint = OnSurfaceVariant)
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    RoomStatus.entries.forEach { status ->
                        DropdownMenuItem(
                            text = { Text(status.displayName()) },
                            onClick = { 
                                onStatusChange(status)
                                expanded = false 
                            }
                        )
                    }
                }
            }
        }
    }
}

private fun RoomStatus.displayName() = when (this) {
    RoomStatus.DIRTY -> "Sucia"
    RoomStatus.CLEANING -> "En Proceso"
    RoomStatus.CLEAN -> "Limpia"
    RoomStatus.INSPECTING -> "En Inspección"
    RoomStatus.OUT_OF_SERVICE -> "Fuera de Servicio"
    RoomStatus.AVAILABLE -> "Disponible"
}
