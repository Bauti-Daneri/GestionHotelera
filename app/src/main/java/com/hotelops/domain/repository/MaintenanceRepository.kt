package com.hotelops.domain.repository

import com.hotelops.domain.model.MaintenanceTicket
import com.hotelops.domain.model.TicketCategory
import com.hotelops.domain.model.TicketStatus
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface MaintenanceRepository {
    fun getTickets(hotelId: String): Flow<Resource<List<MaintenanceTicket>>>

    fun getTicketById(ticketId: String): Flow<Resource<MaintenanceTicket>>

    fun createTicket(
        hotelId: String,
        roomId: String,
        title: String,
        description: String,
        category: TicketCategory,
        priority: String,
        reportedBy: String,
        imageUrl: String?
    ): Flow<Resource<MaintenanceTicket>>

    fun updateTicketStatus(
        ticketId: String,
        status: TicketStatus,
        assignedTo: String?
    ): Flow<Resource<MaintenanceTicket>>
}
