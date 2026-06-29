package com.hotelops.presentation.admin

import com.hotelops.domain.model.MaintenanceTicket
import com.hotelops.domain.model.Room
import com.hotelops.domain.model.RoomServiceOrder
import com.hotelops.domain.model.User

data class AdminState(
    val users: List<User> = emptyList(),
    val rooms: List<Room> = emptyList(),
    val tickets: List<MaintenanceTicket> = emptyList(),
    val orders: List<RoomServiceOrder> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val showAddUserDialog: Boolean = false,
    val showAddRoomDialog: Boolean = false,
    val editingUser: User? = null
)
