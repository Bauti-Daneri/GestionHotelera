package com.example.gestionhotelera.data.local

import com.example.gestionhotelera.domain.model.*
import javax.inject.Inject

class DatabaseInitializer @Inject constructor(
    private val hotelDao: HotelDao,
    private val userDao: UserDao,
    private val roomDao: RoomDao,
    private val ticketDao: MaintenanceTicketDao,
    private val orderDao: RoomServiceOrderDao,
    private val itemDao: RoomServiceItemDao,
    private val menuDao: RoomServiceMenuItemDao,
    private val assignmentDao: RoomHousekeeperAssignmentDao
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

        // Seed Users - Only if no users for this hotel
        if (userDao.countUsersByHotelId(hotelId) == 0) {
            val users = listOf(
                UserEntity("admin-01", hotelId, "Administrador", "admin@hotel.com", UserRole.ADMIN, createdAt = now, updatedAt = now, isDirty = false, syncStatus = SyncStatus.SYNCED),
                UserEntity("house-01", hotelId, "María González", "limpieza@hotel.com", UserRole.HOUSEKEEPING, createdAt = now, updatedAt = now, isDirty = false, syncStatus = SyncStatus.SYNCED),
                UserEntity("maint-01", hotelId, "Carlos Ruiz", "mantenimiento@hotel.com", UserRole.MAINTENANCE, createdAt = now, updatedAt = now, isDirty = false, syncStatus = SyncStatus.SYNCED)
            )
            users.forEach { userDao.insertUser(it) }
        }

        // Seed Rooms - Only if no rooms for this hotel
        if (roomDao.countRoomsByHotelId(hotelId) == 0) {
            val rooms = listOf("101", "102", "103", "104", "105", "203")
            rooms.forEachIndexed { index, num ->
                val id = "room-$num"
                roomDao.insertRoom(
                    RoomEntity(id, hotelId, num, "Standard", if (index % 2 == 0) RoomStatus.CLEAN else RoomStatus.DIRTY, now, now, now, false, SyncStatus.SYNCED)
                )
                
                // Assign some rooms to the housekeeper by default
                if (num == "101" || num == "103" || num == "203") {
                    assignmentDao.insertAssignment(
                        RoomHousekeeperAssignmentEntity(id, "house-01", hotelId, now)
                    )
                }
            }
        }

        // Seed Tickets - Fixed IDs
        if (ticketDao.countTicketsByHotelId(hotelId) == 0) {
            val tickets = listOf(
                MaintenanceTicketEntity("ticket-demo-001", hotelId, "room-203", "Fuga de agua", TicketCategory.PLUMBING, TicketStatus.OPEN, "maint-01", null, now, now, false, SyncStatus.SYNCED),
                MaintenanceTicketEntity("ticket-demo-002", hotelId, "room-105", "Luz del baño no enciende", TicketCategory.ELECTRICAL, TicketStatus.OPEN, "maint-01", null, now, now, false, SyncStatus.SYNCED)
            )
            tickets.forEach { ticketDao.insertTicket(it) }
        }

        // Seed Menu Items
        if (menuDao.countMenuItemsByHotelId(hotelId) == 0) {
            val menuItems = listOf(
                RoomServiceMenuItemEntity("menu-001", hotelId, "Desayuno Continental", "Café, jugo, tostadas y frutas", 12.0, "Desayuno", true, now, now, false, SyncStatus.SYNCED),
                RoomServiceMenuItemEntity("menu-002", hotelId, "Café Espresso", "Café intenso", 3.5, "Bebidas", true, now, now, false, SyncStatus.SYNCED),
                RoomServiceMenuItemEntity("menu-003", hotelId, "Hamburguesa Gourmet", "Carne premium con papas", 18.0, "Almuerzo/Cena", true, now, now, false, SyncStatus.SYNCED),
                RoomServiceMenuItemEntity("menu-004", hotelId, "Vino Tinto", "Copa de la casa", 8.0, "Bebidas", true, now, now, false, SyncStatus.SYNCED)
            )
            menuItems.forEach { menuDao.insertMenuItem(it) }
        }

        // Seed Orders - Fixed IDs
        if (orderDao.countOrdersByHotelId(hotelId) == 0) {
            val order1Id = "order-demo-001"
            orderDao.insertOrder(RoomServiceOrderEntity(order1Id, hotelId, "room-105", OrderStatus.PENDING, 24.0, now, now, false, SyncStatus.SYNCED))
            itemDao.insertItems(listOf(
                RoomServiceItemEntity("item-demo-001", order1Id, "Desayuno Continental", 12.0, 2)
            ))

            val order2Id = "order-demo-002"
            orderDao.insertOrder(RoomServiceOrderEntity(order2Id, hotelId, "room-203", OrderStatus.PENDING, 26.0, now, now, false, SyncStatus.SYNCED))
            itemDao.insertItems(listOf(
                RoomServiceItemEntity("item-demo-002", order2Id, "Hamburguesa Gourmet", 18.0, 1),
                RoomServiceItemEntity("item-demo-003", order2Id, "Vino Tinto", 8.0, 1)
            ))
        }
    }
}
