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
    private val updateStatusUseCase: UpdateOrderStatusUseCase,
    private val getCurrentUserUseCase: com.example.gestionhotelera.domain.usecase.auth.GetCurrentUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RoomServiceUiState())
    val uiState: StateFlow<RoomServiceUiState> = _uiState.asStateFlow()

    private var currentUser: User? = null

    init {
        observeUser()
        loadOrders()
    }

    private fun observeUser() {
        viewModelScope.launch {
            getCurrentUserUseCase().collect { user ->
                currentUser = user
            }
        }
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
        val hotelId = currentUser?.hotelId ?: "HOTEL-DEMO-001"
        viewModelScope.launch {
            createOrderUseCase(
                hotelId = hotelId,
                roomId = if (roomId.startsWith("room-")) roomId else "room-$roomId",
                description = description,
                price = price,
                status = OrderStatus.PENDING
            )
        }
    }
}
