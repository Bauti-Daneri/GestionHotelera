package com.example.gestionhotelera.domain.usecase.roomservice

import com.example.gestionhotelera.domain.model.RoomServiceOrder
import com.example.gestionhotelera.domain.repository.RoomServiceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRoomServiceOrdersUseCase @Inject constructor(
    private val repository: RoomServiceRepository
) {
    operator fun invoke(): Flow<List<RoomServiceOrder>> = repository.getOrders()
}
