package com.hotelops.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hotelops.domain.model.OrderItem
import com.hotelops.domain.model.OrderStatus

@Entity(tableName = "room_service_orders")
data class RoomServiceOrderEntity(
    @PrimaryKey val id: String,
    val hotelId: String,
    val roomId: String,
    val roomNumber: String,
    val guestName: String,
    val items: List<OrderItem>,
    val status: OrderStatus,
    val totalAmount: Double,
    val specialInstructions: String?,
    val createdAt: Long,
    val updatedAt: Long,
    val deliveredAt: Long?,
    val syncedAt: Long? = null,
    val isDirty: Boolean = false
)
