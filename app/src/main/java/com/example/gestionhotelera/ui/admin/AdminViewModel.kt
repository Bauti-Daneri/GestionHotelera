package com.example.gestionhotelera.ui.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionhotelera.domain.model.*
import com.example.gestionhotelera.domain.usecase.maintenance.GetMaintenanceTicketsUseCase
import com.example.gestionhotelera.domain.usecase.room.AssignHousekeeperToRoomUseCase
import com.example.gestionhotelera.domain.usecase.user.GetUsersUseCase
import com.example.gestionhotelera.domain.usecase.room.CheckRoomNumberExistsUseCase
import com.example.gestionhotelera.domain.usecase.room.CreateRoomUseCase
import com.example.gestionhotelera.domain.usecase.room.DeleteRoomUseCase
import com.example.gestionhotelera.domain.usecase.room.GetAssignedHousekeepersUseCase
import com.example.gestionhotelera.domain.usecase.room.GetRoomsUseCase
import com.example.gestionhotelera.domain.usecase.room.RemoveHousekeeperFromRoomUseCase
import com.example.gestionhotelera.domain.usecase.room.UpdateRoomUseCase
import com.example.gestionhotelera.domain.usecase.user.CreateUserUseCase
import com.example.gestionhotelera.domain.usecase.user.DeleteUserUseCase
import com.example.gestionhotelera.domain.usecase.user.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val createRoomUseCase: CreateRoomUseCase,
    private val getRoomsUseCase: GetRoomsUseCase,
    private val updateRoomUseCase: UpdateRoomUseCase,
    private val deleteRoomUseCase: DeleteRoomUseCase,
    private val getMaintenanceTicketsUseCase: GetMaintenanceTicketsUseCase,
    private val checkRoomNumberExistsUseCase: CheckRoomNumberExistsUseCase,
    private val assignHousekeeperToRoomUseCase: AssignHousekeeperToRoomUseCase,
    private val removeHousekeeperFromRoomUseCase: RemoveHousekeeperFromRoomUseCase,
    private val getAssignedHousekeepersUseCase: GetAssignedHousekeepersUseCase
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
                getUsersUseCase(),
                getRoomsUseCase(),
                getMaintenanceTicketsUseCase()
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

    fun saveUser(name: String, email: String, role: UserRole, schedule: String, phone: String, password: String = "") {
        viewModelScope.launch {
            val user = User(
                id = UUID.randomUUID().toString(),
                hotelId = "HOTEL-DEMO-001",
                name = name,
                email = email,
                role = role,
                department = "", // Opcional, se puede inferir del rol si es necesario
                schedule = schedule,
                phone = phone,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
            createUserUseCase(user)
            _uiState.update { it.copy(successMessage = "Usuario creado correctamente") }
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            val result = updateUserUseCase(user)

            if (result.isSuccess) {
                _uiState.update {
                    it.copy(successMessage = "Usuario actualizado correctamente")
                }
            } else {
                _uiState.update {
                    it.copy(
                        error = result.exceptionOrNull()?.message ?: "Error al actualizar usuario"
                    )
                }
            }
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            val result = deleteUserUseCase(user)

            if (result.isFailure) {
                _uiState.update {
                    it.copy(
                        error = result.exceptionOrNull()?.message ?: "Error al eliminar usuario"
                    )
                }
            }
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
                floor = floor,
                type = type,
                status = status,
                lastCleaned = System.currentTimeMillis(),
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
            createRoomUseCase(room)
            _uiState.update { it.copy(error = null, successMessage = "Habitación creada correctamente") }
        }
    }

    fun updateRoom(room: Room) {
        viewModelScope.launch {
            updateRoomUseCase(room)
        }
    }

    fun deleteRoom(room: Room) {
        viewModelScope.launch {
            val result = deleteRoomUseCase(room)

            if (result.isFailure) {
                _uiState.update {
                    it.copy(
                        error = result.exceptionOrNull()?.message ?: "Error al eliminar habitación"
                    )
                }
            }
        }
    }
    
    fun getHousekeepersForRoom(roomId: String): Flow<List<User>> = 
        getAssignedHousekeepersUseCase(roomId)
        
    fun assignHousekeeper(roomId: String, userId: String) {
        viewModelScope.launch {
            val result = assignHousekeeperToRoomUseCase(roomId, userId)
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

    fun clearSuccessMessage() {
        _uiState.update { it.copy(successMessage = null) }
    }
}
