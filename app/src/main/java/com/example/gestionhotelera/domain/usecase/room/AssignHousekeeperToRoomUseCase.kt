package com.example.gestionhotelera.domain.usecase.room

import com.example.gestionhotelera.domain.repository.RoomRepository
import com.example.gestionhotelera.domain.usecase.auth.GetCurrentUserUseCase
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class AssignHousekeeperToRoomUseCase @Inject constructor(
    private val repository: RoomRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend operator fun invoke(roomId: String, userId: String): Result<Unit> {
        val currentUser = getCurrentUserUseCase().firstOrNull()
        val hotelId = currentUser?.hotelId ?: "HOTEL-DEMO-001"

        val count = repository.countHousekeepersByRoom(roomId)
        if (count >= 3) {
            return Result.failure(Exception("Máximo 3 empleados por habitación"))
        }
        repository.assignHousekeeperToRoom(roomId, userId, hotelId)
        return Result.success(Unit)
    }
}
