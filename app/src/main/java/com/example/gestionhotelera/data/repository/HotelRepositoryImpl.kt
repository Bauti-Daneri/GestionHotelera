package com.example.gestionhotelera.data.repository

import com.example.gestionhotelera.data.local.HotelDao
import com.example.gestionhotelera.data.mapper.toDomain
import com.example.gestionhotelera.data.mapper.toEntity
import com.example.gestionhotelera.domain.model.Hotel
import com.example.gestionhotelera.domain.model.SyncStatus
import com.example.gestionhotelera.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HotelRepositoryImpl @Inject constructor(
    private val hotelDao: HotelDao
) : HotelRepository {
    override fun getHotelById(id: String): Flow<Hotel?> = 
        hotelDao.getAllHotels().map { hotels -> 
            hotels.find { it.id == id }?.toDomain() 
        }

    override suspend fun saveHotel(hotel: Hotel) {
        hotelDao.insertHotel(hotel.toEntity(isDirty = true).copy(syncStatus = SyncStatus.PENDING))
    }
}
