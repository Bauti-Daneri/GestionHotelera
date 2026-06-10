package com.example.gestionhotelera.data.local

import com.example.gestionhotelera.domain.model.*
import javax.inject.Inject

class DatabaseInitializer @Inject constructor(
    private val hotelDao: HotelDao,
    private val userDao: UserDao,
    private val roomDao: RoomDao,
    private val ticketDao: MaintenanceTicketDao,
    private val orderDao: RoomServiceOrderDao,
    private val assignmentDao: RoomHousekeeperAssignmentDao,
    private val menuDao: RoomServiceMenuItemDao
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
                    RoomEntity(
                        id = id,
                        hotelId = hotelId,
                        number = num,
                        floor = "1",
                        type = "Standard",
                        status = if (index % 2 == 0) RoomStatus.CLEAN else RoomStatus.DIRTY,
                        lastCleaned = now,
                        createdAt = now,
                        updatedAt = now,
                        isDirty = false,
                        syncStatus = SyncStatus.SYNCED
                    )
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
                RoomServiceMenuItemEntity("menu-01", hotelId, "Hamburguesa Completa", "Con papas fritas y bebida", 15.0, "Cena", null, true, now, now, false, SyncStatus.SYNCED),
                RoomServiceMenuItemEntity("menu-02", hotelId, "Ensalada César", "Pollo, lechuga, croutons y aderezo", 12.0, "Almuerzo", null, true, now, now, false, SyncStatus.SYNCED),
                RoomServiceMenuItemEntity("menu-03", hotelId, "Desayuno Americano", "Huevos, tocino, tostadas y café", 10.0, "Desayuno", null, true, now, now, false, SyncStatus.SYNCED),
                RoomServiceMenuItemEntity("menu-04", hotelId, "Sándwich de Pollo", "Con lechuga, tomate y mayonesa", 9.0, "Snack", null, true, now, now, false, SyncStatus.SYNCED),
                RoomServiceMenuItemEntity("menu-05", hotelId, "Jugo de Naranja", "Natural 500ml", 4.0, "Bebidas", null, true, now, now, false, SyncStatus.SYNCED)
            )
            menuItems.forEach { menuDao.insertMenuItem(it) }
        }

        // Seed Orders - Fixed IDs
        if (orderDao.countOrdersByHotelId(hotelId) == 0) {
            val order1Id = "order-demo-001"
            orderDao.insertOrder(
                RoomServiceOrderEntity(
                    id = order1Id,
                    hotelId = hotelId,
                    roomId = "room-105",
                    description = "Desayuno Americano",
                    status = OrderStatus.PENDING,
                    price = 24.0,
                    createdAt = now,
                    updatedAt = now,
                    isDirty = false,
                    syncStatus = SyncStatus.SYNCED
                )
            )

            val order2Id = "order-demo-002"
            orderDao.insertOrder(
                RoomServiceOrderEntity(
                    id = order2Id,
                    hotelId = hotelId,
                    roomId = "room-203",
                    description = "Cena: Hamburguesa completa",
                    status = OrderStatus.PENDING,
                    price = 26.0,
                    createdAt = now,
                    updatedAt = now,
                    isDirty = false,
                    syncStatus = SyncStatus.SYNCED
                )
            )
        }
    }
}
