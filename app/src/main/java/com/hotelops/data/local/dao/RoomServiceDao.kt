package com.hotelops.data.local.dao

import androidx.room.*
import com.hotelops.data.local.entity.RoomServiceOrderEntity
import com.hotelops.domain.model.OrderStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomServiceDao {

    @Query("SELECT * FROM room_service_orders WHERE hotelId = :hotelId ORDER BY createdAt DESC")
    fun getOrdersByHotel(hotelId: String): Flow<List<RoomServiceOrderEntity>>

    @Query("SELECT * FROM room_service_orders WHERE id = :orderId")
    fun getOrderById(orderId: String): Flow<RoomServiceOrderEntity?>

    @Query("SELECT * FROM room_service_orders WHERE hotelId = :hotelId AND status = :status ORDER BY createdAt DESC")
    fun getOrdersByStatus(hotelId: String, status: OrderStatus): Flow<List<RoomServiceOrderEntity>>

    @Query("SELECT * FROM room_service_orders WHERE hotelId = :hotelId AND roomId = :roomId ORDER BY createdAt DESC")
    fun getOrdersByRoom(hotelId: String, roomId: String): Flow<List<RoomServiceOrderEntity>>

    @Query("SELECT * FROM room_service_orders WHERE hotelId = :hotelId AND status IN ('PENDING', 'PREPARING') ORDER BY createdAt ASC")
    fun getActiveOrders(hotelId: String): Flow<List<RoomServiceOrderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: RoomServiceOrderEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrders(orders: List<RoomServiceOrderEntity>)

    @Update
    suspend fun updateOrder(order: RoomServiceOrderEntity)

    @Delete
    suspend fun deleteOrder(order: RoomServiceOrderEntity)

    @Query("DELETE FROM room_service_orders WHERE id = :orderId")
    suspend fun deleteOrderById(orderId: String)

    @Query("SELECT * FROM room_service_orders WHERE isDirty = 1")
    suspend fun getDirtyOrders(): List<RoomServiceOrderEntity>

    @Query("UPDATE room_service_orders SET syncedAt = :syncedAt, isDirty = 0 WHERE id = :orderId")
    suspend fun markAsSynced(orderId: String, syncedAt: Long)

    @Query("UPDATE room_service_orders SET status = :status, updatedAt = :updatedAt, deliveredAt = :deliveredAt, isDirty = 1 WHERE id = :orderId")
    suspend fun updateOrderStatus(orderId: String, status: OrderStatus, updatedAt: Long, deliveredAt: Long?)

    @Query("DELETE FROM room_service_orders WHERE hotelId = :hotelId")
    suspend fun deleteOrdersByHotel(hotelId: String)
}
