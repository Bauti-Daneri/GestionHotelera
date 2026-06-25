package com.hotelops.domain.usecase.maintenance

import com.hotelops.domain.model.MaintenanceTicket
import com.hotelops.domain.model.TicketStatus
import com.hotelops.domain.repository.MaintenanceRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateTicketStatusUseCase @Inject constructor(
    private val maintenanceRepository: MaintenanceRepository
) {
    operator fun invoke(
        ticketId: String,
        status: TicketStatus,
        assignedTo: String?
    ): Flow<Resource<MaintenanceTicket>> {
        return maintenanceRepository.updateTicketStatus(ticketId, status, assignedTo)
    }
}
