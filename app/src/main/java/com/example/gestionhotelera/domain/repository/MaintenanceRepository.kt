package com.example.gestionhotelera.domain.repository

import com.example.gestionhotelera.domain.model.MaintenanceTicket
import com.example.gestionhotelera.domain.model.TicketStatus
import kotlinx.coroutines.flow.Flow

interface MaintenanceRepository {
    fun getTickets(): Flow<List<MaintenanceTicket>>
    suspend fun getTicketById(id: String): MaintenanceTicket?
    suspend fun createTicket(ticket: MaintenanceTicket)
    suspend fun updateTicketStatus(ticketId: String, status: TicketStatus)
}
