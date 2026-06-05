package com.example.gestionhotelera.data.repository

import com.example.gestionhotelera.data.local.RoomServiceOrderDao
import com.example.gestionhotelera.data.mapper.toDomain
import com.example.gestionhotelera.data.mapper.toEntity
import com.example.gestionhotelera.domain.model.OrderStatus
import com.example.gestionhotelera.domain.model.RoomServiceOrder
import com.example.gestionhotelera.domain.model.SyncStatus
import com.example.gestionhotelera.domain.repository.RoomServiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomServiceRepositoryImpl @Inject constructor(
    private val orderDao: RoomServiceOrderDao
) : RoomServiceRepository {
    override fun getOrders(): Flow<List<RoomServiceOrder>> = orderDao.getAllOrders().map { entities ->
        entities.map { it.toDomain() }
    }

    override suspend fun createOrder(order: RoomServiceOrder) {
        orderDao.insertOrder(order.toEntity(isDirty = true).copy(syncStatus = SyncStatus.PENDING))
    }

    override suspend fun updateOrderStatus(orderId: String, status: OrderStatus) {
        val order = orderDao.getOrderById(orderId)
        order?.let {
            val updated = it.copy(
                status = status,
                updatedAt = System.currentTimeMillis(),
                isDirty = true,
                syncStatus = SyncStatus.PENDING
            )
            orderDao.updateOrder(updated)
        }
    }
}
