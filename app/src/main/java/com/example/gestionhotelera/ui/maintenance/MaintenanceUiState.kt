package com.example.gestionhotelera.ui.maintenance

import com.example.gestionhotelera.domain.model.MaintenanceTicket
import com.example.gestionhotelera.domain.model.TicketCategory

data class MaintenanceUiState(
    val isLoading: Boolean = false,
    val tickets: List<MaintenanceTicket> = emptyList(),
    val filter: TicketCategory? = null,
    val openCount: Int = 0,
    val error: String? = null
)
