package com.hotelops.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hotelops.data.local.dao.*
import com.hotelops.data.local.entity.*

@Database(
    entities = [
        HotelEntity::class,
        UserEntity::class,
        RoomEntity::class,
        MaintenanceTicketEntity::class,
        RoomServiceOrderEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class HotelOpsDatabase : RoomDatabase() {
    abstract fun hotelDao(): HotelDao
    abstract fun userDao(): UserDao
    abstract fun roomDao(): RoomDao
    abstract fun maintenanceDao(): MaintenanceDao
    abstract fun roomServiceDao(): RoomServiceDao

    companion object {
        const val DATABASE_NAME = "hotelops_db"
    }
}
