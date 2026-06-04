package com.example.gestionhotelera.domain.usecase.room

import com.example.gestionhotelera.domain.repository.RoomRepository
import javax.inject.Inject

class CheckRoomNumberExistsUseCase @Inject constructor(
    private val repository: RoomRepository
) {
    suspend operator fun invoke(number: String, hotelId: String): Boolean {
        return repository.getRoomByNumber(number, hotelId) != null
    }
}
