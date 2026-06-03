package com.example.gestionhotelera.domain.usecase.roomservice

import com.example.gestionhotelera.domain.model.RoomServiceOrder
import com.example.gestionhotelera.domain.repository.RoomServiceRepository
import javax.inject.Inject

class CreateRoomServiceOrderUseCase @Inject constructor(
    private val repository: RoomServiceRepository
) {
    suspend operator fun invoke(order: RoomServiceOrder) = repository.createOrder(order)
}
