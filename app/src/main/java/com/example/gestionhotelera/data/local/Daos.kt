package com.example.gestionhotelera.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HotelDao {
    @Query("SELECT * FROM hotels")
    fun getAllHotels(): Flow<List<HotelEntity>>

    @Query("SELECT * FROM hotels WHERE id = :id")
    suspend fun getHotelById(id: String): HotelEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotel(hotel: HotelEntity)
}

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: String): UserEntity?

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)
}

@Dao
interface RoomDao {
    @Query("SELECT * FROM rooms")
    fun getAllRooms(): Flow<List<RoomEntity>>

    @Query("SELECT * FROM rooms WHERE id = :id")
    suspend fun getRoomById(id: String): RoomEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoom(room: RoomEntity)

    @Update
    suspend fun updateRoom(room: RoomEntity)
}

@Dao
interface MaintenanceTicketDao {
    @Query("SELECT * FROM maintenance_tickets")
    fun getAllTickets(): Flow<List<MaintenanceTicketEntity>>

    @Query("SELECT * FROM maintenance_tickets WHERE id = :id")
    suspend fun getTicketById(id: String): MaintenanceTicketEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicket(ticket: MaintenanceTicketEntity)

    @Update
    suspend fun updateTicket(ticket: MaintenanceTicketEntity)
}

@Dao
interface RoomServiceOrderDao {
    @Query("SELECT * FROM room_service_orders")
    fun getAllOrders(): Flow<List<RoomServiceOrderEntity>>

    @Query("SELECT * FROM room_service_orders WHERE id = :id")
    suspend fun getOrderById(id: String): RoomServiceOrderEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: RoomServiceOrderEntity)

    @Update
    suspend fun updateOrder(order: RoomServiceOrderEntity)
}

@Dao
interface RoomServiceItemDao {
    @Query("SELECT * FROM room_service_items WHERE orderId = :orderId")
    suspend fun getItemsByOrderId(orderId: String): List<RoomServiceItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<RoomServiceItemEntity>)
}
