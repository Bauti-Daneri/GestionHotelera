package com.hotelops.app.domain.exceptions

/**
 * Excepción base para todas las excepciones del dominio.
 * No contiene detalles de implementación (BD, API, etc.)
 * Refleja solo errores de lógica de negocio.
 */
open class DomainException(
    message: String = "Error en la lógica de negocio",
    cause: Throwable? = null
) : Exception(message, cause)

class AuthException(message: String = "Error de autenticación") : DomainException(message)
class ValidationException(message: String = "Error de validación") : DomainException(message)
class BookingException(message: String = "Error en la reserva") : DomainException(message)
class HotelNotFoundException(message: String = "Hotel no encontrado") : DomainException(message)
class RoomNotAvailableException(message: String = "Habitación no disponible") : DomainException(message)
class SyncException(message: String = "Error en sincronización") : DomainException(message)
class TenantException(message: String = "Error del tenant") : DomainException(message)

