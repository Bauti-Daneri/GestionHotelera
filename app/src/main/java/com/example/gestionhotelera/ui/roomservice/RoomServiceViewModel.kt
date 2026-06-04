package com.example.gestionhotelera.ui.roomservice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionhotelera.domain.model.*
import com.example.gestionhotelera.domain.repository.RoomServiceRepository
import com.example.gestionhotelera.domain.usecase.roomservice.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RoomServiceViewModel @Inject constructor(
    private val roomServiceRepository: RoomServiceRepository,
    private val getMenuItemsUseCase: GetMenuItemsUseCase,
    private val createMenuItemUseCase: CreateMenuItemUseCase,
    private val updateMenuItemUseCase: UpdateMenuItemUseCase,
    private val deleteMenuItemUseCase: DeleteMenuItemUseCase,
    private val toggleMenuItemAvailabilityUseCase: ToggleMenuItemAvailabilityUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RoomServiceUiState())
    val uiState: StateFlow<RoomServiceUiState> = _uiState.asStateFlow()

    private val _menuItems = MutableStateFlow<List<RoomServiceMenuItem>>(emptyList())
    val menuItems: StateFlow<List<RoomServiceMenuItem>> = _menuItems.asStateFlow()

    init {
        loadOrders()
        loadMenu()
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

    private fun loadMenu() {
        viewModelScope.launch {
            getMenuItemsUseCase().collect { items ->
                _menuItems.value = items
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
                roomId = if (roomId.startsWith("room-")) roomId else "room-$roomId",
                items = items,
                status = OrderStatus.PENDING,
                totalPrice = items.sumOf { it.price * it.quantity },
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
            roomServiceRepository.createOrder(order)
        }
    }

    // Menu Management
    fun createMenuItem(name: String, description: String, price: Double, category: String) {
        viewModelScope.launch {
            val item = RoomServiceMenuItem(
                id = UUID.randomUUID().toString(),
                hotelId = "HOTEL-DEMO-001",
                name = name,
                description = description,
                price = price,
                category = category,
                isAvailable = true,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
            createMenuItemUseCase(item)
        }
    }

    fun updateMenuItem(item: RoomServiceMenuItem) {
        viewModelScope.launch {
            updateMenuItemUseCase(item.copy(updatedAt = System.currentTimeMillis()))
        }
    }

    fun deleteMenuItem(item: RoomServiceMenuItem) {
        viewModelScope.launch {
            deleteMenuItemUseCase(item)
        }
    }

    fun toggleMenuItemAvailability(id: String) {
        viewModelScope.launch {
            toggleMenuItemAvailabilityUseCase(id)
        }
    }
}
