package com.hotelops.di;

import android.content.Context;
import androidx.room.Room;
import com.hotelops.data.local.dao.*;
import com.hotelops.data.local.database.HotelOpsDatabase;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/hotelops/di/DatabaseModule;", "", "()V", "provideHotelDao", "Lcom/hotelops/data/local/dao/HotelDao;", "database", "Lcom/hotelops/data/local/database/HotelOpsDatabase;", "provideHotelOpsDatabase", "context", "Landroid/content/Context;", "provideMaintenanceDao", "Lcom/hotelops/data/local/dao/MaintenanceDao;", "provideRoomDao", "Lcom/hotelops/data/local/dao/RoomDao;", "provideRoomServiceDao", "Lcom/hotelops/data/local/dao/RoomServiceDao;", "provideUserDao", "Lcom/hotelops/data/local/dao/UserDao;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.hotelops.di.DatabaseModule INSTANCE = null;
    
    private DatabaseModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.data.local.database.HotelOpsDatabase provideHotelOpsDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.data.local.dao.HotelDao provideHotelDao(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.database.HotelOpsDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.data.local.dao.UserDao provideUserDao(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.database.HotelOpsDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.data.local.dao.RoomDao provideRoomDao(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.database.HotelOpsDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.data.local.dao.MaintenanceDao provideMaintenanceDao(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.database.HotelOpsDatabase database) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.data.local.dao.RoomServiceDao provideRoomServiceDao(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.database.HotelOpsDatabase database) {
        return null;
    }
}