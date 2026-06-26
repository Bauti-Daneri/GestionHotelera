package com.hotelops.app.domain.models

import java.time.LocalDateTime

/**
 * Modelo de dominio puro para Habitación de Hotel
 */
data class Room(
    val id: String,
    val hotelId: String,
    val roomNumber: String,
    val roomType: RoomType,
    val capacity: Int,
    val pricePerNight: Double,
    val description: String?,
    val amenities: List<String> = emptyList(),
    val isAvailable: Boolean = true,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

enum class RoomType {
    SINGLE,         // Habitación individual
    DOUBLE,         // Habitación doble
    SUITE,          // Suite
    DELUXE,         // Suite deluxe
    FAMILY          // Habitación familiar
}

