package com.hotelops.domain.usecase.room

import com.hotelops.domain.model.Room
import com.hotelops.domain.model.RoomType
import com.hotelops.domain.repository.RoomRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddRoomUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    operator fun invoke(
        hotelId: String,
        roomNumber: String,
        floor: Int,
        type: RoomType
    ): Flow<Resource<Room>> {
        return roomRepository.addRoom(hotelId, roomNumber, floor, type)
    }
}
