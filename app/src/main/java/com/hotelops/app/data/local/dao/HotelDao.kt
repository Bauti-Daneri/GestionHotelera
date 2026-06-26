package com.hotelops.app.data.local.dao

import androidx.room.*
import com.hotelops.app.data.local.entities.HotelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HotelDao {
    @Query("SELECT * FROM hotels WHERE id = :hotelId")
    suspend fun getHotelById(hotelId: String): HotelEntity?

    @Query("SELECT * FROM hotels WHERE tenantId = :tenantId AND isActive = 1")
    suspend fun getHotelsByTenant(tenantId: String): List<HotelEntity>

    @Query("SELECT * FROM hotels WHERE tenantId = :tenantId AND isActive = 1")
    fun observeHotelsByTenant(tenantId: String): Flow<List<HotelEntity>>

    @Query("SELECT * FROM hotels WHERE id = :hotelId")
    fun observeHotel(hotelId: String): Flow<HotelEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotel(hotel: HotelEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotels(hotels: List<HotelEntity>)

    @Update
    suspend fun updateHotel(hotel: HotelEntity)

    @Delete
    suspend fun deleteHotel(hotel: HotelEntity)

    @Query("DELETE FROM hotels WHERE tenantId = :tenantId")
    suspend fun deleteHotelsByTenant(tenantId: String)
}

