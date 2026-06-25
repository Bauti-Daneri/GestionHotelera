package com.hotelops.domain.repository

import com.hotelops.domain.model.OrderItem
import com.hotelops.domain.model.OrderStatus
import com.hotelops.domain.model.RoomServiceOrder
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface RoomServiceRepository {
    fun getOrders(hotelId: String): Flow<Resource<List<RoomServiceOrder>>>

    fun getOrderById(orderId: String): Flow<Resource<RoomServiceOrder>>

    fun createOrder(
        hotelId: String,
        roomId: String,
        guestName: String,
        items: List<OrderItem>,
        specialInstructions: String?
    ): Flow<Resource<RoomServiceOrder>>

    fun updateOrderStatus(
        orderId: String,
        status: OrderStatus
    ): Flow<Resource<RoomServiceOrder>>

    fun deleteOrder(orderId: String): Flow<Resource<Unit>>
}
