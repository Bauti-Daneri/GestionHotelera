package com.example.gestionhotelera.ui.maintenance

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gestionhotelera.domain.model.*
import com.example.gestionhotelera.ui.components.MaintenanceTicketCard
import com.example.gestionhotelera.ui.components.ScreenTitle
import com.example.gestionhotelera.ui.maintenance.components.CreateTicketBottomSheet
import com.example.gestionhotelera.ui.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaintenanceScreen(
    viewModel: MaintenanceViewModel = hiltViewModel(),
    onNavigateToImageViewer: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showCreateTicketSheet by remember { mutableStateOf(false) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = padding.calculateBottomPadding())
        ) {
            ScreenTitle(
                title = "Mantenimiento",
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // Fixed Top Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ScrollableTabRow(
                    selectedTabIndex = if (uiState.filter == null) 0 else TicketCategory.values().indexOf(uiState.filter) + 1,
                    edgePadding = 0.dp,
                    divider = {}
                ) {
                    Tab(
                        selected = uiState.filter == null,
                        onClick = { viewModel.setFilter(null) },
                        text = { Text("TODOS") }
                    )
                    TicketCategory.values().forEach { category ->
                        Tab(
                            selected = uiState.filter == category,
                            onClick = { viewModel.setFilter(category) },
                            text = { Text(category.displayName.uppercase()) }
                        )
                    }
                }

                Button(
                    onClick = { showCreateTicketSheet = true },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Icon(Icons.Rounded.Add, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Nuevo Reporte")
                }
            }

            // Scrollable List
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
                items(uiState.tickets) { ticket ->
                    MaintenanceTicketCard(
                        ticket = ticket,
                        onStatusChange = { status -> viewModel.updateTicketStatus(ticket.id, status) },
                        onImageClick = { url -> onNavigateToImageViewer(url) }
                    )
                }
            }
        }
    }

    if (showCreateTicketSheet) {
        CreateTicketBottomSheet(
            onDismiss = { showCreateTicketSheet = false },
            onCreate = { roomId, category, description, imageUrl ->
                viewModel.createTicket(roomId, category, description, imageUrl)
                showCreateTicketSheet = false
            }
        )
    }
}
