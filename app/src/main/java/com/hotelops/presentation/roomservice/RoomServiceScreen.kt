package com.hotelops.presentation.roomservice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hotelops.domain.model.*
import com.hotelops.presentation.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomServiceScreen(
    currentUser: User?,
    viewModel: RoomServiceViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(currentUser?.hotelId) {
        currentUser?.hotelId?.let { viewModel.loadData(it) }
    }

    val filteredOrders = if (state.filterStatus != null)
        state.orders.filter { it.status == state.filterStatus }
    else state.orders

    val statusFilters = listOf(null to "Todos") + OrderStatus.entries.map { it to it.displayName() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Room Service", 
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Primary
                        )
                    ) 
                },
                actions = {
                    Surface(
                        color = SurfaceVariant,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "${state.orders.size} Pedidos",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.showCreateDialog() },
                containerColor = Primary,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Restaurant, contentDescription = "Nuevo pedido")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Background)
        ) {
            // Search Bar Placeholder
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Buscar por habitación, huésped o plato...", fontSize = 14.sp) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(24.dp),
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(18.dp)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Surface,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            // Filter chips
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 8.dp)
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
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(filteredOrders, key = { it.id }) { order ->
                        OrderCard(
                            order = order, 
                            onStatusChange = { viewModel.updateOrderStatus(order.id, it) }
                        )
                    }
                }
            }
        }
    }

    if (state.showCreateDialog) {
        CreateOrderDialog(
            rooms = state.rooms,
            onDismiss = { viewModel.hideDialog() },
            onConfirm = { room, guest, items, instr ->
                viewModel.createOrder(
                    hotelId = currentUser?.hotelId ?: "",
                    room = room,
                    guestName = guest,
                    items = items,
                    instructions = instr
                )
            }
        )
    }
}

@Composable
private fun OrderCard(order: RoomServiceOrder, onStatusChange: (OrderStatus) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Hab. #${order.roomNumber}", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(order.guestName, color = OnSurfaceVariant, fontSize = 14.sp)
                }
                Surface(
                    color = when(order.status) {
                        OrderStatus.PENDING -> ColorInProgress
                        OrderStatus.PREPARING -> ColorAvailable
                        OrderStatus.READY -> ColorAvailable
                        OrderStatus.DELIVERED -> ColorClean
                    },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        order.status.displayName(),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = SurfaceVariant)
            Spacer(modifier = Modifier.height(16.dp))

            order.items.forEach { item ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("${item.quantity}x ${item.name}", fontSize = 14.sp, color = OnSurfaceVariant)
                    Text("$${item.price}", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                }
                Spacer(modifier = Modifier.height(4.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = SurfaceVariant)
            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AccessTime, contentDescription = null, modifier = Modifier.size(14.dp), tint = OnSurfaceVariant)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("08:30", fontSize = 14.sp, color = OnSurfaceVariant)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("$${order.totalAmount}", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Primary)
                }
            }

            if (order.status != OrderStatus.DELIVERED) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(
                        onClick = { onStatusChange(OrderStatus.PREPARING) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Marcar En Proceso", fontSize = 12.sp)
                    }
                    Button(
                        onClick = { onStatusChange(OrderStatus.DELIVERED) },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Primary)
                    ) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Entregado", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateOrderDialog(
    rooms: List<Room>,
    onDismiss: () -> Unit,
    onConfirm: (Room, String, List<OrderItem>, String) -> Unit
) {
    var guestName by remember { mutableStateOf("") }
    var selectedRoom by remember { mutableStateOf(rooms.firstOrNull()) }
    var instructions by remember { mutableStateOf("") }
    var roomExpanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nuevo Pedido", fontWeight = FontWeight.Bold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(value = guestName, onValueChange = { guestName = it },
                    label = { Text("Nombre del Huésped") }, modifier = Modifier.fillMaxWidth(), singleLine = true)

                ExposedDropdownMenuBox(expanded = roomExpanded, onExpandedChange = { roomExpanded = it }) {
                    OutlinedTextField(
                        value = selectedRoom?.let { "Hab. ${it.roomNumber}" } ?: "Seleccionar habitación",
                        onValueChange = {}, readOnly = true,
                        label = { Text("Habitación") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(roomExpanded) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(expanded = roomExpanded, onDismissRequest = { roomExpanded = false }) {
                        rooms.forEach { room ->
                            DropdownMenuItem(
                                text = { Text("Hab. ${room.roomNumber}") },
                                onClick = { selectedRoom = room; roomExpanded = false }
                            )
                        }
                    }
                }
                
                OutlinedTextField(value = instructions, onValueChange = { instructions = it },
                    label = { Text("Instrucciones Especiales") }, modifier = Modifier.fillMaxWidth(), maxLines = 2)
            }
        },
        confirmButton = {
            TextButton(onClick = {
                selectedRoom?.let { onConfirm(it, guestName, emptyList(), instructions) }
            }) { Text("Crear", fontWeight = FontWeight.Bold) }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}

private fun OrderStatus.displayName() = when (this) {
    OrderStatus.PENDING -> "Pendiente"
    OrderStatus.PREPARING -> "En Proceso"
    OrderStatus.READY -> "Listo"
    OrderStatus.DELIVERED -> "Entregado"
}
