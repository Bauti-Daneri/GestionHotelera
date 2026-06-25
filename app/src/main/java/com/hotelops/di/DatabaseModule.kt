package com.hotelops.di

import android.content.Context
import androidx.room.Room
import com.hotelops.data.local.dao.*
import com.hotelops.data.local.database.HotelOpsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideHotelOpsDatabase(
        @ApplicationContext context: Context
    ): HotelOpsDatabase {
        return Room.databaseBuilder(
            context,
            HotelOpsDatabase::class.java,
            HotelOpsDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideHotelDao(database: HotelOpsDatabase): HotelDao {
        return database.hotelDao()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: HotelOpsDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideRoomDao(database: HotelOpsDatabase): RoomDao {
        return database.roomDao()
    }

    @Provides
    @Singleton
    fun provideMaintenanceDao(database: HotelOpsDatabase): MaintenanceDao {
        return database.maintenanceDao()
    }

    @Provides
    @Singleton
    fun provideRoomServiceDao(database: HotelOpsDatabase): RoomServiceDao {
        return database.roomServiceDao()
    }
}
