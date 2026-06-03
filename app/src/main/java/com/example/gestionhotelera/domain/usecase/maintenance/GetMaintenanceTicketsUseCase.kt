package com.example.gestionhotelera.domain.usecase.maintenance

import com.example.gestionhotelera.domain.model.MaintenanceTicket
import com.example.gestionhotelera.domain.repository.MaintenanceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMaintenanceTicketsUseCase @Inject constructor(
    private val repository: MaintenanceRepository
) {
    operator fun invoke(): Flow<List<MaintenanceTicket>> = repository.getTickets()
}
