package com.example.gestionhotelera.ui.housekeeping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionhotelera.domain.model.*
import com.example.gestionhotelera.domain.usecase.auth.GetActiveUserUseCase
import com.example.gestionhotelera.domain.usecase.maintenance.CreateMaintenanceTicketUseCase
import com.example.gestionhotelera.domain.usecase.room.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HousekeepingViewModel @Inject constructor(
    private val getRoomsUseCase: GetRoomsUseCase,
    private val getRoomsForHousekeeperUseCase: GetRoomsForHousekeeperUseCase,
    private val getAssignedHousekeepersUseCase: GetAssignedHousekeepersUseCase,
    private val updateRoomStatusUseCase: UpdateRoomStatusUseCase,
    private val createMaintenanceTicketUseCase: CreateMaintenanceTicketUseCase,
    private val getActiveUserUseCase: GetActiveUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HousekeepingUiState())
    val uiState: StateFlow<HousekeepingUiState> = _uiState.asStateFlow()

    init {
        loadRooms()
    }

    private fun loadRooms() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            getActiveUserUseCase()
                .flatMapLatest { user ->
                    if (user == null) {
                        flowOf(emptyList<RoomWithHousekeepers>())
                    } else {
                        _uiState.update { it.copy(userRole = user.role) }

                        val roomsFlow = if (user.role == UserRole.ADMIN) {
                            getRoomsUseCase()
                        } else {
                            getRoomsForHousekeeperUseCase(user.id)
                        }

                        roomsFlow.flatMapLatest { rooms ->
                            if (rooms.isEmpty()) {
                                flowOf(emptyList())
                            } else {
                                combine(
                                    rooms.map { room ->
                                        getAssignedHousekeepersUseCase(room.id).map { housekeepers ->
                                            RoomWithHousekeepers(room, housekeepers)
                                        }
                                    }
                                ) { it.toList() }
                            }
                        }
                    }
                }
                .collect { roomsWithHousekeepers ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            rooms = roomsWithHousekeepers,
                            dirtyCount = roomsWithHousekeepers.count { it.room.status == RoomStatus.DIRTY },
                            inProgressCount = roomsWithHousekeepers.count { it.room.status == RoomStatus.IN_PROGRESS },
                            cleanCount = roomsWithHousekeepers.count { it.room.status == RoomStatus.CLEAN }
                        )
                    }
                }
        }
    }

    fun updateRoomStatus(roomId: String, status: RoomStatus) {
        viewModelScope.launch {
            updateRoomStatusUseCase(roomId, status)
        }
    }

    fun reportMaintenance(
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
                roomId = roomId,
                description = description,
                category = category,
                status = TicketStatus.OPEN,
                reportedBy = user?.id ?: "unknown",
                imageUrl = imageUrl,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )

            createMaintenanceTicketUseCase(ticket)
            updateRoomStatusUseCase(roomId, RoomStatus.MAINTENANCE)
        }
    }
}