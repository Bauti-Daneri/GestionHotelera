package com.hotelops.domain.repository

import com.hotelops.domain.model.Room
import com.hotelops.domain.model.RoomStatus
import com.hotelops.domain.model.RoomType
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    fun getRooms(hotelId: String): Flow<Resource<List<Room>>>

    fun getRoomById(roomId: String): Flow<Resource<Room>>

    fun updateRoomStatus(
        roomId: String,
        status: RoomStatus,
        cleanedBy: String?
    ): Flow<Resource<Room>>

    fun addRoom(
        hotelId: String,
        roomNumber: String,
        floor: Int,
        type: RoomType
    ): Flow<Resource<Room>>

    fun deleteRoom(roomId: String): Flow<Resource<Unit>>
}
