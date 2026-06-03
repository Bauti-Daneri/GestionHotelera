package com.example.gestionhotelera.ui.roomservice

import com.example.gestionhotelera.domain.model.OrderStatus
import com.example.gestionhotelera.domain.model.RoomServiceOrder

data class RoomServiceUiState(
    val isLoading: Boolean = false,
    val orders: List<RoomServiceOrder> = emptyList(),
    val filter: OrderStatus? = null,
    val pendingCount: Int = 0,
    val error: String? = null
)
