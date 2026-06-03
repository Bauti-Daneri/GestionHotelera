package com.example.gestionhotelera.domain.usecase.maintenance

import com.example.gestionhotelera.domain.model.TicketStatus
import com.example.gestionhotelera.domain.repository.MaintenanceRepository
import javax.inject.Inject

class UpdateTicketStatusUseCase @Inject constructor(
    private val repository: MaintenanceRepository
) {
    suspend operator fun invoke(ticketId: String, status: TicketStatus) {
        repository.updateTicketStatus(ticketId, status)
    }
}
