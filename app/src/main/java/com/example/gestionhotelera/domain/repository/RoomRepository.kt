package com.example.gestionhotelera.domain.repository

import com.example.gestionhotelera.domain.model.Room
import com.example.gestionhotelera.domain.model.RoomStatus
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    fun getRooms(): Flow<List<Room>>
    suspend fun getRoomById(id: String): Room?
    suspend fun updateRoomStatus(roomId: String, status: RoomStatus)
    suspend fun saveRoom(room: Room)
}
