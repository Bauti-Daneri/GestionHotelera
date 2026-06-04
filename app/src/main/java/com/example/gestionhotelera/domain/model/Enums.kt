package com.example.gestionhotelera.domain.model

enum class UserRole {
    ADMIN,
    HOUSEKEEPING,
    MAINTENANCE
}

enum class RoomStatus {
    DIRTY,
    IN_PROGRESS,
    CLEAN,
    MAINTENANCE,
    OCCUPIED
}

enum class TicketStatus {
    OPEN,
    IN_PROGRESS,
    RESOLVED,
    CLOSED
}

enum class TicketCategory {
    PLUMBING,
    ELECTRICAL,
    SYSTEMS
}

enum class OrderStatus {
    PENDING,
    PREPARING,
    DELIVERED
}

enum class DemoRole {
    ADMIN,
    HOUSEKEEPING,
    MAINTENANCE
}

var CURRENT_DEMO_ROLE: DemoRole? = null
