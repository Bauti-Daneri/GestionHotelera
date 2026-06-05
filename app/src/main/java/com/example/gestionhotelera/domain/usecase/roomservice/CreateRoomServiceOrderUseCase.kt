package com.example.gestionhotelera.domain.usecase.roomservice

import com.example.gestionhotelera.domain.model.OrderStatus
import com.example.gestionhotelera.domain.model.RoomServiceOrder
import com.example.gestionhotelera.domain.repository.RoomServiceRepository
import java.util.UUID
import javax.inject.Inject

class CreateRoomServiceOrderUseCase @Inject constructor(
    private val repository: RoomServiceRepository
) {
    suspend operator fun invoke(
        hotelId: String,
        roomId: String,
        description: String,
        price: Double,
        status: OrderStatus = OrderStatus.PENDING
    ) {
        val now = System.currentTimeMillis()
        val order = RoomServiceOrder(
            id = UUID.randomUUID().toString(),
            hotelId = hotelId,
            roomId = roomId,
            description = description,
            status = status,
            price = price,
            createdAt = now,
            updatedAt = now
        )
        repository.createOrder(order)
    }
}
