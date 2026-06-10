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
    val dirtyRoomsCount: Int = 0,
    val occupancyRate: Int = 0,
    val pendingOrdersCount: Int = 0,
    val preparingOrdersCount: Int = 0,
    val error: String? = null,
    val successMessage: String? = null,
    
    // Errores de validación para el formulario de empleado
    val nameError: String? = null,
    val emailError: String? = null,
    val phoneError: String? = null,
    val passwordError: String? = null,
    val roleError: String? = null,
    val shiftError: String? = null
)
