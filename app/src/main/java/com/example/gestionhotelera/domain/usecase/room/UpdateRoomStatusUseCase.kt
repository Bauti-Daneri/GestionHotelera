package com.example.gestionhotelera.domain.usecase.room

import com.example.gestionhotelera.domain.model.RoomStatus
import com.example.gestionhotelera.domain.repository.RoomRepository
import javax.inject.Inject

class UpdateRoomStatusUseCase @Inject constructor(
    private val repository: RoomRepository
) {
    suspend operator fun invoke(roomId: String, status: RoomStatus) {
        repository.updateRoomStatus(roomId, status)
    }
}
