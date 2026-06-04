package com.example.gestionhotelera.data.repository

import com.example.gestionhotelera.data.local.RoomServiceItemDao
import com.example.gestionhotelera.data.local.RoomServiceMenuItemDao
import com.example.gestionhotelera.data.local.RoomServiceOrderDao
import com.example.gestionhotelera.data.mapper.toDomain
import com.example.gestionhotelera.data.mapper.toEntity
import com.example.gestionhotelera.domain.model.OrderStatus
import com.example.gestionhotelera.domain.model.RoomServiceMenuItem
import com.example.gestionhotelera.domain.model.RoomServiceOrder
import com.example.gestionhotelera.domain.model.SyncStatus
import com.example.gestionhotelera.domain.repository.RoomServiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomServiceRepositoryImpl @Inject constructor(
    private val orderDao: RoomServiceOrderDao,
    private val itemDao: RoomServiceItemDao,
    private val menuDao: RoomServiceMenuItemDao
) : RoomServiceRepository {
    override fun getOrders(): Flow<List<RoomServiceOrder>> = orderDao.getAllOrders().map { entities ->
        entities.map { entity ->
            val items = itemDao.getItemsByOrderId(entity.id).map { it.toDomain() }
            entity.toDomain(items)
        }
    }

    override suspend fun createOrder(order: RoomServiceOrder) {
        orderDao.insertOrder(order.toEntity(isDirty = true).copy(syncStatus = SyncStatus.PENDING))
        itemDao.insertItems(order.items.map { it.toEntity(order.id) })
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

    override fun getMenuItems(): Flow<List<RoomServiceMenuItem>> = 
        menuDao.getAllMenuItems().map { it.map { entity -> entity.toDomain() } }

    override fun getAvailableMenuItems(): Flow<List<RoomServiceMenuItem>> =
        menuDao.getAvailableMenuItems().map { it.map { entity -> entity.toDomain() } }

    override suspend fun saveMenuItem(item: RoomServiceMenuItem) {
        menuDao.insertMenuItem(item.toEntity(isDirty = true).copy(syncStatus = SyncStatus.PENDING))
    }

    override suspend fun deleteMenuItem(item: RoomServiceMenuItem) {
        menuDao.deleteMenuItem(item.toEntity())
    }

    override suspend fun getMenuItemById(id: String): RoomServiceMenuItem? =
        menuDao.getMenuItemById(id)?.toDomain()
}
