package com.example.gestionhotelera.ui.housekeeping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionhotelera.domain.model.*
import com.example.gestionhotelera.domain.repository.AuthRepository
import com.example.gestionhotelera.domain.repository.MaintenanceRepository
import com.example.gestionhotelera.domain.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HousekeepingViewModel @Inject constructor(
    private val roomRepository: RoomRepository,
    private val maintenanceRepository: MaintenanceRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HousekeepingUiState())
    val uiState: StateFlow<HousekeepingUiState> = _uiState.asStateFlow()

    init {
        loadRooms()
    }

    private fun loadRooms() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            // Usamos CURRENT_DEMO_ROLE como fallback si no hay una sesión activa en AuthRepository
            val userFlow = authRepository.currentUser.map { user ->
                user ?: when (CURRENT_DEMO_ROLE) {
                    DemoRole.ADMIN -> User(
                        id = "admin-01",
                        hotelId = "HOTEL-DEMO-001",
                        name = "Administrador",
                        email = "admin@hotel.com",
                        role = UserRole.ADMIN,
                        createdAt = 0,
                        updatedAt = 0
                    )
                    DemoRole.HOUSEKEEPING -> User(
                        id = "house-01",
                        hotelId = "HOTEL-DEMO-001",
                        name = "María González",
                        email = "limpieza@hotel.com",
                        role = UserRole.HOUSEKEEPING,
                        createdAt = 0,
                        updatedAt = 0
                    )
                    DemoRole.MAINTENANCE -> User(
                        id = "maint-01",
                        hotelId = "HOTEL-DEMO-001",
                        name = "Carlos Ruiz",
                        email = "mantenimiento@hotel.com",
                        role = UserRole.MAINTENANCE,
                        createdAt = 0,
                        updatedAt = 0
                    )
                    null -> null
                }
            }

            userFlow.flatMapLatest { user ->
                if (user == null) {
                    flowOf(emptyList<RoomWithHousekeepers>())
                } else {
                    _uiState.update { it.copy(userRole = user.role) }
                    val roomsFlow = if (user.role == UserRole.ADMIN) {
                        roomRepository.getRooms()
                    } else {
                        roomRepository.getRoomsForHousekeeper(user.id)
                    }

                    roomsFlow.flatMapLatest { rooms ->
                        if (rooms.isEmpty()) {
                            flowOf(emptyList())
                        } else {
                            combine(rooms.map { room ->
                                roomRepository.getAssignedHousekeepers(room.id).map { housekeepers ->
                                    RoomWithHousekeepers(room, housekeepers)
                                }
                            }) { it.toList() }
                        }
                    }
                }
            }.collect { roomsWithHousekeepers ->
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
            roomRepository.updateRoomStatus(roomId, status)
        }
    }

    fun reportMaintenance(roomId: String, category: TicketCategory, description: String) {
        viewModelScope.launch {
            val user = authRepository.currentUser.firstOrNull() ?: when (CURRENT_DEMO_ROLE) {
                DemoRole.ADMIN -> User("admin-01", "HOTEL-DEMO-001", "Administrador", "admin@hotel.com", UserRole.ADMIN, createdAt = 0, updatedAt = 0)
                DemoRole.HOUSEKEEPING -> User("house-01", "HOTEL-DEMO-001", "María González", "limpieza@hotel.com", UserRole.HOUSEKEEPING, createdAt = 0, updatedAt = 0)
                DemoRole.MAINTENANCE -> User("maint-01", "HOTEL-DEMO-001", "Carlos Ruiz", "mantenimiento@hotel.com", UserRole.MAINTENANCE, createdAt = 0, updatedAt = 0)
                null -> null
            }
            val ticket = MaintenanceTicket(
                id = UUID.randomUUID().toString(),
                hotelId = user?.hotelId ?: "HOTEL-DEMO-001",
                roomId = roomId,
                description = description,
                category = category,
                status = TicketStatus.OPEN,
                reportedBy = user?.id ?: "unknown",
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
            maintenanceRepository.createTicket(ticket)
            roomRepository.updateRoomStatus(roomId, RoomStatus.MAINTENANCE)
        }
    }
}
