package com.hotelops.presentation.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hotelops.domain.model.Room
import com.hotelops.domain.model.RoomStatus
import com.hotelops.domain.model.User
import com.hotelops.domain.model.UserRole

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    currentUser: User?,
    viewModel: AdminViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(currentUser?.hotelId) {
        currentUser?.hotelId?.let { viewModel.loadData(it) }
    }

    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Usuarios", "Habitaciones")

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Administración") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) }
                )
            }
        }
        when (selectedTab) {
            0 -> UsersTab(
                users = state.users,
                isLoading = state.isLoading,
                onAddUser = { viewModel.showAddUserDialog() },
                onDeleteUser = { viewModel.deleteUser(it) }
            )
            1 -> RoomsTab(
                rooms = state.rooms,
                isLoading = state.isLoading,
                onAddRoom = { viewModel.showAddRoomDialog() },
                onDeleteRoom = { viewModel.deleteRoom(it) }
            )
        }
    }

    // Add User Dialog
    if (state.showAddUserDialog) {
        AddUserDialog(
            onDismiss = { viewModel.hideDialogs() },
            onConfirm = { name, email, password, role ->
                viewModel.addUser(name, email, password, role)
            }
        )
    }

    // Add Room Dialog
    if (state.showAddRoomDialog) {
        AddRoomDialog(
            onDismiss = { viewModel.hideDialogs() },
            onConfirm = { number, floor, type ->
                viewModel.addRoom(number, floor, type)
            }
        )
    }
}

@Composable
private fun UsersTab(
    users: List<User>,
    isLoading: Boolean,
    onAddUser: () -> Unit,
    onDeleteUser: (User) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(users, key = { it.id }) { user ->
                    UserCard(user = user, onDelete = { onDeleteUser(user) })
                }
            }
        }
        FloatingActionButton(
            onClick = onAddUser,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.PersonAdd, contentDescription = "Agregar usuario")
        }
    }
}

@Composable
private fun UserCard(user: User, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(user.name, style = MaterialTheme.typography.titleSmall)
                Text(user.email, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                AssistChip(
                    onClick = {},
                    label = { Text(user.role.toDisplayName()) }
                )
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar",
                    tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
private fun RoomsTab(
    rooms: List<Room>,
    isLoading: Boolean,
    onAddRoom: () -> Unit,
    onDeleteRoom: (Room) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(rooms, key = { it.id }) { room ->
                    RoomCard(room = room, onDelete = { onDeleteRoom(room) })
                }
            }
        }
        FloatingActionButton(
            onClick = onAddRoom,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Agregar habitación")
        }
    }
}

@Composable
private fun RoomCard(room: Room, onDelete: () -> Unit) {
    val statusColor = when (room.status) {
        RoomStatus.CLEAN -> MaterialTheme.colorScheme.tertiary
        RoomStatus.DIRTY -> MaterialTheme.colorScheme.error
        RoomStatus.CLEANING -> MaterialTheme.colorScheme.secondary
        RoomStatus.INSPECTING -> MaterialTheme.colorScheme.primary
        RoomStatus.OUT_OF_SERVICE -> MaterialTheme.colorScheme.outline
    }
    val statusLabel = when (room.status) {
        RoomStatus.CLEAN -> "Limpia"
        RoomStatus.DIRTY -> "Sucia"
        RoomStatus.CLEANING -> "En limpieza"
        RoomStatus.INSPECTING -> "Inspeccionando"
        RoomStatus.OUT_OF_SERVICE -> "Fuera de servicio"
    }
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Habitación ${room.roomNumber}", style = MaterialTheme.typography.titleSmall)
                Text("Piso ${room.floor} · ${room.type.name}", style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                AssistChip(
                    onClick = {},
                    label = { Text(statusLabel) },
                    colors = AssistChipDefaults.assistChipColors(labelColor = statusColor)
                )
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar",
                    tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddUserDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, String, UserRole) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf(UserRole.HOUSEKEEPING) }
    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Usuario") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = name, onValueChange = { name = it },
                    label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(value = email, onValueChange = { email = it },
                    label = { Text("Email") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(value = password, onValueChange = { password = it },
                    label = { Text("Contraseña") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
                    OutlinedTextField(
                        value = selectedRole.toDisplayName(),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Rol") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        UserRole.entries.forEach { role ->
                            DropdownMenuItem(
                                text = { Text(role.toDisplayName()) },
                                onClick = { selectedRole = role; expanded = false }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(name, email, password, selectedRole) }) { Text("Agregar") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddRoomDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, Int, String) -> Unit
) {
    var roomNumber by remember { mutableStateOf("") }
    var floor by remember { mutableStateOf("1") }
    var type by remember { mutableStateOf("SINGLE") }
    var expanded by remember { mutableStateOf(false) }
    val types = listOf("SINGLE", "DOUBLE", "SUITE")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Habitación") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = roomNumber, onValueChange = { roomNumber = it },
                    label = { Text("Número") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                OutlinedTextField(value = floor, onValueChange = { floor = it },
                    label = { Text("Piso") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
                    OutlinedTextField(
                        value = type,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Tipo") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        types.forEach { t ->
                            DropdownMenuItem(text = { Text(t) }, onClick = { type = t; expanded = false })
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(roomNumber, floor.toIntOrNull() ?: 1, type) }) { Text("Agregar") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}
