package com.example.gestionhotelera.ui.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionhotelera.domain.model.*
import com.example.gestionhotelera.domain.repository.MaintenanceRepository
import com.example.gestionhotelera.domain.repository.RoomRepository
import com.example.gestionhotelera.domain.repository.UserRepository
import com.example.gestionhotelera.domain.usecase.room.CheckRoomNumberExistsUseCase
import com.example.gestionhotelera.domain.usecase.room.CreateRoomUseCase
import com.example.gestionhotelera.domain.usecase.room.DeleteRoomUseCase
import com.example.gestionhotelera.domain.usecase.room.UpdateRoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val roomRepository: RoomRepository,
    private val maintenanceRepository: MaintenanceRepository,
    private val createRoomUseCase: CreateRoomUseCase,
    private val updateRoomUseCase: UpdateRoomUseCase,
    private val deleteRoomUseCase: DeleteRoomUseCase,
    private val checkRoomNumberExistsUseCase: CheckRoomNumberExistsUseCase,
    private val assignHousekeeperToRoomUseCase: com.example.gestionhotelera.domain.usecase.room.AssignHousekeeperToRoomUseCase,
    private val removeHousekeeperFromRoomUseCase: com.example.gestionhotelera.domain.usecase.room.RemoveHousekeeperFromRoomUseCase,
    private val getAssignedHousekeepersUseCase: com.example.gestionhotelera.domain.usecase.room.GetAssignedHousekeepersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AdminUiState())
    val uiState: StateFlow<AdminUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            combine(
                userRepository.getUsers(),
                roomRepository.getRooms(),
                maintenanceRepository.getTickets()
            ) { users, rooms, tickets ->
                val openTickets = tickets.count { it.status == TicketStatus.OPEN }
                val occupiedCount = rooms.count { it.status == RoomStatus.OCCUPIED }
                val occupancy = if (rooms.isNotEmpty()) (occupiedCount * 100) / rooms.size else 0
                
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        users = users,
                        rooms = rooms,
                        openTicketsCount = openTickets,
                        occupancyRate = occupancy
                    )
                }
            }.collect()
        }
    }

    fun saveUser(name: String, email: String, role: UserRole, department: String, phone: String) {
        viewModelScope.launch {
            val user = User(
                id = UUID.randomUUID().toString(),
                hotelId = "HOTEL-DEMO-001",
                name = name,
                email = email,
                role = role,
                department = department,
                phone = phone,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
            userRepository.saveUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userRepository.deleteUser(user)
        }
    }

    fun createRoom(number: String, floor: String, type: String, status: RoomStatus) {
        viewModelScope.launch {
            if (checkRoomNumberExistsUseCase(number, "HOTEL-DEMO-001")) {
                _uiState.update { it.copy(error = "Ya existe una habitación con ese número") }
                return@launch
            }
            
            val room = Room(
                id = "room-$number",
                hotelId = "HOTEL-DEMO-001",
                number = number,
                type = type,
                status = status,
                lastCleaned = System.currentTimeMillis(),
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
            createRoomUseCase(room)
            _uiState.update { it.copy(error = null) }
        }
    }

    fun updateRoom(room: Room) {
        viewModelScope.launch {
            updateRoomUseCase(room)
        }
    }

    fun deleteRoom(room: Room) {
        viewModelScope.launch {
            deleteRoomUseCase(room)
        }
    }
    
    fun getHousekeepersForRoom(roomId: String): Flow<List<User>> = 
        getAssignedHousekeepersUseCase(roomId)
        
    fun assignHousekeeper(roomId: String, userId: String) {
        viewModelScope.launch {
            val result = assignHousekeeperToRoomUseCase(roomId, userId, "HOTEL-DEMO-001")
            if (result.isFailure) {
                _uiState.update { it.copy(error = result.exceptionOrNull()?.message) }
            } else {
                _uiState.update { it.copy(error = null) }
            }
        }
    }
    
    fun removeHousekeeper(roomId: String, userId: String) {
        viewModelScope.launch {
            removeHousekeeperFromRoomUseCase(roomId, userId)
        }
    }
    
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
