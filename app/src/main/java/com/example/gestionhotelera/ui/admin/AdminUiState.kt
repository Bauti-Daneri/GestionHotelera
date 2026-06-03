package com.example.gestionhotelera.ui.admin

import com.example.gestionhotelera.domain.model.Hotel
import com.example.gestionhotelera.domain.model.Room
import com.example.gestionhotelera.domain.model.User

data class AdminUiState(
    val isLoading: Boolean = false,
    val hotel: Hotel? = null,
    val users: List<User> = emptyList(),
    val rooms: List<Room> = emptyList(),
    val openTicketsCount: Int = 0,
    val occupancyRate: Int = 0,
    val error: String? = null
)
