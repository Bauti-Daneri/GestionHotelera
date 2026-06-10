package com.example.gestionhotelera.ui.profile

import com.example.gestionhotelera.domain.model.User

data class ProfileUiState(
    val user: User? = null,
    val hotelName: String = "",
    val hotelId: String = "",
    val isDarkMode: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val totalTickets: Int = 12,
    val completedTickets: Int = 8,
    val successMessage: String? = null
)
