package com.example.gestionhotelera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionhotelera.domain.model.OrderStatus
import com.example.gestionhotelera.domain.model.RoomStatus
import com.example.gestionhotelera.domain.model.TicketStatus
import com.example.gestionhotelera.domain.usecase.maintenance.GetMaintenanceTicketsUseCase
import com.example.gestionhotelera.domain.usecase.profile.GetDarkModeUseCase
import com.example.gestionhotelera.domain.usecase.room.GetRoomsUseCase
import com.example.gestionhotelera.domain.usecase.roomservice.GetRoomServiceOrdersUseCase
import com.example.gestionhotelera.ui.components.NotificationCounts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getDarkModeUseCase: GetDarkModeUseCase,
    getMaintenanceTicketsUseCase: GetMaintenanceTicketsUseCase,
    getRoomsUseCase: GetRoomsUseCase,
    getRoomServiceOrdersUseCase: GetRoomServiceOrdersUseCase
) : ViewModel() {

    val isDarkMode: StateFlow<Boolean> = getDarkModeUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    val notificationCounts: StateFlow<NotificationCounts> = combine(
        getMaintenanceTicketsUseCase(),
        getRoomsUseCase(),
        getRoomServiceOrdersUseCase()
    ) { tickets, rooms, orders ->
        NotificationCounts(
            openTickets = tickets.count { it.status == TicketStatus.OPEN },
            dirtyRooms = rooms.count { it.status == RoomStatus.DIRTY },
            pendingOrders = orders.count { it.status == OrderStatus.PENDING }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = NotificationCounts()
    )
}