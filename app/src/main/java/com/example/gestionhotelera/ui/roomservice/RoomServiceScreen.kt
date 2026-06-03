package com.example.gestionhotelera.ui.roomservice

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gestionhotelera.domain.model.OrderStatus
import com.example.gestionhotelera.domain.model.RoomServiceItem
import com.example.gestionhotelera.ui.components.RoomServiceOrderCard
import com.example.gestionhotelera.ui.theme.PrimaryBlue
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomServiceScreen(
    viewModel: RoomServiceViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showCreateOrderSheet by remember { mutableStateOf(false) }

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
            FloatingActionButton(
                onClick = { showCreateOrderSheet = true },
                containerColor = PrimaryBlue
            ) {
                Icon(Icons.Rounded.Add, contentDescription = "Nuevo Pedido")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            RoomServiceContent(
                uiState = uiState,
                onFilterChange = { viewModel.setFilter(it) },
                onStatusChange = { id, status -> viewModel.updateOrderStatus(id, status) }
            )
        }
    }

    if (showCreateOrderSheet) {
        CreateOrderBottomSheet(
            onDismiss = { showCreateOrderSheet = false },
            onCreate = { roomId, items ->
                viewModel.createOrder(roomId, items)
                showCreateOrderSheet = false
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOrderBottomSheet(
    onDismiss: () -> Unit,
    onCreate: (String, List<RoomServiceItem>) -> Unit
) {
    var roomId by remember { mutableStateOf("") }
    val availableProducts = listOf(
        RoomServiceItem(UUID.randomUUID().toString(), "Desayuno Continental", 12.0, 0),
        RoomServiceItem(UUID.randomUUID().toString(), "Café Espresso", 3.5, 0),
        RoomServiceItem(UUID.randomUUID().toString(), "Hamburguesa Gourmet", 18.0, 0),
        RoomServiceItem(UUID.randomUUID().toString(), "Vino Tinto", 25.0, 0)
    )
    val itemsMap = remember { mutableStateMapOf<String, Int>() }
    
    val total = availableProducts.sumOf { (itemsMap[it.name] ?: 0) * it.price }

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
            
            availableProducts.forEach { product ->
                val quantity = itemsMap[product.name] ?: 0
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(product.name, style = MaterialTheme.typography.bodyLarge)
                        Text("$${product.price}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { if (quantity > 0) itemsMap[product.name] = quantity - 1 }) {
                            Icon(Icons.Rounded.Remove, contentDescription = "Menos")
                        }
                        Text(quantity.toString(), modifier = Modifier.padding(horizontal = 8.dp))
                        IconButton(onClick = { itemsMap[product.name] = quantity + 1 }) {
                            Icon(Icons.Rounded.Add, contentDescription = "Más")
                        }
                    }
                }
            }
            
            Divider()
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Total", style = MaterialTheme.typography.titleMedium, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                Text("$${String.format("%.2f", total)}", style = MaterialTheme.typography.titleMedium, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, color = PrimaryBlue)
            }
            
            Button(
                onClick = { 
                    val selectedItems = availableProducts.filter { (itemsMap[it.name] ?: 0) > 0 }.map { 
                        it.copy(quantity = itemsMap[it.name] ?: 0)
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
