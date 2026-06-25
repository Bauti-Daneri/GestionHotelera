package com.hotelops.di;

import com.hotelops.data.repository.AuthRepositoryImpl;
import com.hotelops.data.repository.MaintenanceRepositoryImpl;
import com.hotelops.data.repository.RoomRepositoryImpl;
import com.hotelops.data.repository.RoomServiceRepositoryImpl;
import com.hotelops.data.repository.UserRepositoryImpl;
import com.hotelops.domain.repository.AuthRepository;
import com.hotelops.domain.repository.MaintenanceRepository;
import com.hotelops.domain.repository.RoomRepository;
import com.hotelops.domain.repository.RoomServiceRepository;
import com.hotelops.domain.repository.UserRepository;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\fH\'J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000fH\'J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0012H\'\u00a8\u0006\u0013"}, d2 = {"Lcom/hotelops/di/RepositoryModule;", "", "()V", "bindAuthRepository", "Lcom/hotelops/domain/repository/AuthRepository;", "impl", "Lcom/hotelops/data/repository/AuthRepositoryImpl;", "bindMaintenanceRepository", "Lcom/hotelops/domain/repository/MaintenanceRepository;", "Lcom/hotelops/data/repository/MaintenanceRepositoryImpl;", "bindRoomRepository", "Lcom/hotelops/domain/repository/RoomRepository;", "Lcom/hotelops/data/repository/RoomRepositoryImpl;", "bindRoomServiceRepository", "Lcom/hotelops/domain/repository/RoomServiceRepository;", "Lcom/hotelops/data/repository/RoomServiceRepositoryImpl;", "bindUserRepository", "Lcom/hotelops/domain/repository/UserRepository;", "Lcom/hotelops/data/repository/UserRepositoryImpl;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class RepositoryModule {
    
    public RepositoryModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.hotelops.domain.repository.AuthRepository bindAuthRepository(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.repository.AuthRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.hotelops.domain.repository.RoomRepository bindRoomRepository(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.repository.RoomRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.hotelops.domain.repository.UserRepository bindUserRepository(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.repository.UserRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.hotelops.domain.repository.MaintenanceRepository bindMaintenanceRepository(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.repository.MaintenanceRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.hotelops.domain.repository.RoomServiceRepository bindRoomServiceRepository(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.repository.RoomServiceRepositoryImpl impl);
}