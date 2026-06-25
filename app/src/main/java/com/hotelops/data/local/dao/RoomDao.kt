package com.hotelops.data.local.dao

import androidx.room.*
import com.hotelops.data.local.entity.RoomEntity
import com.hotelops.domain.model.RoomStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {

    @Query("SELECT * FROM rooms WHERE hotelId = :hotelId ORDER BY floor ASC, roomNumber ASC")
    fun getRoomsByHotel(hotelId: String): Flow<List<RoomEntity>>

    @Query("SELECT * FROM rooms WHERE id = :roomId")
    fun getRoomById(roomId: String): Flow<RoomEntity?>

    @Query("SELECT * FROM rooms WHERE hotelId = :hotelId AND status = :status ORDER BY floor ASC, roomNumber ASC")
    fun getRoomsByStatus(hotelId: String, status: RoomStatus): Flow<List<RoomEntity>>

    @Query("SELECT * FROM rooms WHERE hotelId = :hotelId AND floor = :floor ORDER BY roomNumber ASC")
    fun getRoomsByFloor(hotelId: String, floor: Int): Flow<List<RoomEntity>>

    @Query("SELECT * FROM rooms WHERE roomNumber = :roomNumber AND hotelId = :hotelId")
    suspend fun getRoomByNumber(roomNumber: String, hotelId: String): RoomEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoom(room: RoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRooms(rooms: List<RoomEntity>)

    @Update
    suspend fun updateRoom(room: RoomEntity)

    @Delete
    suspend fun deleteRoom(room: RoomEntity)

    @Query("DELETE FROM rooms WHERE id = :roomId")
    suspend fun deleteRoomById(roomId: String)

    @Query("SELECT * FROM rooms WHERE isDirty = 1")
    suspend fun getDirtyRooms(): List<RoomEntity>

    @Query("UPDATE rooms SET syncedAt = :syncedAt, isDirty = 0 WHERE id = :roomId")
    suspend fun markAsSynced(roomId: String, syncedAt: Long)

    @Query("UPDATE rooms SET status = :status, lastCleanedAt = :cleanedAt, lastCleanedBy = :cleanedBy, isDirty = 1 WHERE id = :roomId")
    suspend fun updateRoomStatus(roomId: String, status: RoomStatus, cleanedAt: Long?, cleanedBy: String?)

    @Query("DELETE FROM rooms WHERE hotelId = :hotelId")
    suspend fun deleteRoomsByHotel(hotelId: String)
}
