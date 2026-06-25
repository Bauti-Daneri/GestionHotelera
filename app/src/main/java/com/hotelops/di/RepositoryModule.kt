package com.hotelops.di

import com.hotelops.data.repository.AuthRepositoryImpl
import com.hotelops.data.repository.MaintenanceRepositoryImpl
import com.hotelops.data.repository.RoomRepositoryImpl
import com.hotelops.data.repository.RoomServiceRepositoryImpl
import com.hotelops.data.repository.UserRepositoryImpl
import com.hotelops.domain.repository.AuthRepository
import com.hotelops.domain.repository.MaintenanceRepository
import com.hotelops.domain.repository.RoomRepository
import com.hotelops.domain.repository.RoomServiceRepository
import com.hotelops.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindRoomRepository(impl: RoomRepositoryImpl): RoomRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindMaintenanceRepository(impl: MaintenanceRepositoryImpl): MaintenanceRepository

    @Binds
    @Singleton
    abstract fun bindRoomServiceRepository(impl: RoomServiceRepositoryImpl): RoomServiceRepository
}
