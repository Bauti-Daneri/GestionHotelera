package com.hotelops.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.hotelops.data.local.dao.MaintenanceDao
import com.hotelops.data.local.entity.MaintenanceTicketEntity
import com.hotelops.data.mapper.toDomain
import com.hotelops.domain.model.MaintenanceTicket
import com.hotelops.domain.model.TicketCategory
import com.hotelops.domain.model.TicketStatus
import com.hotelops.domain.repository.MaintenanceRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class MaintenanceRepositoryImpl @Inject constructor(
    private val maintenanceDao: MaintenanceDao,
    private val firestore: FirebaseFirestore
) : MaintenanceRepository {

    companion object {
        private const val COL_TICKETS = "maintenance_tickets"
    }

    override fun getTickets(hotelId: String): Flow<Resource<List<MaintenanceTicket>>> {
        return maintenanceDao.getTicketsByHotel(hotelId).map { entities ->
            Resource.Success(entities.map { it.toDomain() })
        }
    }

    override fun getTicketById(ticketId: String): Flow<Resource<MaintenanceTicket>> {
        return maintenanceDao.getTicketById(ticketId).map { entity ->
            if (entity != null) Resource.Success(entity.toDomain())
            else Resource.Error("Ticket no encontrado")
        }
    }

    override fun createTicket(
        hotelId: String,
        roomId: String,
        title: String,
        description: String,
        category: TicketCategory,
        priority: String,
        reportedBy: String,
        imageUrl: String?
    ): Flow<Resource<MaintenanceTicket>> = flow {
        emit(Resource.Loading())
        try {
            val ticketId = UUID.randomUUID().toString()
            val now = System.currentTimeMillis()

            // Obtener número de habitación desde Firestore o usar roomId como fallback
            val roomNumber = try {
                val roomDoc = firestore.collection("rooms").document(roomId).get().await()
                roomDoc.getString("roomNumber") ?: roomId
            } catch (e: Exception) { roomId }

            val entity = MaintenanceTicketEntity(
                id = ticketId,
                hotelId = hotelId,
                roomId = roomId,
                roomNumber = roomNumber,
                title = title,
                description = description,
                category = category,
                status = TicketStatus.PENDING,
                priority = priority,
                reportedBy = reportedBy,
                reportedByName = "",
                assignedTo = null,
                assignedToName = null,
                imageUrl = imageUrl,
                createdAt = now,
                updatedAt = now,
                completedAt = null,
                isDirty = true
            )

            // 1. Guardar local (offline-first)
            maintenanceDao.insertTicket(entity)

            // 2. Sincronizar con Firestore
            val data = mapOf(
                "id" to ticketId,
                "hotelId" to hotelId,
                "roomId" to roomId,
                "roomNumber" to roomNumber,
                "title" to title,
                "description" to description,
                "category" to category.name,
                "status" to TicketStatus.PENDING.name,
                "priority" to priority,
                "reportedBy" to reportedBy,
                "imageUrl" to (imageUrl ?: ""),
                "createdAt" to now,
                "updatedAt" to now
            )
            firestore.collection(COL_TICKETS).document(ticketId).set(data).await()
            maintenanceDao.markAsSynced(ticketId, System.currentTimeMillis())

            emit(Resource.Success(entity.toDomain()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al crear ticket"))
        }
    }

    override fun updateTicketStatus(
        ticketId: String,
        status: TicketStatus,
        assignedTo: String?
    ): Flow<Resource<MaintenanceTicket>> = flow {
        emit(Resource.Loading())
        try {
            val now = System.currentTimeMillis()
            val completedAt = if (status == TicketStatus.COMPLETED) now else null

            // 1. Actualizar local
            maintenanceDao.updateTicketStatus(ticketId, status, now, completedAt)

            // 2. Sincronizar con Firestore
            val update = mutableMapOf<String, Any>(
                "status" to status.name,
                "updatedAt" to now
            )
            completedAt?.let { update["completedAt"] = it }
            assignedTo?.let { update["assignedTo"] = it }
            firestore.collection(COL_TICKETS).document(ticketId).update(update).await()

            val entity = maintenanceDao.getTicketById(ticketId).first()
            if (entity != null) emit(Resource.Success(entity.toDomain()))
        } catch (e: Exception) {
            val entity = maintenanceDao.getTicketById(ticketId).first()
            if (entity != null) emit(Resource.Success(entity.toDomain()))
            else emit(Resource.Error(e.message ?: "Error al actualizar ticket"))
        }
    }

    override fun deleteTicket(ticketId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            maintenanceDao.deleteTicketById(ticketId)
            firestore.collection(COL_TICKETS).document(ticketId).delete().await()
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al eliminar ticket"))
        }
    }
}
