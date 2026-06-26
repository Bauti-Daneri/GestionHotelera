package com.hotelops.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hotelops.app.data.local.dao.*
import com.hotelops.app.data.local.entities.*

@Database(
    entities = [
        TenantEntity::class,
        UserEntity::class,
        HotelEntity::class,
        RoomEntity::class,
        BookingEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class HotelOpsDatabase : RoomDatabase() {
    abstract fun tenantDao(): TenantDao
    abstract fun userDao(): UserDao
    abstract fun hotelDao(): HotelDao
    abstract fun roomDao(): RoomDao
    abstract fun bookingDao(): BookingDao
}

