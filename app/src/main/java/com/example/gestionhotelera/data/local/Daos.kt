package com.example.gestionhotelera.data.local

import androidx.room.*
import com.example.gestionhotelera.domain.model.SyncStatus
import com.example.gestionhotelera.domain.model.UserRole
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

    @Query("SELECT * FROM users WHERE phone = :phone")
    suspend fun getUserByPhone(phone: String): UserEntity?

    @Query("SELECT COUNT(*) FROM users WHERE hotelId = :hotelId")
    suspend fun countUsersByHotelId(hotelId: String): Int

    @Query("SELECT * FROM users WHERE role = :role")
    fun getUsersByRole(role: UserRole): Flow<List<UserEntity>>

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

    @Query("SELECT * FROM rooms WHERE number = :number AND hotelId = :hotelId")
    suspend fun getRoomByNumber(number: String, hotelId: String): RoomEntity?

    @Query("SELECT COUNT(*) FROM rooms WHERE hotelId = :hotelId")
    suspend fun countRoomsByHotelId(hotelId: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoom(room: RoomEntity)

    @Update
    suspend fun updateRoom(room: RoomEntity)

    @Delete
    suspend fun deleteRoom(room: RoomEntity)
}

@Dao
interface MaintenanceTicketDao {
    @Query("SELECT * FROM maintenance_tickets")
    fun getAllTickets(): Flow<List<MaintenanceTicketEntity>>

    @Query("SELECT * FROM maintenance_tickets WHERE id = :id")
    suspend fun getTicketById(id: String): MaintenanceTicketEntity?

    @Query("SELECT COUNT(*) FROM maintenance_tickets WHERE hotelId = :hotelId")
    suspend fun countTicketsByHotelId(hotelId: String): Int

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

    @Query("SELECT COUNT(*) FROM room_service_orders WHERE hotelId = :hotelId")
    suspend fun countOrdersByHotelId(hotelId: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: RoomServiceOrderEntity)

    @Update
    suspend fun updateOrder(order: RoomServiceOrderEntity)
}

@Dao
interface RoomHousekeeperAssignmentDao {
    @Query("SELECT * FROM room_housekeeper_assignments WHERE roomId = :roomId")
    fun getAssignmentsByRoom(roomId: String): Flow<List<RoomHousekeeperAssignmentEntity>>

    @Query("SELECT * FROM rooms WHERE id IN (SELECT roomId FROM room_housekeeper_assignments WHERE userId = :userId)")
    fun getRoomsForHousekeeper(userId: String): Flow<List<RoomEntity>>

    @Query("SELECT * FROM users WHERE id IN (SELECT userId FROM room_housekeeper_assignments WHERE roomId = :roomId)")
    fun getHousekeepersForRoom(roomId: String): Flow<List<UserEntity>>

    @Query("SELECT COUNT(*) FROM room_housekeeper_assignments WHERE roomId = :roomId")
    suspend fun countHousekeepersByRoom(roomId: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignment(assignment: RoomHousekeeperAssignmentEntity)

    @Delete
    suspend fun deleteAssignment(assignment: RoomHousekeeperAssignmentEntity)

    @Query("DELETE FROM room_housekeeper_assignments WHERE roomId = :roomId AND userId = :userId")
    suspend fun deleteAssignmentByIds(roomId: String, userId: String)
}

@Dao
interface RoomServiceItemDao {
    @Query("SELECT * FROM room_service_items WHERE orderId = :orderId")
    fun getItemsByOrderId(orderId: String): Flow<List<RoomServiceItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<RoomServiceItemEntity>)
}

@Dao
interface RoomServiceMenuItemDao {
    @Query("SELECT * FROM room_service_menu_items")
    fun getAllMenuItems(): Flow<List<RoomServiceMenuItemEntity>>

    @Query("SELECT * FROM room_service_menu_items WHERE hotelId = :hotelId")
    fun getMenuItemsByHotel(hotelId: String): Flow<List<RoomServiceMenuItemEntity>>

    @Query("SELECT * FROM room_service_menu_items WHERE id = :id")
    suspend fun getMenuItemById(id: String): RoomServiceMenuItemEntity?

    @Query("SELECT COUNT(*) FROM room_service_menu_items WHERE hotelId = :hotelId")
    suspend fun countMenuItemsByHotelId(hotelId: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuItem(item: RoomServiceMenuItemEntity)

    @Delete
    suspend fun deleteMenuItem(item: RoomServiceMenuItemEntity)
}
