package com.example.gestionhotelera.domain.usecase.maintenance

import com.example.gestionhotelera.domain.model.MaintenanceTicket
import com.example.gestionhotelera.domain.repository.MaintenanceRepository
import javax.inject.Inject

class GetTicketByIdUseCase @Inject constructor(
    private val repository: MaintenanceRepository
) {
    suspend operator fun invoke(id: String): MaintenanceTicket? = repository.getTicketById(id)
}
