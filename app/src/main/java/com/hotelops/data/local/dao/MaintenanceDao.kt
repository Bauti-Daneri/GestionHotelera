package com.hotelops.data.local.dao

import androidx.room.*
import com.hotelops.data.local.entity.MaintenanceTicketEntity
import com.hotelops.domain.model.TicketCategory
import com.hotelops.domain.model.TicketStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface MaintenanceDao {

    @Query("SELECT * FROM maintenance_tickets WHERE hotelId = :hotelId ORDER BY createdAt DESC")
    fun getTicketsByHotel(hotelId: String): Flow<List<MaintenanceTicketEntity>>

    @Query("SELECT * FROM maintenance_tickets WHERE id = :ticketId")
    fun getTicketById(ticketId: String): Flow<MaintenanceTicketEntity?>

    @Query("SELECT * FROM maintenance_tickets WHERE hotelId = :hotelId AND status = :status ORDER BY createdAt DESC")
    fun getTicketsByStatus(hotelId: String, status: TicketStatus): Flow<List<MaintenanceTicketEntity>>

    @Query("SELECT * FROM maintenance_tickets WHERE hotelId = :hotelId AND category = :category ORDER BY createdAt DESC")
    fun getTicketsByCategory(hotelId: String, category: TicketCategory): Flow<List<MaintenanceTicketEntity>>

    @Query("SELECT * FROM maintenance_tickets WHERE hotelId = :hotelId AND assignedTo = :userId ORDER BY createdAt DESC")
    fun getTicketsByAssignedUser(hotelId: String, userId: String): Flow<List<MaintenanceTicketEntity>>

    @Query("SELECT * FROM maintenance_tickets WHERE hotelId = :hotelId AND roomId = :roomId ORDER BY createdAt DESC")
    fun getTicketsByRoom(hotelId: String, roomId: String): Flow<List<MaintenanceTicketEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicket(ticket: MaintenanceTicketEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTickets(tickets: List<MaintenanceTicketEntity>)

    @Update
    suspend fun updateTicket(ticket: MaintenanceTicketEntity)

    @Delete
    suspend fun deleteTicket(ticket: MaintenanceTicketEntity)

    @Query("DELETE FROM maintenance_tickets WHERE id = :ticketId")
    suspend fun deleteTicketById(ticketId: String)

    @Query("SELECT * FROM maintenance_tickets WHERE isDirty = 1")
    suspend fun getDirtyTickets(): List<MaintenanceTicketEntity>

    @Query("UPDATE maintenance_tickets SET syncedAt = :syncedAt, isDirty = 0 WHERE id = :ticketId")
    suspend fun markAsSynced(ticketId: String, syncedAt: Long)

    @Query("UPDATE maintenance_tickets SET status = :status, updatedAt = :updatedAt, completedAt = :completedAt, isDirty = 1 WHERE id = :ticketId")
    suspend fun updateTicketStatus(ticketId: String, status: TicketStatus, updatedAt: Long, completedAt: Long?)

    @Query("DELETE FROM maintenance_tickets WHERE hotelId = :hotelId")
    suspend fun deleteTicketsByHotel(hotelId: String)
}
