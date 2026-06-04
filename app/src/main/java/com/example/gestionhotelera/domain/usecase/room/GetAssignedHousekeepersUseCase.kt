package com.example.gestionhotelera.domain.usecase.room

import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.repository.RoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAssignedHousekeepersUseCase @Inject constructor(
    private val repository: RoomRepository
) {
    operator fun invoke(roomId: String): Flow<List<User>> = repository.getAssignedHousekeepers(roomId)
}
