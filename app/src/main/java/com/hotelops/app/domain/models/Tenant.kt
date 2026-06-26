package com.hotelops.app.domain.models

import java.time.LocalDateTime

/**
 * Modelo de dominio puro para Tenant (empresa hotelera)
 * Sin anotaciones de BD, API ni framework.
 */
data class Tenant(
    val id: String,
    val name: String,
    val email: String,
    val phone: String?,
    val address: String?,
    val city: String?,
    val country: String?,
    val logo: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val isActive: Boolean = true
)

