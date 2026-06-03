package com.example.gestionhotelera.data.repository

import com.example.gestionhotelera.data.local.MaintenanceTicketDao
import com.example.gestionhotelera.data.mapper.toDomain
import com.example.gestionhotelera.data.mapper.toEntity
import com.example.gestionhotelera.domain.model.MaintenanceTicket
import com.example.gestionhotelera.domain.model.SyncStatus
import com.example.gestionhotelera.domain.model.TicketStatus
import com.example.gestionhotelera.domain.repository.MaintenanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MaintenanceRepositoryImpl @Inject constructor(
    private val ticketDao: MaintenanceTicketDao
) : MaintenanceRepository {
    override fun getTickets(): Flow<List<MaintenanceTicket>> = ticketDao.getAllTickets().map { entities ->
        entities.map { it.toDomain() }
    }

    override suspend fun getTicketById(id: String): MaintenanceTicket? = ticketDao.getTicketById(id)?.toDomain()

    override suspend fun createTicket(ticket: MaintenanceTicket) {
        ticketDao.insertTicket(ticket.toEntity(isDirty = true).copy(syncStatus = SyncStatus.PENDING))
    }

    override suspend fun updateTicketStatus(ticketId: String, status: TicketStatus) {
        val ticket = ticketDao.getTicketById(ticketId)
        ticket?.let {
            val updated = it.copy(
                status = status,
                updatedAt = System.currentTimeMillis(),
                isDirty = true,
                syncStatus = SyncStatus.PENDING
            )
            ticketDao.updateTicket(updated)
        }
    }
}
