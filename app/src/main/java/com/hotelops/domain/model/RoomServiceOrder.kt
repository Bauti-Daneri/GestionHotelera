package com.hotelops.domain.model

import kotlinx.serialization.Serializable

enum class OrderStatus {
    PENDING,
    PREPARING,
    READY,
    DELIVERED
}

@Serializable
data class OrderItem(
    val name: String,
    val quantity: Int,
    val price: Double
)

data class RoomServiceOrder(
    val id: String,
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
    val deliveredAt: Long?
)
