package com.example.gestionhotelera.ui.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestionhotelera.domain.model.SyncStatus
import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.model.UserRole
import com.example.gestionhotelera.domain.repository.MaintenanceRepository
import com.example.gestionhotelera.domain.repository.RoomRepository
import com.example.gestionhotelera.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val roomRepository: RoomRepository,
    private val maintenanceRepository: MaintenanceRepository
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
                val openTickets = tickets.count { it.status.name == "OPEN" }
                val occupiedCount = rooms.count { it.status.name == "OCCUPIED" }
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
}
