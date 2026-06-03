package com.example.gestionhotelera.ui.housekeeping

import com.example.gestionhotelera.domain.model.Room

data class HousekeepingUiState(
    val isLoading: Boolean = false,
    val rooms: List<Room> = emptyList(),
    val dirtyCount: Int = 0,
    val inProgressCount: Int = 0,
    val cleanCount: Int = 0,
    val error: String? = null
)
