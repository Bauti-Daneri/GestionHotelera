package com.example.gestionhotelera.domain.repository

import com.example.gestionhotelera.domain.model.OrderStatus
import com.example.gestionhotelera.domain.model.RoomServiceMenuItem
import com.example.gestionhotelera.domain.model.RoomServiceOrder
import kotlinx.coroutines.flow.Flow

interface RoomServiceRepository {
    // Orders
    fun getOrders(): Flow<List<RoomServiceOrder>>
    suspend fun createOrder(order: RoomServiceOrder)
    suspend fun updateOrderStatus(orderId: String, status: OrderStatus)
    
    // Menu
    fun getMenuItems(): Flow<List<RoomServiceMenuItem>>
    fun getAvailableMenuItems(): Flow<List<RoomServiceMenuItem>>
    suspend fun saveMenuItem(item: RoomServiceMenuItem)
    suspend fun deleteMenuItem(item: RoomServiceMenuItem)
    suspend fun getMenuItemById(id: String): RoomServiceMenuItem?
}
