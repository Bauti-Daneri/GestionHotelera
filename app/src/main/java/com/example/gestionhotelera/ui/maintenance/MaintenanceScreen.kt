package com.example.gestionhotelera.ui.maintenance

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gestionhotelera.domain.model.TicketCategory
import com.example.gestionhotelera.ui.components.MaintenanceTicketCard
import com.example.gestionhotelera.ui.theme.PrimaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaintenanceScreen(
    viewModel: MaintenanceViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showCreateTicketSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mantenimiento") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryBlue,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showCreateTicketSheet = true },
                containerColor = PrimaryBlue
            ) {
                Icon(Icons.Rounded.Add, contentDescription = "Nuevo Ticket")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            MaintenanceContent(
                uiState = uiState,
                onFilterChange = { viewModel.setFilter(it) },
                onStatusChange = { id, status -> viewModel.updateTicketStatus(id, status) }
            )
        }
    }

    if (showCreateTicketSheet) {
        CreateTicketBottomSheet(
            onDismiss = { showCreateTicketSheet = false },
            onCreate = { roomId, category, description ->
                viewModel.createTicket(roomId, category, description)
                showCreateTicketSheet = false
            }
        )
    }
}

@Composable
fun MaintenanceContent(
    uiState: MaintenanceUiState,
    onFilterChange: (TicketCategory?) -> Unit,
    onStatusChange: (String, com.example.gestionhotelera.domain.model.TicketStatus) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = if (uiState.filter == null) 0 else TicketCategory.values().indexOf(uiState.filter) + 1,
            edgePadding = 16.dp,
            divider = {}
        ) {
            Tab(
                selected = uiState.filter == null,
                onClick = { onFilterChange(null) },
                text = { Text("Todos") }
            )
            TicketCategory.values().forEach { category ->
                Tab(
                    selected = uiState.filter == category,
                    onClick = { onFilterChange(category) },
                    text = { Text(category.name) }
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(uiState.tickets) { ticket ->
                MaintenanceTicketCard(
                    ticket = ticket,
                    onStatusChange = { status -> onStatusChange(ticket.id, status) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTicketBottomSheet(
    onDismiss: () -> Unit,
    onCreate: (String, TicketCategory, String) -> Unit
) {
    var roomId by remember { mutableStateOf("") }
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
            Text("Nuevo Reporte de Mantenimiento", style = MaterialTheme.typography.titleLarge)
            
            OutlinedTextField(
                value = roomId,
                onValueChange = { roomId = it },
                label = { Text("Número de Habitación") },
                placeholder = { Text("Ej: 203") },
                modifier = Modifier.fillMaxWidth()
            )

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
                label = { Text("Descripción del Problema") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            
            Button(
                onClick = { onCreate(roomId, category, description) },
                modifier = Modifier.fillMaxWidth(),
                enabled = roomId.isNotBlank() && description.isNotBlank()
            ) {
                Text("Crear Ticket")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
