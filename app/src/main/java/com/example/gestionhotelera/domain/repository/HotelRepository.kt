package com.example.gestionhotelera.domain.repository

import com.example.gestionhotelera.domain.model.Hotel
import kotlinx.coroutines.flow.Flow

interface HotelRepository {
    fun getHotelById(id: String): Flow<Hotel?>
    suspend fun saveHotel(hotel: Hotel)
}
