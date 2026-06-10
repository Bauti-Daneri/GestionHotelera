package com.example.gestionhotelera.ui.maintenance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionhotelera.domain.model.*
import com.example.gestionhotelera.domain.usecase.auth.GetActiveUserUseCase
import com.example.gestionhotelera.domain.usecase.maintenance.CreateMaintenanceTicketUseCase
import com.example.gestionhotelera.domain.usecase.maintenance.GetMaintenanceTicketsUseCase
import com.example.gestionhotelera.domain.usecase.maintenance.GetTicketByIdUseCase
import com.example.gestionhotelera.domain.usecase.maintenance.UpdateTicketStatusUseCase
import com.example.gestionhotelera.domain.usecase.room.UpdateRoomStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MaintenanceViewModel @Inject constructor(
    private val getMaintenanceTicketsUseCase: GetMaintenanceTicketsUseCase,
    private val getTicketByIdUseCase: GetTicketByIdUseCase,
    private val updateTicketStatusUseCase: UpdateTicketStatusUseCase,
    private val createMaintenanceTicketUseCase: CreateMaintenanceTicketUseCase,
    private val updateRoomStatusUseCase: UpdateRoomStatusUseCase,
    private val getActiveUserUseCase: GetActiveUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MaintenanceUiState())
    val uiState: StateFlow<MaintenanceUiState> = _uiState.asStateFlow()

    private val selectedFilter = MutableStateFlow<TicketCategory?>(null)

    init {
        loadTickets()
    }

    private fun loadTickets() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            combine(
                getMaintenanceTicketsUseCase(),
                selectedFilter
            ) { tickets, filter ->
                val filteredTickets = if (filter != null) {
                    tickets.filter { it.category == filter }
                } else {
                    tickets
                }

                Triple(tickets, filteredTickets, filter)
            }.collect { (allTickets, filteredTickets, filter) ->
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        tickets = filteredTickets,
                        filter = filter,
                        openCount = allTickets.count { it.status == TicketStatus.OPEN }
                    )
                }
            }
        }
    }

    fun setFilter(category: TicketCategory?) {
        selectedFilter.value = category
    }

    fun updateTicketStatus(ticketId: String, status: TicketStatus) {
        viewModelScope.launch {
            updateTicketStatusUseCase(ticketId, status)

            if (status == TicketStatus.RESOLVED) {
                val ticket = getTicketByIdUseCase(ticketId)
                ticket?.let {
                    updateRoomStatusUseCase(it.roomId, RoomStatus.DIRTY)
                }
            }
        }
    }

    fun createTicket(
        roomId: String,
        category: TicketCategory,
        description: String,
        imageUrl: String? = null
    ) {
        viewModelScope.launch {
            val user = getActiveUserUseCase().firstOrNull()

            val ticket = MaintenanceTicket(
                id = UUID.randomUUID().toString(),
                hotelId = user?.hotelId ?: "HOTEL-DEMO-001",
                roomId = if (roomId.startsWith("room-")) roomId else "room-$roomId",
                description = description,
                category = category,
                status = TicketStatus.OPEN,
                reportedBy = user?.id ?: "maint-01",
                imageUrl = imageUrl,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )

            createMaintenanceTicketUseCase(ticket)
            updateRoomStatusUseCase(ticket.roomId, RoomStatus.MAINTENANCE)
        }
    }
}