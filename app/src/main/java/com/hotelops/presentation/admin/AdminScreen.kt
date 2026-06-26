package com.hotelops.presentation.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hotelops.domain.model.Room
import com.hotelops.domain.model.RoomStatus
import com.hotelops.domain.model.User
import com.hotelops.domain.model.UserRole
import com.hotelops.presentation.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    currentUser: User?,
    viewModel: AdminViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val colorScheme = MaterialTheme.colorScheme

    LaunchedEffect(currentUser?.hotelId) {
        currentUser?.hotelId?.let { viewModel.loadData(it) }
    }

    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Usuarios", "Habitaciones")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Administración", 
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = colorScheme.headingColor
                        )
                    ) 
                },
                actions = {
                    Box {
                        var menuExpanded by remember { mutableStateOf(false) }
                        IconButton(onClick = { menuExpanded = true }) {
                            Icon(Icons.Default.MoreVert, contentDescription = null)
                        }
                        DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                            DropdownMenuItem(
                                text = { Text("Limpiar Tickets Completados") },
                                onClick = { viewModel.deleteCompletedTickets(); menuExpanded = false }
                            )
                            DropdownMenuItem(
                                text = { Text("Limpiar Pedidos Entregados") },
                                onClick = { viewModel.deleteDeliveredOrders(); menuExpanded = false }
                            )
                        }
                    }
                    AssistChip(
                        onClick = { },
                        label = { Text("Admin", color = colorScheme.primary) },
                        leadingIcon = { Icon(Icons.Default.Settings, contentDescription = null, modifier = Modifier.size(16.dp)) },
                        shape = RoundedCornerShape(16.dp),
                        colors = AssistChipDefaults.assistChipColors(containerColor = colorScheme.surfaceVariant)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(colorScheme.background)
        ) {
            // Hotel Header Card
            HotelHeaderCard(hotelName = "Hotel Plaza Central", hotelId = "HOTEL-DEMO-001")

            // Stats Grid
            AdminStatsGrid(
                roomsCount = state.rooms.size,
                employeesCount = state.users.size,
                ticketsCount = state.tickets.count { it.status != com.hotelops.domain.model.TicketStatus.COMPLETED },
                occupancy = if (state.rooms.isNotEmpty()) 
                    ((state.rooms.count { it.status != RoomStatus.AVAILABLE && it.status != RoomStatus.CLEAN }.toFloat() / state.rooms.size) * 100).toInt()
                    else 0
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Custom Tab Row
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.Transparent,
                contentColor = colorScheme.primary,
                divider = {},
                indicator = { tabPositions ->
                    if (selectedTab < tabPositions.size) {
                        TabRowDefaults.SecondaryIndicator(
                            Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                            color = colorScheme.primary
                        )
                    }
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                tabs.forEachIndexed { index, title ->
                    val selected = selectedTab == index
                    Tab(
                        selected = selected,
                        onClick = { selectedTab = index },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = if (index == 0) Icons.Default.People else Icons.Default.MeetingRoom,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(title, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal)
                            }
                        }
                    )
                }
            }

            if (selectedTab == 0) {
                UsersTab(
                    users = state.users,
                    isLoading = state.isLoading,
                    onAddClick = { viewModel.showAddUserDialog() },
                    onDeleteClick = { viewModel.deleteUser(it) }
                )
            } else {
                RoomsTab(
                    rooms = state.rooms,
                    isLoading = state.isLoading,
                    onAddClick = { viewModel.showAddRoomDialog() },
                    onDeleteClick = { viewModel.deleteRoom(it) },
                    onStatusChange = { room, status -> viewModel.updateRoomStatus(room.id, status) }
                )
            }
        }
    }

    if (state.showAddUserDialog) {
        AddUserDialog(
            onDismiss = { viewModel.hideDialogs() },
            onConfirm = { name, email, password, role ->
                viewModel.addUser(name, email, password, role)
            }
        )
    }

    if (state.showAddRoomDialog) {
        AddRoomDialog(
            onDismiss = { viewModel.hideDialogs() },
            onConfirm = { roomNumber, floor, type ->
                viewModel.addRoom(roomNumber, floor, type)
            }
        )
    }
}

@Composable
fun HotelHeaderCard(hotelName: String, hotelId: String) {
    val colorScheme = MaterialTheme.colorScheme
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = colorScheme.primary)
    ) {
        Row(
            modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorScheme.onPrimary.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Apartment, contentDescription = null, tint = colorScheme.onPrimary)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(hotelName, color = colorScheme.onPrimary, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text("ID: $hotelId", color = colorScheme.onPrimary.copy(alpha = 0.7f), fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun AdminStatsGrid(roomsCount: Int, employeesCount: Int, ticketsCount: Int, occupancy: Int) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatItem(Modifier.weight(1f), roomsCount.toString(), "Habitaciones", Icons.Default.Apartment, ColorAdmin)
            StatItem(Modifier.weight(1f), employeesCount.toString(), "Empleados", Icons.Default.Groups, ColorClean)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatItem(Modifier.weight(1f), ticketsCount.toString(), "Tickets Abiertos", Icons.AutoMirrored.Filled.Assignment, ColorInProgress)
            StatItem(Modifier.weight(1f), "$occupancy%", "Ocupación", Icons.Default.SensorDoor, ColorAvailable)
        }
    }
}

@Composable
fun StatItem(modifier: Modifier, value: String, label: String, icon: ImageVector, iconColor: Color) {
    val colorScheme = MaterialTheme.colorScheme
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = colorScheme.surface),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(iconColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(value, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = colorScheme.onSurface)
                Text(label, fontSize = 11.sp, color = colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
fun UsersTab(
    users: List<User>,
    isLoading: Boolean,
    onAddClick: () -> Unit,
    onDeleteClick: (User) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Gestión de Usuarios", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = colorScheme.headingColor)
            Button(
                onClick = onAddClick,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Icon(Icons.Default.PersonAdd, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Nuevo Usuario")
            }
        }

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = colorScheme.primary)
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(users) { user ->
                    UserCard(user = user, onDeleteClick = { onDeleteClick(user) })
                }
            }
        }
    }
}

@Composable
fun UserCard(user: User, onDeleteClick: () -> Unit) {
    val colorScheme = MaterialTheme.colorScheme
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val roleColor = when (user.role) {
                UserRole.ADMIN -> ColorAdmin
                UserRole.HOUSEKEEPING -> ColorClean
                UserRole.MAINTENANCE -> ColorInProgress
            }
            val roleIcon = when (user.role) {
                UserRole.ADMIN -> Icons.Default.Shield
                UserRole.HOUSEKEEPING -> Icons.Default.AutoAwesome
                UserRole.MAINTENANCE -> Icons.Default.Build
            }

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(roleColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(roleIcon, contentDescription = null, tint = Color.White)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(user.name, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = colorScheme.onSurface)
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        color = roleColor,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            user.role.toDisplayName(),
                            color = Color.White,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Email, contentDescription = null, modifier = Modifier.size(14.dp), tint = colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(user.email, fontSize = 13.sp, color = colorScheme.onSurfaceVariant)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Business, contentDescription = null, modifier = Modifier.size(14.dp), tint = colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(user.department, fontSize = 13.sp, color = colorScheme.onSurfaceVariant)
                }
                Text("ID: ${user.employeeId}", fontSize = 11.sp, color = colorScheme.onSurfaceVariant.copy(alpha = 0.6f))
            }

            Row {
                IconButton(onClick = { }) { Icon(Icons.Default.Edit, contentDescription = "Edit", tint = colorScheme.onSurfaceVariant) }
                IconButton(onClick = onDeleteClick) { Icon(Icons.Default.Delete, contentDescription = "Delete", tint = ColorDirty) }
            }
        }
    }
}

@Composable
fun RoomsTab(
    rooms: List<Room>,
    isLoading: Boolean,
    onAddClick: () -> Unit,
    onDeleteClick: (Room) -> Unit,
    onStatusChange: (Room, RoomStatus) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Gestión de Habitaciones", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = colorScheme.headingColor)
            Button(
                onClick = onAddClick,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorScheme.primary)
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Agregar")
            }
        }

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = colorScheme.primary)
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(rooms) { room ->
                    RoomCard(
                        room = room, 
                        onDeleteClick = { onDeleteClick(room) },
                        onStatusChange = { onStatusChange(room, it) }
                    )
                }
            }
        }
    }
}

@Composable
fun RoomCard(room: Room, onDeleteClick: () -> Unit, onStatusChange: (RoomStatus) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val colorScheme = MaterialTheme.colorScheme

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.MeetingRoom, contentDescription = null, tint = colorScheme.primary)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("#${room.roomNumber}", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = colorScheme.onSurface)
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    val statusColor = when (room.status) {
                        RoomStatus.AVAILABLE -> ColorAvailable
                        RoomStatus.CLEAN -> ColorClean
                        else -> ColorDirty
                    }

                    Box {
                        Surface(
                            color = statusColor,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.clickable { expanded = true }
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)) {
                                Text(room.status.name, color = Color.White, fontSize = 10.sp)
                                Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                            }
                        }
                        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                            RoomStatus.entries.forEach { status ->
                                DropdownMenuItem(
                                    text = { Text(status.name) },
                                    onClick = { onStatusChange(status); expanded = false }
                                )
                            }
                        }
                    }
                }
                Text("Piso ${room.floor} · ${room.type.name}", color = colorScheme.onSurfaceVariant, fontSize = 14.sp)
            }
            IconButton(onClick = onDeleteClick) {
                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = ColorDirty.copy(alpha = 0.5f))
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
        title = { Text("Agregar Usuario", fontWeight = FontWeight.Bold) },
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
            TextButton(onClick = { onConfirm(name, email, password, selectedRole) }) {
                Text("Agregar", fontWeight = FontWeight.Bold)
            }
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
        title = { Text("Agregar Habitación", fontWeight = FontWeight.Bold) },
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
            TextButton(onClick = { onConfirm(roomNumber, floor.toIntOrNull() ?: 1, type) }) {
                Text("Agregar", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}
