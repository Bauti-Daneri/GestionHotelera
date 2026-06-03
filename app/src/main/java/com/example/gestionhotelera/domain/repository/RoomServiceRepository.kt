package com.example.gestionhotelera.domain.repository

import com.example.gestionhotelera.domain.model.OrderStatus
import com.example.gestionhotelera.domain.model.RoomServiceOrder
import kotlinx.coroutines.flow.Flow

interface RoomServiceRepository {
    fun getOrders(): Flow<List<RoomServiceOrder>>
    suspend fun createOrder(order: RoomServiceOrder)
    suspend fun updateOrderStatus(orderId: String, status: OrderStatus)
}
