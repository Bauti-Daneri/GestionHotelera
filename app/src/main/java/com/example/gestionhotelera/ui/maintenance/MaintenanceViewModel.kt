package com.example.gestionhotelera.ui.maintenance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionhotelera.domain.model.*
import com.example.gestionhotelera.domain.repository.MaintenanceRepository
import com.example.gestionhotelera.domain.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MaintenanceViewModel @Inject constructor(
    private val maintenanceRepository: MaintenanceRepository,
    private val roomRepository: RoomRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MaintenanceUiState())
    val uiState: StateFlow<MaintenanceUiState> = _uiState.asStateFlow()

    init {
        loadTickets()
    }

    private fun loadTickets() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            maintenanceRepository.getTickets().collect { tickets ->
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        tickets = if (state.filter != null) tickets.filter { it.category == state.filter } else tickets,
                        openCount = tickets.count { it.status == TicketStatus.OPEN }
                    )
                }
            }
        }
    }

    fun setFilter(category: TicketCategory?) {
        _uiState.update { it.copy(filter = category) }
        loadTickets()
    }

    fun updateTicketStatus(ticketId: String, status: TicketStatus) {
        viewModelScope.launch {
            maintenanceRepository.updateTicketStatus(ticketId, status)
            if (status == TicketStatus.RESOLVED) {
                // If resolved, we might want to update the room status back to DIRTY or CLEAN
                // For simplicity in demo, we'll assume it goes back to DIRTY to be checked by Housekeeping
                val ticket = maintenanceRepository.getTicketById(ticketId)
                ticket?.let {
                    roomRepository.updateRoomStatus(it.roomId, RoomStatus.DIRTY)
                }
            }
        }
    }

    fun createTicket(roomId: String, category: TicketCategory, description: String) {
        viewModelScope.launch {
            val ticket = MaintenanceTicket(
                id = UUID.randomUUID().toString(),
                hotelId = "HOTEL-DEMO-001",
                roomId = "room-$roomId",
                description = description,
                category = category,
                status = TicketStatus.OPEN,
                reportedBy = "maint-01",
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
            maintenanceRepository.createTicket(ticket)
            roomRepository.updateRoomStatus("room-$roomId", RoomStatus.MAINTENANCE)
        }
    }
}
