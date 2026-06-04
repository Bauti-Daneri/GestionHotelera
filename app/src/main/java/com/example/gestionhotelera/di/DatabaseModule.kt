package com.example.gestionhotelera.di

import android.content.Context
import androidx.room.Room
import com.example.gestionhotelera.data.local.HotelOpsDatabase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): HotelOpsDatabase {
        return Room.databaseBuilder(
            context,
            HotelOpsDatabase::class.java,
            "hotel_ops_db"
        ).fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    fun provideHotelDao(db: HotelOpsDatabase) = db.hotelDao

    @Provides
    fun provideUserDao(db: HotelOpsDatabase) = db.userDao

    @Provides
    fun provideRoomDao(db: HotelOpsDatabase) = db.roomDao

    @Provides
    fun provideMaintenanceTicketDao(db: HotelOpsDatabase) = db.maintenanceTicketDao

    @Provides
    fun provideRoomServiceOrderDao(db: HotelOpsDatabase) = db.roomServiceOrderDao

    @Provides
    fun provideRoomServiceItemDao(db: HotelOpsDatabase) = db.roomServiceItemDao

    @Provides
    fun provideRoomHousekeeperAssignmentDao(db: HotelOpsDatabase) = db.roomHousekeeperAssignmentDao

    @Provides
    fun provideRoomServiceMenuItemDao(db: HotelOpsDatabase) = db.roomServiceMenuItemDao
}
