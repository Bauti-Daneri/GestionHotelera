package com.example.gestionhotelera.domain.repository

import com.example.gestionhotelera.domain.model.Room
import com.example.gestionhotelera.domain.model.RoomStatus
import com.example.gestionhotelera.domain.model.User
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    fun getRooms(): Flow<List<Room>>
    suspend fun getRoomById(id: String): Room?
    suspend fun getRoomByNumber(number: String, hotelId: String): Room?
    suspend fun updateRoomStatus(roomId: String, status: RoomStatus)
    suspend fun saveRoom(room: Room)
    suspend fun deleteRoom(room: Room)
    
    // Assignments
    fun getAssignedHousekeepers(roomId: String): Flow<List<User>>
    fun getRoomsForHousekeeper(userId: String): Flow<List<Room>>
    suspend fun assignHousekeeperToRoom(roomId: String, userId: String, hotelId: String)
    suspend fun removeHousekeeperFromRoom(roomId: String, userId: String)
    suspend fun countHousekeepersByRoom(roomId: String): Int
}
