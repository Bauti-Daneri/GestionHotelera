package com.example.gestionhotelera.domain.model

enum class EmployeeShift(val displayName: String) {
    MORNING("Mañana (06:00 - 14:00)"),
    AFTERNOON("Tarde (14:00 - 22:00)"),
    NIGHT("Noche (22:00 - 06:00)"),
    FULL_TIME("Tiempo Completo")
}
