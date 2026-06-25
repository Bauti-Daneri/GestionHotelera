package com.hotelops.domain.usecase.room

import com.hotelops.domain.repository.RoomRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteRoomUseCase @Inject constructor(
    private val repository: RoomRepository
) {
    operator fun invoke(roomId: String): Flow<Resource<Unit>> {
        return repository.deleteRoom(roomId)
    }
}
