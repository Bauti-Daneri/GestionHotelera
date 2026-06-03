package com.example.gestionhotelera.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        HotelEntity::class,
        UserEntity::class,
        RoomEntity::class,
        MaintenanceTicketEntity::class,
        RoomServiceOrderEntity::class,
        RoomServiceItemEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class HotelOpsDatabase : RoomDatabase() {
    abstract val hotelDao: HotelDao
    abstract val userDao: UserDao
    abstract val roomDao: RoomDao
    abstract val maintenanceTicketDao: MaintenanceTicketDao
    abstract val roomServiceOrderDao: RoomServiceOrderDao
    abstract val roomServiceItemDao: RoomServiceItemDao
}
