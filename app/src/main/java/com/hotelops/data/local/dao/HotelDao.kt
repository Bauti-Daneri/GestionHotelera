package com.hotelops.data.local.dao

import androidx.room.*
import com.hotelops.data.local.entity.HotelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HotelDao {

    @Query("SELECT * FROM hotels WHERE id = :hotelId")
    fun getHotelById(hotelId: String): Flow<HotelEntity?>

    @Query("SELECT * FROM hotels")
    fun getAllHotels(): Flow<List<HotelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotel(hotel: HotelEntity)

    @Update
    suspend fun updateHotel(hotel: HotelEntity)

    @Delete
    suspend fun deleteHotel(hotel: HotelEntity)

    @Query("DELETE FROM hotels WHERE id = :hotelId")
    suspend fun deleteHotelById(hotelId: String)

    @Query("SELECT * FROM hotels WHERE isDirty = 1")
    suspend fun getDirtyHotels(): List<HotelEntity>

    @Query("UPDATE hotels SET syncedAt = :syncedAt, isDirty = 0 WHERE id = :hotelId")
    suspend fun markAsSynced(hotelId: String, syncedAt: Long)
}
