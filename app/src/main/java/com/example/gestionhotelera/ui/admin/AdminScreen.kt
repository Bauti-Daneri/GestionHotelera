package com.example.gestionhotelera.ui.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gestionhotelera.domain.model.*
import com.example.gestionhotelera.ui.components.*
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    viewModel: AdminViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showAddUserSheet by remember { mutableStateOf(false) }
    var showAddRoomSheet by remember { mutableStateOf(false) }
    var selectedRoomForAssignment by remember { mutableStateOf<Room?>(null) }
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Usuarios", "Habitaciones")

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
                    Icon(Icons.Rounded.PersonAdd, contentDescription = "Nuevo Usuario")
                }
            } else {
                FloatingActionButton(
                    onClick = { showAddRoomSheet = true },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(Icons.Rounded.AddHome, contentDescription = "Nueva Habitación")
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
                onDeleteUser = { viewModel.deleteUser(it) },
                onRoomClick = { selectedRoomForAssignment = it }
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

    if (showAddRoomSheet) {
        RoomBottomSheet(
            onDismiss = { showAddRoomSheet = false },
            onSave = { number, floor, type, status ->
                viewModel.createRoom(number, floor, type, status)
                showAddRoomSheet = false
            }
        )
    }

    if (selectedRoomForAssignment != null) {
        AssignmentBottomSheet(
            room = selectedRoomForAssignment!!,
            allUsers = uiState.users.filter { it.role == UserRole.HOUSEKEEPING },
            assignedUsersFlow = viewModel.getHousekeepersForRoom(selectedRoomForAssignment!!.id),
            onDismiss = { selectedRoomForAssignment = null },
            onAssign = { userId -> viewModel.assignHousekeeper(selectedRoomForAssignment!!.id, userId) },
            onRemove = { userId -> viewModel.removeHousekeeper(selectedRoomForAssignment!!.id, userId) }
        )
    }
}

@Composable
fun AdminContent(
    uiState: AdminUiState,
    selectedTab: Int,
    tabs: List<String>,
    onTabSelected: (Int) -> Unit,
    onDeleteUser: (User) -> Unit,
    onRoomClick: (Room) -> Unit
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
                    onClick = { onRoomClick(room) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomBottomSheet(
    onDismiss: () -> Unit,
    onSave: (String, String, String, RoomStatus) -> Unit
) {
    var number by remember { mutableStateOf("") }
    var floor by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("Single") }
    var status by remember { mutableStateOf(RoomStatus.CLEAN) }
    val types = listOf("Single", "Double", "Suite")

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Nueva Habitación", style = MaterialTheme.typography.titleLarge)
            
            OutlinedTextField(
                value = number,
                onValueChange = { number = it },
                label = { Text("Número de Habitación") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            
            OutlinedTextField(
                value = floor,
                onValueChange = { floor = it },
                label = { Text("Piso") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Text("Tipo", style = MaterialTheme.typography.labelLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                types.forEach { t ->
                    FilterChip(
                        selected = type == t,
                        onClick = { type = t },
                        label = { Text(t) }
                    )
                }
            }

            Text("Estado Inicial", style = MaterialTheme.typography.labelLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                RoomStatus.values().forEach { s ->
                    FilterChip(
                        selected = status == s,
                        onClick = { status = s },
                        label = { Text(s.name) }
                    )
                }
            }

            Button(
                onClick = { onSave(number, floor, type, status) },
                modifier = Modifier.fillMaxWidth(),
                enabled = number.isNotBlank() && floor.isNotBlank()
            ) {
                Text("Guardar")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignmentBottomSheet(
    room: Room,
    allUsers: List<User>,
    assignedUsersFlow: Flow<List<User>>,
    onDismiss: () -> Unit,
    onAssign: (String) -> Unit,
    onRemove: (String) -> Unit
) {
    val assignedUsers by assignedUsersFlow.collectAsState(initial = emptyList())

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Asignar Personal - Habitación ${room.number}", style = MaterialTheme.typography.titleLarge)
            
            Text("Personal Asignado (${assignedUsers.size}/3)", style = MaterialTheme.typography.labelLarge)
            
            assignedUsers.forEach { user ->
                ListItem(
                    headlineContent = { Text(user.name) },
                    supportingContent = { Text(user.email) },
                    trailingContent = {
                        IconButton(onClick = { onRemove(user.id) }) {
                            Icon(Icons.Rounded.Delete, contentDescription = "Quitar", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                )
            }

            if (assignedUsers.size < 3) {
                HorizontalDivider()
                Text("Asignar Empleado", style = MaterialTheme.typography.labelLarge)
                val availableUsers = allUsers.filter { user -> assignedUsers.none { it.id == user.id } }
                
                if (availableUsers.isEmpty()) {
                    Text("No hay más personal de limpieza disponible", style = MaterialTheme.typography.bodyMedium)
                } else {
                    LazyColumn(modifier = Modifier.heightIn(max = 200.dp)) {
                        items(availableUsers) { user ->
                            ListItem(
                                headlineContent = { Text(user.name) },
                                modifier = Modifier.clickable { onAssign(user.id) },
                                trailingContent = { Icon(Icons.Rounded.Add, contentDescription = null) }
                            )
                        }
                    }
                }
            } else {
                Text("Límite de asignación alcanzado", color = MaterialTheme.colorScheme.error)
            }

            Button(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar")
            }
            Spacer(modifier = Modifier.height(16.dp))
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
