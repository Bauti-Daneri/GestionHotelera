package com.hotelops.domain.usecase.room

import com.hotelops.domain.model.Room
import com.hotelops.domain.repository.RoomRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    operator fun invoke(hotelId: String): Flow<Resource<List<Room>>> {
        return roomRepository.getRooms(hotelId)
    }
}
