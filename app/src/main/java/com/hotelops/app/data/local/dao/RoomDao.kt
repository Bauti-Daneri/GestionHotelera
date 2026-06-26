package com.hotelops.app.data.local.dao

import androidx.room.*
import com.hotelops.app.data.local.entities.RoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Query("SELECT * FROM rooms WHERE id = :roomId")
    suspend fun getRoomById(roomId: String): RoomEntity?

    @Query("SELECT * FROM rooms WHERE hotelId = :hotelId")
    suspend fun getRoomsByHotel(hotelId: String): List<RoomEntity>

    @Query("SELECT * FROM rooms WHERE hotelId = :hotelId")
    fun observeRoomsByHotel(hotelId: String): Flow<List<RoomEntity>>

    @Query("SELECT * FROM rooms WHERE hotelId = :hotelId AND isAvailable = 1")
    suspend fun getAvailableRoomsByHotel(hotelId: String): List<RoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoom(room: RoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRooms(rooms: List<RoomEntity>)

    @Update
    suspend fun updateRoom(room: RoomEntity)

    @Delete
    suspend fun deleteRoom(room: RoomEntity)

    @Query("DELETE FROM rooms WHERE hotelId = :hotelId")
    suspend fun deleteRoomsByHotel(hotelId: String)
}

