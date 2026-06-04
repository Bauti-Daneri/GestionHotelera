package com.example.gestionhotelera.ui.roomservice

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
import com.example.gestionhotelera.ui.components.RoomServiceOrderCard
import com.example.gestionhotelera.ui.theme.PrimaryBlue
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomServiceScreen(
    viewModel: RoomServiceViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val menuItems by viewModel.menuItems.collectAsStateWithLifecycle()
    var showCreateOrderSheet by remember { mutableStateOf(false) }
    var showMenuManagementSheet by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Pedidos", "Menú")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Room Service") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryBlue,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            if (selectedTab == 0) {
                FloatingActionButton(
                    onClick = { showCreateOrderSheet = true },
                    containerColor = PrimaryBlue
                ) {
                    Icon(Icons.Rounded.Add, contentDescription = "Nuevo Pedido")
                }
            } else {
                FloatingActionButton(
                    onClick = { showMenuManagementSheet = true },
                    containerColor = PrimaryBlue
                ) {
                    Icon(Icons.Rounded.RestaurantMenu, contentDescription = "Gestionar Menú")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }

            if (selectedTab == 0) {
                RoomServiceContent(
                    uiState = uiState,
                    onFilterChange = { viewModel.setFilter(it) },
                    onStatusChange = { id, status -> viewModel.updateOrderStatus(id, status) }
                )
            } else {
                MenuManagementContent(
                    menuItems = menuItems,
                    onToggleAvailability = { viewModel.toggleMenuItemAvailability(it) },
                    onDelete = { viewModel.deleteMenuItem(it) }
                )
            }
        }
    }

    if (showCreateOrderSheet) {
        CreateOrderBottomSheet(
            availableMenuItems = menuItems.filter { it.isAvailable },
            onDismiss = { showCreateOrderSheet = false },
            onCreate = { roomId, items ->
                viewModel.createOrder(roomId, items)
                showCreateOrderSheet = false
            }
        )
    }

    if (showMenuManagementSheet) {
        AddMenuItemBottomSheet(
            onDismiss = { showMenuManagementSheet = false },
            onSave = { name, desc, price, category ->
                viewModel.createMenuItem(name, desc, price, category)
                showMenuManagementSheet = false
            }
        )
    }
}

@Composable
fun RoomServiceContent(
    uiState: RoomServiceUiState,
    onFilterChange: (OrderStatus?) -> Unit,
    onStatusChange: (String, OrderStatus) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = if (uiState.filter == null) 0 else OrderStatus.values().indexOf(uiState.filter) + 1,
            edgePadding = 16.dp,
            divider = {}
        ) {
            Tab(
                selected = uiState.filter == null,
                onClick = { onFilterChange(null) },
                text = { Text("Todos") }
            )
            OrderStatus.values().forEach { status ->
                Tab(
                    selected = uiState.filter == status,
                    onClick = { onFilterChange(status) },
                    text = { Text(status.name) }
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(uiState.orders) { order ->
                RoomServiceOrderCard(
                    order = order,
                    onStatusChange = { status -> onStatusChange(order.id, status) }
                )
            }
        }
    }
}

@Composable
fun MenuManagementContent(
    menuItems: List<RoomServiceMenuItem>,
    onToggleAvailability: (String) -> Unit,
    onDelete: (RoomServiceMenuItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(menuItems) { item ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (item.isAvailable) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                )
            ) {
                ListItem(
                    headlineContent = { Text(item.name) },
                    supportingContent = { Text("${item.category} - $${item.price}") },
                    trailingContent = {
                        Row {
                            Switch(
                                checked = item.isAvailable,
                                onCheckedChange = { onToggleAvailability(item.id) }
                            )
                            IconButton(onClick = { onDelete(item) }) {
                                Icon(Icons.Rounded.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOrderBottomSheet(
    availableMenuItems: List<RoomServiceMenuItem>,
    onDismiss: () -> Unit,
    onCreate: (String, List<RoomServiceItem>) -> Unit
) {
    var roomId by remember { mutableStateOf("") }
    val itemsMap = remember { mutableStateMapOf<String, Int>() }
    
    val total = availableMenuItems.sumOf { (itemsMap[it.id] ?: 0) * it.price }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Nuevo Pedido", style = MaterialTheme.typography.titleLarge)
            
            OutlinedTextField(
                value = roomId,
                onValueChange = { roomId = it },
                label = { Text("Habitación") },
                placeholder = { Text("Ej: 101") },
                modifier = Modifier.fillMaxWidth()
            )

            Text("Productos", style = MaterialTheme.typography.labelLarge)
            
            LazyColumn(modifier = Modifier.weight(1f, fill = false)) {
                items(availableMenuItems) { product ->
                    val quantity = itemsMap[product.id] ?: 0
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(product.name, style = MaterialTheme.typography.bodyLarge)
                            Text("$${product.price}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { if (quantity > 0) itemsMap[product.id] = quantity - 1 }) {
                                Icon(Icons.Rounded.Remove, contentDescription = "Menos")
                            }
                            Text(quantity.toString(), modifier = Modifier.padding(horizontal = 8.dp))
                            IconButton(onClick = { itemsMap[product.id] = quantity + 1 }) {
                                Icon(Icons.Rounded.Add, contentDescription = "Más")
                            }
                        }
                    }
                }
            }
            
            HorizontalDivider()
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total", style = MaterialTheme.typography.titleMedium, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                Text("$${String.format("%.2f", total)}", style = MaterialTheme.typography.titleMedium, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, color = PrimaryBlue)
            }
            
            Button(
                onClick = { 
                    val selectedItems = availableMenuItems.filter { (itemsMap[it.id] ?: 0) > 0 }.map { 
                        RoomServiceItem(
                            id = it.id,
                            name = it.name,
                            price = it.price,
                            quantity = itemsMap[it.id] ?: 0
                        )
                    }
                    onCreate(roomId, selectedItems)
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = roomId.isNotBlank() && total > 0
            ) {
                Text("Crear Pedido")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMenuItemBottomSheet(
    onDismiss: () -> Unit,
    onSave: (String, String, Double, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Nuevo Producto", style = MaterialTheme.typography.titleLarge)
            
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Precio") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Categoría") },
                placeholder = { Text("Ej: Bebidas, Postres") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { 
                    val priceDouble = price.toDoubleOrNull() ?: 0.0
                    onSave(name, description, priceDouble, category)
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = name.isNotBlank() && price.toDoubleOrNull() != null && price.toDouble() > 0
            ) {
                Text("Guardar")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
