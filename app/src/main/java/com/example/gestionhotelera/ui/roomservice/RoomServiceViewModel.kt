package com.example.gestionhotelera.ui.roomservice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionhotelera.domain.model.*
import com.example.gestionhotelera.domain.repository.RoomServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RoomServiceViewModel @Inject constructor(
    private val roomServiceRepository: RoomServiceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RoomServiceUiState())
    val uiState: StateFlow<RoomServiceUiState> = _uiState.asStateFlow()

    init {
        loadOrders()
    }

    private fun loadOrders() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            roomServiceRepository.getOrders().collect { orders ->
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        orders = if (state.filter != null) orders.filter { it.status == state.filter } else orders,
                        pendingCount = orders.count { it.status == OrderStatus.PENDING }
                    )
                }
            }
        }
    }

    fun setFilter(status: OrderStatus?) {
        _uiState.update { it.copy(filter = status) }
        loadOrders()
    }

    fun updateOrderStatus(orderId: String, status: OrderStatus) {
        viewModelScope.launch {
            roomServiceRepository.updateOrderStatus(orderId, status)
        }
    }

    fun createOrder(roomId: String, items: List<RoomServiceItem>) {
        viewModelScope.launch {
            val order = RoomServiceOrder(
                id = UUID.randomUUID().toString(),
                hotelId = "HOTEL-DEMO-001",
                roomId = "room-$roomId",
                items = items,
                status = OrderStatus.PENDING,
                totalPrice = items.sumOf { it.price * it.quantity },
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
            roomServiceRepository.createOrder(order)
        }
    }
}
