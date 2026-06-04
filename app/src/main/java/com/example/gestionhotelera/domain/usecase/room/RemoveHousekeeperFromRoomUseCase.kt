package com.example.gestionhotelera.domain.usecase.room

import com.example.gestionhotelera.domain.repository.RoomRepository
import javax.inject.Inject

class RemoveHousekeeperFromRoomUseCase @Inject constructor(
    private val repository: RoomRepository
) {
    suspend operator fun invoke(roomId: String, userId: String) = 
        repository.removeHousekeeperFromRoom(roomId, userId)
}
