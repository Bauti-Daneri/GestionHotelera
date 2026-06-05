package com.example.gestionhotelera.domain.model

data class Hotel(
    val id: String,
    val name: String,
    val address: String,
    val phone: String,
    val createdAt: Long,
    val updatedAt: Long,
    val syncStatus: SyncStatus = SyncStatus.SYNCED
)

data class User(
    val id: String,
    val hotelId: String,
    val name: String,
    val email: String,
    val role: UserRole,
    val phone: String = "",
    val department: String = "",
    val createdAt: Long,
    val updatedAt: Long,
    val syncStatus: SyncStatus = SyncStatus.SYNCED
)

data class Room(
    val id: String,
    val hotelId: String,
    val number: String,
    val type: String,
    val status: RoomStatus,
    val lastCleaned: Long,
    val createdAt: Long,
    val updatedAt: Long,
    val syncStatus: SyncStatus = SyncStatus.SYNCED
)

data class MaintenanceTicket(
    val id: String,
    val hotelId: String,
    val roomId: String,
    val description: String,
    val category: TicketCategory,
    val status: TicketStatus,
    val reportedBy: String,
    val imageUrl: String? = null,
    val createdAt: Long,
    val updatedAt: Long,
    val syncStatus: SyncStatus = SyncStatus.SYNCED
)

data class RoomServiceOrder(
    val id: String,
    val hotelId: String,
    val roomId: String,
    val description: String,
    val status: OrderStatus,
    val price: Double,
    val createdAt: Long,
    val updatedAt: Long,
    val syncStatus: SyncStatus = SyncStatus.SYNCED
)

data class RoomHousekeeperAssignment(
    val roomId: String,
    val userId: String,
    val hotelId: String,
    val createdAt: Long
)
