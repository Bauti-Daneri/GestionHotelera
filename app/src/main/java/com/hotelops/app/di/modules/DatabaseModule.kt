package com.hotelops.app.di.modules

import android.content.Context
import androidx.room.Room
import com.hotelops.app.data.local.database.HotelOpsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo Hilt para proveer instancias de Room Database
 * Centraliza la configuración de BD para toda la aplicación
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideHotelOpsDatabase(
        @ApplicationContext context: Context
    ): HotelOpsDatabase {
        return Room.databaseBuilder(
            context,
            HotelOpsDatabase::class.java,
            "hotelops_database"
        )
            .fallbackToDestructiveMigration()  // Solo para desarrollo, cambiar en producción
            .build()
    }

    @Singleton
    @Provides
    fun provideTenantDao(database: HotelOpsDatabase) =
        database.tenantDao()

    @Singleton
    @Provides
    fun provideUserDao(database: HotelOpsDatabase) =
        database.userDao()

    @Singleton
    @Provides
    fun provideHotelDao(database: HotelOpsDatabase) =
        database.hotelDao()

    @Singleton
    @Provides
    fun provideRoomDao(database: HotelOpsDatabase) =
        database.roomDao()

    @Singleton
    @Provides
    fun provideBookingDao(database: HotelOpsDatabase) =
        database.bookingDao()
}

