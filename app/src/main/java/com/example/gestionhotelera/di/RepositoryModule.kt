package com.example.gestionhotelera.di

import com.example.gestionhotelera.data.repository.*
import com.example.gestionhotelera.domain.repository.*
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
    abstract fun bindRoomRepository(impl: RoomRepositoryImpl): RoomRepository

    @Binds
    @Singleton
    abstract fun bindMaintenanceRepository(impl: MaintenanceRepositoryImpl): MaintenanceRepository

    @Binds
    @Singleton
    abstract fun bindRoomServiceRepository(impl: RoomServiceRepositoryImpl): RoomServiceRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    @Singleton
    abstract fun bindHotelRepository(impl: HotelRepositoryImpl): HotelRepository
}
