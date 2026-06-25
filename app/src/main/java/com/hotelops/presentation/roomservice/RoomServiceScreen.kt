package com.hotelops.presentation.roomservice

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hotelops.domain.model.OrderItem
import com.hotelops.domain.model.OrderStatus
import com.hotelops.domain.model.Room
import com.hotelops.domain.model.RoomServiceOrder
import com.hotelops.domain.model.User

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

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Room Service") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        )

        // Stats
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatCard("Pendientes", state.orders.count { it.status == OrderStatus.PENDING },
                MaterialTheme.colorScheme.errorContainer, Modifier.weight(1f))
            StatCard("Preparando", state.orders.count { it.status == OrderStatus.PREPARING },
                MaterialTheme.colorScheme.secondaryContainer, Modifier.weight(1f))
            StatCard("Listos", state.orders.count { it.status == OrderStatus.READY },
                MaterialTheme.colorScheme.tertiaryContainer, Modifier.weight(1f))
        }

        // Filter chips
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(statusFilters) { (status, label) ->
                FilterChip(
                    selected = state.filterStatus == status,
                    onClick = { viewModel.setFilter(status) },
                    label = { Text(label) }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredOrders, key = { it.id }) { order ->
                        OrderCard(order = order, onStatusChange = { viewModel.updateOrderStatus(order.id, it) })
                    }
                }
            }
            FloatingActionButton(
                onClick = { viewModel.showCreateDialog() },
                modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Nuevo pedido")
            }
        }
    }

    if (state.showCreateDialog) {
        CreateOrderDialog(
            rooms = state.rooms,
            onDismiss = { viewModel.hideDialog() },
            onConfirm = { room, guestName, items, instructions ->
                viewModel.createOrder(
                    hotelId = currentUser?.hotelId ?: "",
                    room = room,
                    guestName = guestName,
                    items = items,
                    instructions = instructions
                )
            }
        )
    }
}

@Composable
private fun StatCard(label: String, count: Int, containerColor: androidx.compose.ui.graphics.Color, modifier: Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = containerColor)) {
        Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(count.toString(), style = MaterialTheme.typography.headlineSmall)
            Text(label, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
private fun OrderCard(order: RoomServiceOrder, onStatusChange: (OrderStatus) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Hab. ${order.roomNumber} · ${order.guestName}", style = MaterialTheme.typography.titleSmall)
                    Text("Total: ${"%.2f".format(order.totalAmount)}€", style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary)
                }
                Box {
                    AssistChip(onClick = { expanded = true }, label = { Text(order.status.displayName()) })
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        OrderStatus.entries.filter { it != order.status }.forEach { status ->
                            DropdownMenuItem(
                                text = { Text(status.displayName()) },
                                onClick = { onStatusChange(status); expanded = false }
                            )
                        }
                    }
                }
            }
            order.items.forEach { item ->
                Text("• ${item.quantity}x ${item.name} — ${"%.2f".format(item.price)}€",
                    style = MaterialTheme.typography.bodySmall)
            }
            order.specialInstructions?.let {
                Text("Nota: $it", style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
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
    var selectedRoom by remember { mutableStateOf(rooms.firstOrNull()) }
    var guestName by remember { mutableStateOf("") }
    var instructions by remember { mutableStateOf("") }
    var roomExpanded by remember { mutableStateOf(false) }
    var items by remember { mutableStateOf(listOf<OrderItem>()) }
    var newItemName by remember { mutableStateOf("") }
    var newItemPrice by remember { mutableStateOf("") }
    var newItemQty by remember { mutableStateOf("1") }

    // Predefined menu items
    val menuItems = listOf(
        "Desayuno continental" to 12.0,
        "Hamburguesa" to 15.0,
        "Ensalada" to 10.0,
        "Agua mineral" to 3.0,
        "Zumo de naranja" to 5.0,
        "Café" to 3.5
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nuevo Pedido") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 500.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ExposedDropdownMenuBox(expanded = roomExpanded, onExpandedChange = { roomExpanded = it }) {
                    OutlinedTextField(
                        value = selectedRoom?.let { "Hab. ${it.roomNumber}" } ?: "Habitación",
                        onValueChange = {}, readOnly = true,
                        label = { Text("Habitación") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(roomExpanded) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(expanded = roomExpanded, onDismissRequest = { roomExpanded = false }) {
                        rooms.forEach { room ->
                            DropdownMenuItem(
                                text = { Text("Hab. ${room.roomNumber} - Piso ${room.floor}") },
                                onClick = { selectedRoom = room; roomExpanded = false }
                            )
                        }
                    }
                }

                OutlinedTextField(value = guestName, onValueChange = { guestName = it },
                    label = { Text("Nombre del huésped") }, modifier = Modifier.fillMaxWidth(), singleLine = true)

                Text("Menú rápido", style = MaterialTheme.typography.labelMedium)
                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(menuItems) { (name, price) ->
                        SuggestionChip(
                            onClick = {
                                val existing = items.firstOrNull { it.name == name }
                                items = if (existing != null) {
                                    items.map { if (it.name == name) it.copy(quantity = it.quantity + 1) else it }
                                } else {
                                    items + OrderItem(name, 1, price)
                                }
                            },
                            label = { Text("$name €${"%.0f".format(price)}") }
                        )
                    }
                }

                if (items.isNotEmpty()) {
                    Text("Pedido:", style = MaterialTheme.typography.labelMedium)
                    items.forEach { item ->
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {
                            Text("${item.quantity}x ${item.name}", style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.weight(1f))
                            Text("${"%.2f".format(item.price * item.quantity)}€",
                                style = MaterialTheme.typography.bodySmall)
                            IconButton(onClick = { items = items.filter { it.name != item.name } },
                                modifier = Modifier.size(24.dp)) {
                                Icon(Icons.Default.Delete, contentDescription = null,
                                    modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }

                OutlinedTextField(value = instructions, onValueChange = { instructions = it },
                    label = { Text("Instrucciones especiales") }, modifier = Modifier.fillMaxWidth(), maxLines = 2)
            }
        },
        confirmButton = {
            TextButton(onClick = {
                selectedRoom?.let { onConfirm(it, guestName, items, instructions) }
            }, enabled = selectedRoom != null && items.isNotEmpty()) {
                Text("Crear Pedido")
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}

private fun OrderStatus.displayName() = when (this) {
    OrderStatus.PENDING -> "Pendiente"
    OrderStatus.PREPARING -> "Preparando"
    OrderStatus.READY -> "Listo"
    OrderStatus.DELIVERED -> "Entregado"
}
