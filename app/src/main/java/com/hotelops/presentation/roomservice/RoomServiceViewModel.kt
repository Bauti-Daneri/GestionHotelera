package com.hotelops.presentation.roomservice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotelops.data.local.dao.RoomDao
import com.hotelops.data.local.dao.RoomServiceDao
import com.hotelops.data.local.entity.RoomServiceOrderEntity
import com.hotelops.data.mapper.toDomain
import com.hotelops.domain.model.OrderItem
import com.hotelops.domain.model.OrderStatus
import com.hotelops.domain.model.Room
import com.hotelops.domain.model.RoomServiceOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

data class RoomServiceState(
    val orders: List<RoomServiceOrder> = emptyList(),
    val rooms: List<Room> = emptyList(),
    val isLoading: Boolean = false,
    val filterStatus: OrderStatus? = null,
    val showCreateDialog: Boolean = false
)

@HiltViewModel
class RoomServiceViewModel @Inject constructor(
    private val roomServiceDao: RoomServiceDao,
    private val roomDao: RoomDao
) : ViewModel() {

    private val _state = MutableStateFlow(RoomServiceState())
    val state: StateFlow<RoomServiceState> = _state.asStateFlow()

    fun loadData(hotelId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            roomServiceDao.getOrdersByHotel(hotelId).collect { entities ->
                _state.value = _state.value.copy(orders = entities.map { it.toDomain() }, isLoading = false)
            }
        }
        viewModelScope.launch {
            roomDao.getRoomsByHotel(hotelId).collect { entities ->
                _state.value = _state.value.copy(rooms = entities.map { it.toDomain() })
            }
        }
    }

    fun setFilter(status: OrderStatus?) { _state.value = _state.value.copy(filterStatus = status) }
    fun showCreateDialog() { _state.value = _state.value.copy(showCreateDialog = true) }
    fun hideDialog() { _state.value = _state.value.copy(showCreateDialog = false) }

    fun createOrder(hotelId: String, room: Room, guestName: String, items: List<OrderItem>, instructions: String) {
        viewModelScope.launch {
            val total = items.sumOf { it.price * it.quantity }
            val entity = RoomServiceOrderEntity(
                id = UUID.randomUUID().toString(),
                hotelId = hotelId,
                roomId = room.id,
                roomNumber = room.roomNumber,
                guestName = guestName,
                items = items,
                status = OrderStatus.PENDING,
                totalAmount = total,
                specialInstructions = instructions.ifBlank { null },
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis(),
                deliveredAt = null,
                isDirty = true
            )
            roomServiceDao.insertOrder(entity)
            hideDialog()
        }
    }

    fun updateOrderStatus(orderId: String, newStatus: OrderStatus) {
        viewModelScope.launch {
            val deliveredAt = if (newStatus == OrderStatus.DELIVERED) System.currentTimeMillis() else null
            roomServiceDao.updateOrderStatus(orderId, newStatus, System.currentTimeMillis(), deliveredAt)
        }
    }
}
