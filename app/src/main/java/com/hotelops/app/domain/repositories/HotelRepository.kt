package com.hotelops.app.domain.repositories

import com.hotelops.app.domain.models.Hotel
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz de repositorio para Hotel
 */
interface HotelRepository {
    /**
     * Obtiene lista de hoteles del tenant actual
     */
    suspend fun getHotelsByTenant(tenantId: String): Result<List<Hotel>>

    /**
     * Obtiene hotel por ID
     */
    suspend fun getHotelById(hotelId: String): Result<Hotel>

    /**
     * Observa cambios en hoteles en tiempo real
     */
    fun observeHotels(tenantId: String): Flow<List<Hotel>>

    /**
     * Crea un nuevo hotel
     */
    suspend fun createHotel(hotel: Hotel): Result<Hotel>

    /**
     * Actualiza información del hotel
     */
    suspend fun updateHotel(hotel: Hotel): Result<Hotel>

    /**
     * Elimina un hotel
     */
    suspend fun deleteHotel(hotelId: String): Result<Unit>

    /**
     * Sincroniza hoteles con backend
     */
    suspend fun syncHotels(tenantId: String): Result<Unit>
}

