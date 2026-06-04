package com.example.gestionhotelera.ui.housekeeping

import com.example.gestionhotelera.domain.model.Room
import com.example.gestionhotelera.domain.model.User

data class RoomWithHousekeepers(
    val room: Room,
    val housekeepers: List<User> = emptyList()
)

data class HousekeepingUiState(
    val isLoading: Boolean = false,
    val rooms: List<RoomWithHousekeepers> = emptyList(),
    val dirtyCount: Int = 0,
    val inProgressCount: Int = 0,
    val cleanCount: Int = 0,
    val error: String? = null,
    val userRole: com.example.gestionhotelera.domain.model.UserRole? = null
)
