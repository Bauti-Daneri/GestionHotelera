package com.hotelops.app.domain.repositories

import com.hotelops.app.domain.models.Booking
import com.hotelops.app.domain.models.BookingStatus
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

/**
 * Interfaz de repositorio para Reserva
 */
interface BookingRepository {
    /**
     * Obtiene reservas de un hotel
     */
    suspend fun getBookingsByHotel(hotelId: String): Result<List<Booking>>

    /**
     * Obtiene reservas del usuario
     */
    suspend fun getBookingsByUser(userId: String): Result<List<Booking>>

    /**
     * Obtiene reserva por ID
     */
    suspend fun getBookingById(bookingId: String): Result<Booking>

    /**
     * Observa reservas en tiempo real
     */
    fun observeBookingsByHotel(hotelId: String): Flow<List<Booking>>

    /**
     * Crea nueva reserva
     */
    suspend fun createBooking(booking: Booking): Result<Booking>

    /**
     * Actualiza estado de reserva
     */
    suspend fun updateBookingStatus(bookingId: String, status: BookingStatus): Result<Booking>

    /**
     * Cancela una reserva
     */
    suspend fun cancelBooking(bookingId: String): Result<Unit>

    /**
     * Obtiene reservas por rango de fechas
     */
    suspend fun getBookingsByDateRange(
        hotelId: String,
        startDate: LocalDate,
        endDate: LocalDate
    ): Result<List<Booking>>

    /**
     * Sincroniza reservas con backend
     */
    suspend fun syncBookings(hotelId: String): Result<Unit>
}

