package com.example.gestionhotelera.ui.housekeeping

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gestionhotelera.domain.model.*
import com.example.gestionhotelera.ui.components.*
import com.example.gestionhotelera.ui.housekeeping.components.ReportMaintenanceBottomSheet
import com.example.gestionhotelera.ui.housekeeping.components.RoomDetailBottomSheet
import com.example.gestionhotelera.ui.theme.DestructiveRed
import com.example.gestionhotelera.ui.theme.PrimaryBlue
import com.example.gestionhotelera.ui.theme.SuccessGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HousekeepingScreen(
    viewModel: HousekeepingViewModel = hiltViewModel(),
    onNavigateToImageViewer: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var selectedRoomWithHousekeepers by remember { mutableStateOf<RoomWithHousekeepers?>(null) }
    var showReportMaintenance by remember { mutableStateOf(false) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = padding.calculateBottomPadding())
        ) {
            ScreenTitle(
                title = "Limpieza",
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // Fixed Top Header (No TopBar as requested)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
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

            // Scrollable Content
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 120.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(uiState.rooms) { roomWithHousekeepers ->
                    RoomCard(
                        room = roomWithHousekeepers.room,
                        assignedEmployees = if (uiState.userRole == UserRole.ADMIN) {
                            roomWithHousekeepers.housekeepers
                        } else {
                            emptyList()
                        },
                        onClick = { selectedRoomWithHousekeepers = roomWithHousekeepers }
                    )
                }
            }
        }
    }

    selectedRoomWithHousekeepers?.let { roomWithHousekeepers ->
        val room = roomWithHousekeepers.room
        if (showReportMaintenance) {
            ReportMaintenanceBottomSheet(
                onDismiss = { showReportMaintenance = false },
                onReport = { category, description, imageUrl ->
                    viewModel.reportMaintenance(room.id, category, description, imageUrl)
                    showReportMaintenance = false
                    selectedRoomWithHousekeepers = null
                },
                onImageClick = onNavigateToImageViewer
            )
        } else {
            RoomDetailBottomSheet(
                room = room,
                onDismiss = { selectedRoomWithHousekeepers = null },
                onStatusChange = { status ->
                    viewModel.updateRoomStatus(room.id, status)
                    selectedRoomWithHousekeepers = null
                },
                onReportMaintenance = { showReportMaintenance = true }
            )
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
