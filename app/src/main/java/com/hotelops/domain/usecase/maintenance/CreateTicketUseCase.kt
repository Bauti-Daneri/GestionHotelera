package com.hotelops.domain.usecase.maintenance

import com.hotelops.domain.model.MaintenanceTicket
import com.hotelops.domain.model.TicketCategory
import com.hotelops.domain.repository.MaintenanceRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateTicketUseCase @Inject constructor(
    private val maintenanceRepository: MaintenanceRepository
) {
    operator fun invoke(
        hotelId: String,
        roomId: String,
        title: String,
        description: String,
        category: TicketCategory,
        priority: String,
        reportedBy: String,
        imageUrl: String? = null
    ): Flow<Resource<MaintenanceTicket>> {
        return maintenanceRepository.createTicket(
            hotelId = hotelId,
            roomId = roomId,
            title = title,
            description = description,
            category = category,
            priority = priority,
            reportedBy = reportedBy,
            imageUrl = imageUrl
        )
    }
}
