package com.hotelops.domain.model

enum class UserRole {
    ADMIN,
    HOUSEKEEPING,
    MAINTENANCE;

    fun toDisplayName(): String = when (this) {
        ADMIN -> "Administrador"
        HOUSEKEEPING -> "Limpieza"
        MAINTENANCE -> "Mantenimiento"
    }
}
