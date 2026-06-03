package com.example.gestionhotelera.domain.usecase.room

import com.example.gestionhotelera.domain.model.Room
import com.example.gestionhotelera.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoomsUseCase @Inject constructor(
    private val repository: RoomRepository
) {
    operator fun invoke(): Flow<List<Room>> = repository.getRooms()
}
