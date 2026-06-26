package com.hotelops.app.domain.models

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Modelo de dominio puro para Reserva
 */
data class Booking(
    val id: String,
    val hotelId: String,
    val roomId: String,
    val userId: String,
    val tenantId: String,
    val checkInDate: LocalDate,
    val checkOutDate: LocalDate,
    val guestName: String,
    val guestEmail: String,
    val guestPhone: String,
    val totalPrice: Double,
    val status: BookingStatus,
    val specialRequests: String? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

enum class BookingStatus {
    PENDING,        // Pendiente de confirmación
    CONFIRMED,      // Confirmada
    CHECKED_IN,     // Check-in realizado
    CHECKED_OUT,    // Check-out realizado
    CANCELLED,      // Cancelada
    NO_SHOW         // No se presentó
}

