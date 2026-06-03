package com.example.gestionhotelera.domain.usecase.roomservice

import com.example.gestionhotelera.domain.model.OrderStatus
import com.example.gestionhotelera.domain.repository.RoomServiceRepository
import javax.inject.Inject

class UpdateOrderStatusUseCase @Inject constructor(
    private val repository: RoomServiceRepository
) {
    suspend operator fun invoke(orderId: String, status: OrderStatus) {
        repository.updateOrderStatus(orderId, status)
    }
}
