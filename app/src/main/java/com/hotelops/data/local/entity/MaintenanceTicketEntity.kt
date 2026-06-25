package com.hotelops.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hotelops.domain.model.TicketCategory
import com.hotelops.domain.model.TicketStatus

@Entity(tableName = "maintenance_tickets")
data class MaintenanceTicketEntity(
    @PrimaryKey val id: String,
    val hotelId: String,
    val roomId: String,
    val roomNumber: String,
    val title: String,
    val description: String,
    val category: TicketCategory,
    val status: TicketStatus,
    val priority: String,
    val reportedBy: String,
    val reportedByName: String,
    val assignedTo: String?,
    val assignedToName: String?,
    val imageUrl: String?,
    val createdAt: Long,
    val updatedAt: Long,
    val completedAt: Long?,
    val syncedAt: Long? = null,
    val isDirty: Boolean = false
)
