package com.example.gestionhotelera.data.local

import com.example.gestionhotelera.domain.model.*
import java.util.UUID
import javax.inject.Inject

class DatabaseInitializer @Inject constructor(
    private val hotelDao: HotelDao,
    private val userDao: UserDao,
    private val roomDao: RoomDao,
    private val ticketDao: MaintenanceTicketDao,
    private val orderDao: RoomServiceOrderDao,
    private val itemDao: RoomServiceItemDao
) {
    suspend fun seed() {
        val hotelId = "HOTEL-DEMO-001"
        val now = System.currentTimeMillis()

        // Seed Hotel
        if (hotelDao.getHotelById(hotelId) == null) {
            hotelDao.insertHotel(
                HotelEntity(hotelId, "Hotel Plaza Central", "Av. Principal 123", "555-0101", now, now, false, SyncStatus.SYNCED)
            )
        }

        // Seed Users
        val users = listOf(
            UserEntity("admin-01", hotelId, "Administrador", "admin@hotel.com", UserRole.ADMIN, createdAt = now, updatedAt = now, isDirty = false, syncStatus = SyncStatus.SYNCED),
            UserEntity("house-01", hotelId, "María González", "limpieza@hotel.com", UserRole.HOUSEKEEPING, createdAt = now, updatedAt = now, isDirty = false, syncStatus = SyncStatus.SYNCED),
            UserEntity("maint-01", hotelId, "Carlos Ruiz", "mantenimiento@hotel.com", UserRole.MAINTENANCE, createdAt = now, updatedAt = now, isDirty = false, syncStatus = SyncStatus.SYNCED)
        )
        users.forEach { if (userDao.getUserById(it.id) == null) userDao.insertUser(it) }

        // Seed Rooms
        val rooms = listOf("101", "102", "103", "104", "105", "203")
        rooms.forEachIndexed { index, num ->
            val id = "room-$num"
            if (roomDao.getRoomById(id) == null) {
                roomDao.insertRoom(
                    RoomEntity(id, hotelId, num, "Standard", if (index % 2 == 0) RoomStatus.CLEAN else RoomStatus.DIRTY, now, now, now, false, SyncStatus.SYNCED)
                )
            }
        }

        // Seed Tickets
        val tickets = listOf(
            MaintenanceTicketEntity(UUID.randomUUID().toString(), hotelId, "room-203", "Fuga de agua", TicketCategory.PLUMBING, TicketStatus.OPEN, "maint-01", null, now, now, false, SyncStatus.SYNCED),
            MaintenanceTicketEntity(UUID.randomUUID().toString(), hotelId, "room-105", "Luz del baño no enciende", TicketCategory.ELECTRICAL, TicketStatus.OPEN, "maint-01", null, now, now, false, SyncStatus.SYNCED)
        )
        tickets.forEach { ticketDao.insertTicket(it) }

        // Seed Orders
        val order1Id = UUID.randomUUID().toString()
        if (orderDao.getOrderById(order1Id) == null) {
            orderDao.insertOrder(RoomServiceOrderEntity(order1Id, hotelId, "room-105", OrderStatus.PENDING, 25.0, now, now, false, SyncStatus.SYNCED))
            itemDao.insertItems(listOf(
                RoomServiceItemEntity(UUID.randomUUID().toString(), order1Id, "Desayuno", 10.0, 2),
                RoomServiceItemEntity(UUID.randomUUID().toString(), order1Id, "Café", 2.5, 2)
            ))
        }

        val order2Id = UUID.randomUUID().toString()
        if (orderDao.getOrderById(order2Id) == null) {
            orderDao.insertOrder(RoomServiceOrderEntity(order2Id, hotelId, "room-203", OrderStatus.PENDING, 45.0, now, now, false, SyncStatus.SYNCED))
            itemDao.insertItems(listOf(
                RoomServiceItemEntity(UUID.randomUUID().toString(), order2Id, "Hamburguesa Gourmet", 15.0, 1),
                RoomServiceItemEntity(UUID.randomUUID().toString(), order2Id, "Vino Tinto", 30.0, 1)
            ))
        }
    }
}
