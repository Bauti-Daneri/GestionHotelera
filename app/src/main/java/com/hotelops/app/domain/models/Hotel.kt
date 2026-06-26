package com.hotelops.app.domain.models

import java.time.LocalDateTime

/**
 * Modelo de dominio puro para Hotel
 */
data class Hotel(
    val id: String,
    val tenantId: String,
    val name: String,
    val description: String?,
    val address: String,
    val city: String,
    val country: String,
    val phoneNumber: String?,
    val email: String?,
    val website: String?,
    val rating: Double = 0.0,
    val totalRooms: Int = 0,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val isActive: Boolean = true
)

