package com.example.gestionhotelera.domain.usecase.maintenance

import com.example.gestionhotelera.domain.model.MaintenanceTicket
import com.example.gestionhotelera.domain.repository.MaintenanceRepository
import javax.inject.Inject

class CreateMaintenanceTicketUseCase @Inject constructor(
    private val repository: MaintenanceRepository
) {
    suspend operator fun invoke(ticket: MaintenanceTicket) = repository.createTicket(ticket)
}
