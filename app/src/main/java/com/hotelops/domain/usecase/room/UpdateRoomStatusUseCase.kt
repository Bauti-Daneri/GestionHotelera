package com.hotelops.domain.usecase.room

import com.hotelops.domain.model.Room
import com.hotelops.domain.model.RoomStatus
import com.hotelops.domain.repository.RoomRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateRoomStatusUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    operator fun invoke(
        roomId: String,
        status: RoomStatus,
        cleanedBy: String?
    ): Flow<Resource<Room>> {
        return roomRepository.updateRoomStatus(roomId, status, cleanedBy)
    }
}
