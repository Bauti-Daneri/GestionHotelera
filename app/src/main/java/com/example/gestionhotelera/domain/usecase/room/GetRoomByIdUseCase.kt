package com.example.gestionhotelera.domain.usecase.room

import com.example.gestionhotelera.domain.model.Room
import com.example.gestionhotelera.domain.repository.RoomRepository
import javax.inject.Inject

class GetRoomByIdUseCase @Inject constructor(
    private val repository: RoomRepository
) {
    suspend operator fun invoke(id: String): Room? = repository.getRoomById(id)
}
