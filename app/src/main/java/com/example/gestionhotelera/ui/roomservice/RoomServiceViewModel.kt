package com.example.gestionhotelera.ui.roomservice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionhotelera.domain.model.*
import com.example.gestionhotelera.domain.usecase.roomservice.CreateRoomServiceOrderUseCase
import com.example.gestionhotelera.domain.usecase.roomservice.GetRoomServiceOrdersUseCase
import com.example.gestionhotelera.domain.usecase.roomservice.UpdateOrderStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomServiceViewModel @Inject constructor(
    private val getOrdersUseCase: GetRoomServiceOrdersUseCase,
    private val createOrderUseCase: CreateRoomServiceOrderUseCase,
    private val updateStatusUseCase: UpdateOrderStatusUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RoomServiceUiState())
    val uiState: StateFlow<RoomServiceUiState> = _uiState.asStateFlow()

    init {
        loadOrders()
    }

    private fun loadOrders() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getOrdersUseCase().collect { orders ->
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
            updateStatusUseCase(orderId, status)
        }
    }

    fun createOrder(roomId: String, description: String, price: Double) {
        viewModelScope.launch {
            createOrderUseCase(
                hotelId = "HOTEL-DEMO-001", // ID por defecto para la demo
                roomId = if (roomId.startsWith("room-")) roomId else "room-$roomId",
                description = description,
                price = price,
                status = OrderStatus.PENDING
            )
        }
    }
}
