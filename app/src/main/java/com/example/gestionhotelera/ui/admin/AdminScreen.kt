package com.example.gestionhotelera.ui.admin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gestionhotelera.domain.model.*
import com.example.gestionhotelera.ui.components.*
import com.example.gestionhotelera.ui.admin.components.AssignmentBottomSheet
import com.example.gestionhotelera.ui.admin.components.EmployeeBottomSheet
import com.example.gestionhotelera.ui.admin.components.RoomBottomSheet
import kotlinx.coroutines.flow.Flow

@Composable
fun AdminScreen(
    viewModel: AdminViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showAddUserSheet by remember { mutableStateOf(false) }
    var userToEdit by remember { mutableStateOf<User?>(null) }
    
    var showAddRoomSheet by remember { mutableStateOf(false) }
    var roomToDelete by remember { mutableStateOf<Room?>(null) }
    
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
    
    LaunchedEffect(uiState.successMessage) {
        uiState.successMessage?.let {
            snackbarHostState.showSnackbar(it)
            showAddUserSheet = false
            userToEdit = null
            showAddRoomSheet = false
            viewModel.clearSuccessMessage()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = padding.calculateBottomPadding()
                )
        ) {
            ScreenTitle(title = "Administración")

            HotelHeaderCard(
                name = uiState.hotel?.name ?: "Hotel Plaza Central",
                modifier = Modifier.fillMaxWidth()
            )

            Surface(
                shape = MaterialTheme.shapes.extraLarge,
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            ) {
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = Color.Transparent,
                    divider = {},
                    indicator = { tabPositions ->
                        if (selectedTab < tabPositions.size) {
                            TabRowDefaults.SecondaryIndicator(
                                Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Icon(if (index == 0) Icons.Rounded.Badge else Icons.Rounded.Hotel, null, modifier = Modifier.size(18.dp))
                                    Text(title)
                                }
                            }
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (selectedTab == 0) "Gestión de Usuarios" else "Gestión de Habitaciones",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                FilledTonalButton(
                    onClick = {
                        if (selectedTab == 0) showAddUserSheet = true
                        else showAddRoomSheet = true
                    },
                    shape = MaterialTheme.shapes.medium,
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Icon(
                        if (selectedTab == 0) Icons.Rounded.PersonAdd else Icons.Rounded.AddHome,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = if (selectedTab == 0) "Nuevo" else "Nueva",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (selectedTab == 0) {
                    items(uiState.users) { user ->
                        EmployeeCard(
                            user = user,
                            onEdit = { userToEdit = user },
                            onDelete = { viewModel.deleteUser(user) }
                        )
                    }
                } else {
                    items(uiState.rooms) { room ->
                        RoomCard(
                            room = room,
                            onClick = { selectedRoomForAssignment = room },
                            onDelete = { roomToDelete = room }
                        )
                    }
                }
            }
        }
    }

    if (showAddUserSheet || userToEdit != null) {
        EmployeeBottomSheet(
            user = userToEdit,
            nameError = uiState.nameError,
            emailError = uiState.emailError,
            phoneError = uiState.phoneError,
            passwordError = uiState.passwordError,
            roleError = uiState.roleError,
            shiftError = uiState.shiftError,
            onDismiss = { 
                showAddUserSheet = false 
                userToEdit = null
            },
            onSave = { name, email, role, schedule, phone, password ->
                if (userToEdit != null) {
                    viewModel.updateUser(userToEdit!!.copy(
                        name = name,
                        email = email,
                        role = role,
                        schedule = schedule,
                        phone = phone
                    ))
                } else {
                    viewModel.saveUser(name, email, role, schedule, phone, password)
                }
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

    if (roomToDelete != null) {
        AlertDialog(
            onDismissRequest = { roomToDelete = null },
            title = { Text("Eliminar habitación") },
            text = { Text("¿Seguro que querés eliminar esta habitación? Esta acción no se puede deshacer.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteRoom(roomToDelete!!)
                        roomToDelete = null
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { roomToDelete = null }) {
                    Text("Cancelar")
                }
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

