package com.hotelops.presentation.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotelops.domain.model.*
import com.hotelops.domain.usecase.maintenance.DeleteTicketUseCase
import com.hotelops.domain.usecase.maintenance.GetTicketsUseCase
import com.hotelops.domain.usecase.room.*
import com.hotelops.domain.usecase.roomservice.DeleteOrderUseCase
import com.hotelops.domain.usecase.roomservice.GetOrdersUseCase
import com.hotelops.domain.usecase.user.*
import com.hotelops.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.security.MessageDigest
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getRoomsUseCase: GetRoomsUseCase,
    private val getTicketsUseCase: GetTicketsUseCase,
    private val getOrdersUseCase: GetOrdersUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val addRoomUseCase: AddRoomUseCase,
    private val deleteRoomUseCase: DeleteRoomUseCase,
    private val updateRoomStatusUseCase: UpdateRoomStatusUseCase,
    private val deleteTicketUseCase: DeleteTicketUseCase,
    private val deleteOrderUseCase: DeleteOrderUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AdminState())
    val state: StateFlow<AdminState> = _state.asStateFlow()

    private var currentHotelId: String? = null

    fun loadData(hotelId: String) {
        currentHotelId = hotelId
        getUsersUseCase(hotelId).onEach { result ->
            if (result is Resource.Success) {
                _state.value = _state.value.copy(users = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)

        getRoomsUseCase(hotelId).onEach { result ->
            if (result is Resource.Success) {
                _state.value = _state.value.copy(rooms = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)

        getTicketsUseCase(hotelId).onEach { result ->
            if (result is Resource.Success) {
                _state.value = _state.value.copy(tickets = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)

        getOrdersUseCase(hotelId).onEach { result ->
            if (result is Resource.Success) {
                _state.value = _state.value.copy(orders = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }

    fun showAddUserDialog() { _state.value = _state.value.copy(showAddUserDialog = true) }
    fun showAddRoomDialog() { _state.value = _state.value.copy(showAddRoomDialog = true) }
    fun hideDialogs() { _state.value = _state.value.copy(showAddUserDialog = false, showAddRoomDialog = false) }

    fun addUser(name: String, email: String, password: String, role: UserRole) {
        val hotelId = currentHotelId ?: return
        createUserUseCase(
            hotelId = hotelId,
            name = name,
            email = email,
            password = password,
            role = role,
            department = role.toDisplayName(),
            phone = null,
            employeeId = "EMP-${System.currentTimeMillis()}"
        ).onEach { result ->
            if (result is Resource.Success) {
                hideDialogs()
            }
        }.launchIn(viewModelScope)
    }

    fun deleteUser(user: User) {
        deleteUserUseCase(user.id).launchIn(viewModelScope)
    }

    fun addRoom(roomNumber: String, floor: Int, type: String) {
        val hotelId = currentHotelId ?: return
        val roomType = runCatching { RoomType.valueOf(type) }.getOrDefault(RoomType.SINGLE)
        addRoomUseCase(hotelId, roomNumber, floor, roomType).onEach { result ->
            if (result is Resource.Success) {
                hideDialogs()
            }
        }.launchIn(viewModelScope)
    }

    fun deleteRoom(room: Room) {
        deleteRoomUseCase(room.id).launchIn(viewModelScope)
    }

    fun updateRoomStatus(roomId: String, status: RoomStatus) {
        updateRoomStatusUseCase(roomId, status, "Admin").launchIn(viewModelScope)
    }

    fun deleteCompletedTickets() {
        _state.value.tickets.filter { it.status == TicketStatus.COMPLETED }.forEach { ticket ->
            deleteTicketUseCase(ticket.id).launchIn(viewModelScope)
        }
    }

    fun deleteDeliveredOrders() {
        _state.value.orders.filter { it.status == OrderStatus.DELIVERED }.forEach { order ->
            deleteOrderUseCase(order.id).launchIn(viewModelScope)
        }
    }
}
