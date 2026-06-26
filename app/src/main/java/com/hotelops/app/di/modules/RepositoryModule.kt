package com.hotelops.app.di.modules

import com.hotelops.app.domain.repositories.BookingRepository
import com.hotelops.app.domain.repositories.HotelRepository
import com.hotelops.app.domain.repositories.RoomRepository
import com.hotelops.app.domain.repositories.TenantRepository
import com.hotelops.app.domain.repositories.UserRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo Hilt para inyectar implementaciones de repositorios
 * Mapea interfaces (domain) con implementaciones (data)
 * 
 * NOTA: Estas bindngs se completarán una vez implementadas las clases
 * en data/repositories/
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    // @Binds anotaciones irán aquí cuando se implementen los repositorios
    // @Binds
    // abstract fun bindTenantRepository(impl: TenantRepositoryImpl): TenantRepository
    //
    // @Binds
    // abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
    //
    // @Binds
    // abstract fun bindHotelRepository(impl: HotelRepositoryImpl): HotelRepository
    //
    // @Binds
    // abstract fun bindRoomRepository(impl: RoomRepositoryImpl): RoomRepository
    //
    // @Binds
    // abstract fun bindBookingRepository(impl: BookingRepositoryImpl): BookingRepository
}

