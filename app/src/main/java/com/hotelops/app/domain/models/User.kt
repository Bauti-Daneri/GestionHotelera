package com.hotelops.app.domain.models

import java.time.LocalDateTime

/**
 * Modelo de dominio puro para Usuario del sistema
 */
data class User(
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val role: UserRole,
    val tenantId: String,
    val photoUrl: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val isActive: Boolean = true
)

enum class UserRole {
    ADMIN,          // Administrador del tenant
    MANAGER,        // Gerente
    STAFF,          // Personal
    GUEST           // Huésped
}

