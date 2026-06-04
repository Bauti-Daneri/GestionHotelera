package com.example.gestionhotelera.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gestionhotelera.domain.model.*

@Entity(tableName = "hotels")
data class HotelEntity(
    @PrimaryKey val id: String,
    val name: String,
    val address: String,
    val phone: String,
    val createdAt: Long,
    val updatedAt: Long,
    val isDirty: Boolean,
    val syncStatus: SyncStatus
)

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val hotelId: String,
    val name: String,
    val email: String,
    val role: UserRole,
    val phone: String = "",
    val department: String = "",
    val createdAt: Long,
    val updatedAt: Long,
    val isDirty: Boolean,
    val syncStatus: SyncStatus
)

@Entity(tableName = "rooms")
data class RoomEntity(
    @PrimaryKey val id: String,
    val hotelId: String,
    val number: String,
    val type: String,
    val status: RoomStatus,
    val lastCleaned: Long,
    val createdAt: Long,
    val updatedAt: Long,
    val isDirty: Boolean,
    val syncStatus: SyncStatus
)

@Entity(tableName = "maintenance_tickets")
data class MaintenanceTicketEntity(
    @PrimaryKey val id: String,
    val hotelId: String,
    val roomId: String,
    val description: String,
    val category: TicketCategory,
    val status: TicketStatus,
    val reportedBy: String,
    val imageUrl: String?,
    val createdAt: Long,
    val updatedAt: Long,
    val isDirty: Boolean,
    val syncStatus: SyncStatus
)

@Entity(tableName = "room_service_orders")
data class RoomServiceOrderEntity(
    @PrimaryKey val id: String,
    val hotelId: String,
    val roomId: String,
    val status: OrderStatus,
    val totalPrice: Double,
    val createdAt: Long,
    val updatedAt: Long,
    val isDirty: Boolean,
    val syncStatus: SyncStatus
)

@Entity(tableName = "room_service_items")
data class RoomServiceItemEntity(
    @PrimaryKey val id: String,
    val orderId: String,
    val name: String,
    val price: Double,
    val quantity: Int
)

@Entity(tableName = "room_housekeeper_assignments", primaryKeys = ["roomId", "userId"])
data class RoomHousekeeperAssignmentEntity(
    val roomId: String,
    val userId: String,
    val hotelId: String,
    val createdAt: Long
)

@Entity(tableName = "room_service_menu_items")
data class RoomServiceMenuItemEntity(
    @PrimaryKey val id: String,
    val hotelId: String,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val isAvailable: Boolean,
    val createdAt: Long,
    val updatedAt: Long,
    val isDirty: Boolean,
    val syncStatus: SyncStatus
)
