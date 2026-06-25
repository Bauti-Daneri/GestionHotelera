package com.hotelops.domain.usecase.maintenance

import com.hotelops.domain.model.MaintenanceTicket
import com.hotelops.domain.repository.MaintenanceRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTicketsUseCase @Inject constructor(
    private val maintenanceRepository: MaintenanceRepository
) {
    operator fun invoke(hotelId: String): Flow<Resource<List<MaintenanceTicket>>> {
        return maintenanceRepository.getTickets(hotelId)
    }
}
