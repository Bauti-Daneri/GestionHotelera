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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gestionhotelera.domain.model.*
import com.example.gestionhotelera.ui.components.RoomServiceOrderCard
import com.example.gestionhotelera.ui.theme.PrimaryBlue

@Composable
fun RoomServiceScreen(
    viewModel: RoomServiceViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showCreateOrderSheet by remember { mutableStateOf(false) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // Fixed top section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ScrollableTabRow(
                    selectedTabIndex = if (uiState.filter == null) 0 else OrderStatus.values().indexOf(uiState.filter) + 1,
                    edgePadding = 0.dp,
                    divider = {},
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = PrimaryBlue
                ) {
                    Tab(
                        selected = uiState.filter == null,
                        onClick = { viewModel.setFilter(null) },
                        text = { Text("TODOS") }
                    )
                    OrderStatus.values().forEach { status ->
                        Tab(
                            selected = uiState.filter == status,
                            onClick = { viewModel.setFilter(status) },
                            text = { Text(status.name) }
                        )
                    }
                }

                Button(
                    onClick = { showCreateOrderSheet = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Icon(Icons.Rounded.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Nuevo Pedido")
                }
            }

            // Scrollable List
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 120.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (uiState.orders.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillParentMaxSize()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "No hay pedidos",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                } else {
                    items(uiState.orders) { order ->
                        RoomServiceOrderCard(
                            order = order,
                            onStatusChange = { status -> viewModel.updateOrderStatus(order.id, status) }
                        )
                    }
                }
            }
        }
    }

    if (showCreateOrderSheet) {
        CreateOrderBottomSheet(
            onDismiss = { showCreateOrderSheet = false },
            onCreate = { roomId, description, price ->
                viewModel.createOrder(roomId, description, price)
                showCreateOrderSheet = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateOrderBottomSheet(
    onDismiss: () -> Unit,
    onCreate: (String, String, Double) -> Unit
) {
    var roomId by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Nuevo Pedido", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            
            OutlinedTextField(
                value = roomId,
                onValueChange = { roomId = it },
                label = { Text("Número de Habitación") },
                placeholder = { Text("Ej: 101") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción del pedido") },
                placeholder = { Text("Ej: Cena completa, Bebidas, etc.") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Precio Total") },
                placeholder = { Text("0.00") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                prefix = { Text("$ ") }
            )
            
            Button(
                onClick = { 
                    val priceDouble = price.toDoubleOrNull() ?: 0.0
                    onCreate(roomId, description, priceDouble)
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = roomId.isNotBlank() && description.isNotBlank() && price.toDoubleOrNull() != null,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
            ) {
                Text("Crear Pedido")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
