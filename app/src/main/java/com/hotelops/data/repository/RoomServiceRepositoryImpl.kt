package com.hotelops.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.hotelops.data.local.dao.RoomServiceDao
import com.hotelops.data.local.entity.RoomServiceOrderEntity
import com.hotelops.data.mapper.toDomain
import com.hotelops.domain.model.OrderItem
import com.hotelops.domain.model.OrderStatus
import com.hotelops.domain.model.RoomServiceOrder
import com.hotelops.domain.repository.RoomServiceRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class RoomServiceRepositoryImpl @Inject constructor(
    private val roomServiceDao: RoomServiceDao,
    private val firestore: FirebaseFirestore
) : RoomServiceRepository {

    companion object {
        private const val COL_ORDERS = "room_service_orders"
    }

    override fun getOrders(hotelId: String): Flow<Resource<List<RoomServiceOrder>>> {
        return roomServiceDao.getOrdersByHotel(hotelId).map { entities ->
            Resource.Success(entities.map { it.toDomain() })
        }
    }

    override fun getOrderById(orderId: String): Flow<Resource<RoomServiceOrder>> {
        return roomServiceDao.getOrderById(orderId).map { entity ->
            if (entity != null) Resource.Success(entity.toDomain())
            else Resource.Error("Pedido no encontrado")
        }
    }

    override fun createOrder(
        hotelId: String,
        roomId: String,
        guestName: String,
        items: List<OrderItem>,
        specialInstructions: String?
    ): Flow<Resource<RoomServiceOrder>> = flow {
        emit(Resource.Loading())
        try {
            val orderId = UUID.randomUUID().toString()
            val now = System.currentTimeMillis()
            val total = items.sumOf { it.price * it.quantity }

            val roomNumber = try {
                val roomDoc = firestore.collection("rooms").document(roomId).get().await()
                roomDoc.getString("roomNumber") ?: roomId
            } catch (e: Exception) { roomId }

            val entity = RoomServiceOrderEntity(
                id = orderId,
                hotelId = hotelId,
                roomId = roomId,
                roomNumber = roomNumber,
                guestName = guestName,
                items = items,
                status = OrderStatus.PENDING,
                totalAmount = total,
                specialInstructions = specialInstructions,
                createdAt = now,
                updatedAt = now,
                deliveredAt = null,
                isDirty = true
            )

            // 1. Guardar local (offline-first)
            roomServiceDao.insertOrder(entity)

            // 2. Sincronizar con Firestore
            val itemsData = items.map { mapOf("name" to it.name, "quantity" to it.quantity, "price" to it.price) }
            val data = mapOf(
                "id" to orderId,
                "hotelId" to hotelId,
                "roomId" to roomId,
                "roomNumber" to roomNumber,
                "guestName" to guestName,
                "items" to itemsData,
                "status" to OrderStatus.PENDING.name,
                "totalAmount" to total,
                "specialInstructions" to (specialInstructions ?: ""),
                "createdAt" to now,
                "updatedAt" to now
            )
            firestore.collection(COL_ORDERS).document(orderId).set(data).await()
            roomServiceDao.markAsSynced(orderId, System.currentTimeMillis())

            emit(Resource.Success(entity.toDomain()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al crear pedido"))
        }
    }

    override fun updateOrderStatus(
        orderId: String,
        status: OrderStatus
    ): Flow<Resource<RoomServiceOrder>> = flow {
        emit(Resource.Loading())
        try {
            val now = System.currentTimeMillis()
            val deliveredAt = if (status == OrderStatus.DELIVERED) now else null

            // 1. Actualizar local
            roomServiceDao.updateOrderStatus(orderId, status, now, deliveredAt)

            // 2. Sincronizar con Firestore
            val update = mutableMapOf<String, Any>(
                "status" to status.name,
                "updatedAt" to now
            )
            deliveredAt?.let { update["deliveredAt"] = it }
            firestore.collection(COL_ORDERS).document(orderId).update(update).await()

            val entity = roomServiceDao.getOrderById(orderId).first()
            if (entity != null) emit(Resource.Success(entity.toDomain()))
        } catch (e: Exception) {
            val entity = roomServiceDao.getOrderById(orderId).first()
            if (entity != null) emit(Resource.Success(entity.toDomain()))
            else emit(Resource.Error(e.message ?: "Error al actualizar pedido"))
        }
    }
}
