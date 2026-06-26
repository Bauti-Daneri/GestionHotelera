package com.hotelops.app.domain.repositories

import com.hotelops.app.domain.models.Room
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

/**
 * Interfaz de repositorio para Habitación
 */
interface RoomRepository {
    /**
     * Obtiene habitaciones de un hotel
     */
    suspend fun getRoomsByHotel(hotelId: String): Result<List<Room>>

    /**
     * Obtiene habitación por ID
     */
    suspend fun getRoomById(roomId: String): Result<Room>

    /**
     * Observa cambios en habitaciones
     */
    fun observeRoomsByHotel(hotelId: String): Flow<List<Room>>

    /**
     * Obtiene habitaciones disponibles en rango de fechas
     */
    suspend fun getAvailableRooms(
        hotelId: String,
        checkInDate: LocalDate,
        checkOutDate: LocalDate
    ): Result<List<Room>>

    /**
     * Crea nueva habitación
     */
    suspend fun createRoom(room: Room): Result<Room>

    /**
     * Actualiza habitación
     */
    suspend fun updateRoom(room: Room): Result<Room>

    /**
     * Elimina habitación
     */
    suspend fun deleteRoom(roomId: String): Result<Unit>

    /**
     * Sincroniza habitaciones con backend
     */
    suspend fun syncRooms(hotelId: String): Result<Unit>
}

