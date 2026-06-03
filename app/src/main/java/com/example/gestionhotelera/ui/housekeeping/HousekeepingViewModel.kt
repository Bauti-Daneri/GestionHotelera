package com.example.gestionhotelera.ui.housekeeping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionhotelera.domain.model.MaintenanceTicket
import com.example.gestionhotelera.domain.model.RoomStatus
import com.example.gestionhotelera.domain.model.TicketCategory
import com.example.gestionhotelera.domain.model.TicketStatus
import com.example.gestionhotelera.domain.repository.MaintenanceRepository
import com.example.gestionhotelera.domain.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HousekeepingViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val maintenanceRepository: MaintenanceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HousekeepingUiState())
    val uiState: StateFlow<HousekeepingUiState> = _uiState.asStateFlow()

    init {
        loadRooms()
    }

    private fun loadRooms() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            roomRepository.getRooms().collect { rooms ->
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        rooms = rooms,
                        dirtyCount = rooms.count { it.status == RoomStatus.DIRTY },
                        inProgressCount = rooms.count { it.status == RoomStatus.IN_PROGRESS },
                        cleanCount = rooms.count { it.status == RoomStatus.CLEAN }
                    )
                }
            }
        }
    }

    fun updateRoomStatus(roomId: String, status: RoomStatus) {
        viewModelScope.launch {
            roomRepository.updateRoomStatus(roomId, status)
        }
    }

    fun reportMaintenance(roomId: String, category: TicketCategory, description: String) {
        viewModelScope.launch {
            val ticket = MaintenanceTicket(
                id = UUID.randomUUID().toString(),
                hotelId = "HOTEL-DEMO-001",
                roomId = roomId,
                description = description,
                category = category,
                status = TicketStatus.OPEN,
                reportedBy = "house-01", // Demo user
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
            maintenanceRepository.createTicket(ticket)
            roomRepository.updateRoomStatus(roomId, RoomStatus.MAINTENANCE)
        }
    }
}
