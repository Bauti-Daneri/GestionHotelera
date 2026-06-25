package com.hotelops.data.local.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.hotelops.data.local.dao.*;
import com.hotelops.data.local.entity.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\u000e"}, d2 = {"Lcom/hotelops/data/local/database/HotelOpsDatabase;", "Landroidx/room/RoomDatabase;", "()V", "hotelDao", "Lcom/hotelops/data/local/dao/HotelDao;", "maintenanceDao", "Lcom/hotelops/data/local/dao/MaintenanceDao;", "roomDao", "Lcom/hotelops/data/local/dao/RoomDao;", "roomServiceDao", "Lcom/hotelops/data/local/dao/RoomServiceDao;", "userDao", "Lcom/hotelops/data/local/dao/UserDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.hotelops.data.local.entity.HotelEntity.class, com.hotelops.data.local.entity.UserEntity.class, com.hotelops.data.local.entity.RoomEntity.class, com.hotelops.data.local.entity.MaintenanceTicketEntity.class, com.hotelops.data.local.entity.RoomServiceOrderEntity.class}, version = 1, exportSchema = false)
@androidx.room.TypeConverters(value = {com.hotelops.data.local.database.Converters.class})
public abstract class HotelOpsDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DATABASE_NAME = "hotelops_db";
    @org.jetbrains.annotations.NotNull()
    public static final com.hotelops.data.local.database.HotelOpsDatabase.Companion Companion = null;
    
    public HotelOpsDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.hotelops.data.local.dao.HotelDao hotelDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.hotelops.data.local.dao.UserDao userDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.hotelops.data.local.dao.RoomDao roomDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.hotelops.data.local.dao.MaintenanceDao maintenanceDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.hotelops.data.local.dao.RoomServiceDao roomServiceDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/hotelops/data/local/database/HotelOpsDatabase$Companion;", "", "()V", "DATABASE_NAME", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}