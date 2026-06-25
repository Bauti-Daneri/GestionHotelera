package com.hotelops.domain.usecase.maintenance

import com.hotelops.domain.repository.MaintenanceRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteTicketUseCase @Inject constructor(
    private val repository: MaintenanceRepository
) {
    operator fun invoke(ticketId: String): Flow<Resource<Unit>> {
        return repository.deleteTicket(ticketId)
    }
}
