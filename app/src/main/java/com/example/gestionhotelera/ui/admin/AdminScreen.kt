package com.example.gestionhotelera.ui.admin

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
import com.example.gestionhotelera.domain.model.UserRole
import com.example.gestionhotelera.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    viewModel: AdminViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showAddUserSheet by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Usuarios", "Habitaciones")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Administración") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            if (selectedTab == 0) {
                FloatingActionButton(
                    onClick = { showAddUserSheet = true },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(Icons.Rounded.Add, contentDescription = "Nuevo Usuario")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            AdminContent(
                uiState = uiState,
                selectedTab = selectedTab,
                tabs = tabs,
                onTabSelected = { selectedTab = it },
                onDeleteUser = { viewModel.deleteUser(it) }
            )
        }
    }

    if (showAddUserSheet) {
        EmployeeBottomSheet(
            onDismiss = { showAddUserSheet = false },
            onSave = { name, email, role, dept, phone ->
                viewModel.saveUser(name, email, role, dept, phone)
                showAddUserSheet = false
            }
        )
    }
}

@Composable
fun AdminContent(
    uiState: AdminUiState,
    selectedTab: Int,
    tabs: List<String>,
    onTabSelected: (Int) -> Unit,
    onDeleteUser: (com.example.gestionhotelera.domain.model.User) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            HotelHeaderCard(name = uiState.hotel?.name ?: "Hotel Plaza Central")
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MetricCard(
                    title = "Tickets Abiertos",
                    value = uiState.openTicketsCount.toString(),
                    icon = Icons.Rounded.ConfirmationNumber,
                    modifier = Modifier.weight(1f)
                )
                MetricCard(
                    title = "Ocupación",
                    value = "${uiState.occupancyRate}%",
                    icon = Icons.Rounded.PieChart,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { onTabSelected(index) },
                        text = { Text(title) }
                    )
                }
            }
        }

        if (selectedTab == 0) {
            items(uiState.users) { user ->
                EmployeeCard(
                    user = user,
                    onEdit = { /* Implement edit */ },
                    onDelete = { onDeleteUser(user) }
                )
            }
        } else {
            items(uiState.rooms) { room ->
                RoomCard(
                    room = room,
                    onClick = { /* Implement room detail */ }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeBottomSheet(
    onDismiss: () -> Unit,
    onSave: (String, String, UserRole, String, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var role by remember { mutableStateOf(UserRole.HOUSEKEEPING) }
    var dept by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Nuevo Usuario", style = MaterialTheme.typography.titleLarge)
            
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre Completo") },
                modifier = Modifier.fillMaxWidth()
            )
            
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = dept,
                onValueChange = { dept = it },
                label = { Text("Departamento") },
                modifier = Modifier.fillMaxWidth()
            )

            Text("Rol", style = MaterialTheme.typography.labelLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                UserRole.values().forEach { userRole ->
                    FilterChip(
                        selected = role == userRole,
                        onClick = { role = userRole },
                        label = { Text(userRole.name) }
                    )
                }
            }

            Button(
                onClick = { onSave(name, email, role, dept, phone) },
                modifier = Modifier.fillMaxWidth(),
                enabled = name.isNotBlank() && email.isNotBlank()
            ) {
                Text("Guardar")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
