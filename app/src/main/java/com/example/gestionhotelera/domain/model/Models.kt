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
    val items: List<RoomServiceItem>,
    val status: OrderStatus,
    val totalPrice: Double,
    val createdAt: Long,
    val updatedAt: Long,
    val syncStatus: SyncStatus = SyncStatus.SYNCED
)

data class RoomServiceItem(
    val id: String,
    val name: String,
    val price: Double,
    val quantity: Int
)
