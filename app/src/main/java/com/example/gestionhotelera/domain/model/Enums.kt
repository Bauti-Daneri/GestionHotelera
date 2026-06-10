package com.example.gestionhotelera.domain.model

enum class UserRole {
    ADMIN,
    HOUSEKEEPING,
    MAINTENANCE
}

val UserRole.displayName: String
    get() = when (this) {
        UserRole.ADMIN -> "Administrador"
        UserRole.HOUSEKEEPING -> "Limpieza"
        UserRole.MAINTENANCE -> "Mantenimiento"
    }

enum class RoomStatus {
    DIRTY,
    IN_PROGRESS,
    CLEAN,
    MAINTENANCE,
    OCCUPIED
}

val RoomStatus.displayName: String
    get() = when (this) {
        RoomStatus.DIRTY -> "Sucia"
        RoomStatus.IN_PROGRESS -> "En Proceso"
        RoomStatus.CLEAN -> "Limpia"
        RoomStatus.MAINTENANCE -> "Mantenimiento"
        RoomStatus.OCCUPIED -> "Ocupada"
    }

enum class TicketStatus {
    OPEN,
    IN_PROGRESS,
    RESOLVED,
    CLOSED
}

val TicketStatus.displayName: String
    get() = when (this) {
        TicketStatus.OPEN -> "Abierto"
        TicketStatus.IN_PROGRESS -> "En Proceso"
        TicketStatus.RESOLVED -> "Resuelto"
        TicketStatus.CLOSED -> "Cerrado"
    }

enum class TicketCategory {
    PLUMBING,
    ELECTRICAL,
    SYSTEMS
}

val TicketCategory.displayName: String
    get() = when (this) {
        TicketCategory.PLUMBING -> "Plomería"
        TicketCategory.ELECTRICAL -> "Electricidad"
        TicketCategory.SYSTEMS -> "Sistemas"
    }

enum class OrderStatus {
    PENDING,
    PREPARING,
    DELIVERED
}

val OrderStatus.displayName: String
    get() = when (this) {
        OrderStatus.PENDING -> "Pendiente"
        OrderStatus.PREPARING -> "Preparando"
        OrderStatus.DELIVERED -> "Entregado"
    }

enum class DemoRole {
    ADMIN,
    HOUSEKEEPING,
    MAINTENANCE
}

var CURRENT_DEMO_ROLE: DemoRole? = null
