package com.hotelops.domain.model

enum class TicketCategory {
    PLUMBING,
    ELECTRICAL,
    HVAC,
    FURNITURE,
    OTHER
}

enum class TicketStatus {
    PENDING,
    IN_PROGRESS,
    COMPLETED
}

data class MaintenanceTicket(
    val id: String,
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
    val completedAt: Long?
)
