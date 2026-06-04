package com.example.gestionhotelera.domain.usecase.room

import com.example.gestionhotelera.domain.repository.RoomRepository
import javax.inject.Inject

class AssignHousekeeperToRoomUseCase @Inject constructor(
    private val repository: RoomRepository
) {
    suspend operator fun invoke(roomId: String, userId: String, hotelId: String): Result<Unit> {
        val count = repository.countHousekeepersByRoom(roomId)
        if (count >= 3) {
            return Result.failure(Exception("Máximo 3 empleados por habitación"))
        }
        repository.assignHousekeeperToRoom(roomId, userId, hotelId)
        return Result.success(Unit)
    }
}
