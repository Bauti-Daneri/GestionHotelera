package com.hotelops.sync;

import android.content.Context;
import androidx.hilt.work.HiltWorker;
import androidx.work.CoroutineWorker;
import androidx.work.WorkerParameters;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hotelops.data.local.dao.*;
import com.hotelops.domain.model.*;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import timber.log.Timber;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001BK\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\u000e\u0010\u0013\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010\u0015J\u000e\u0010\u0016\u001a\u00020\u0017H\u0082@\u00a2\u0006\u0002\u0010\u0015J\u000e\u0010\u0018\u001a\u00020\u0017H\u0082@\u00a2\u0006\u0002\u0010\u0015J\u000e\u0010\u0019\u001a\u00020\u0017H\u0082@\u00a2\u0006\u0002\u0010\u0015J\u000e\u0010\u001a\u001a\u00020\u0017H\u0082@\u00a2\u0006\u0002\u0010\u0015J\u000e\u0010\u001b\u001a\u00020\u0017H\u0082@\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/hotelops/sync/SyncWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "hotelDao", "Lcom/hotelops/data/local/dao/HotelDao;", "userDao", "Lcom/hotelops/data/local/dao/UserDao;", "roomDao", "Lcom/hotelops/data/local/dao/RoomDao;", "maintenanceDao", "Lcom/hotelops/data/local/dao/MaintenanceDao;", "roomServiceDao", "Lcom/hotelops/data/local/dao/RoomServiceDao;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/hotelops/data/local/dao/HotelDao;Lcom/hotelops/data/local/dao/UserDao;Lcom/hotelops/data/local/dao/RoomDao;Lcom/hotelops/data/local/dao/MaintenanceDao;Lcom/hotelops/data/local/dao/RoomServiceDao;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncHotels", "", "syncMaintenanceTickets", "syncRoomServiceOrders", "syncRooms", "syncUsers", "app_debug"})
@androidx.hilt.work.HiltWorker()
public final class SyncWorker extends androidx.work.CoroutineWorker {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.data.local.dao.HotelDao hotelDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.data.local.dao.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.data.local.dao.RoomDao roomDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.data.local.dao.MaintenanceDao maintenanceDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.data.local.dao.RoomServiceDao roomServiceDao = null;
    
    @dagger.assisted.AssistedInject()
    public SyncWorker(@dagger.assisted.Assisted()
    @org.jetbrains.annotations.NotNull()
    android.content.Context appContext, @dagger.assisted.Assisted()
    @org.jetbrains.annotations.NotNull()
    androidx.work.WorkerParameters workerParams, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore, @org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.dao.HotelDao hotelDao, @org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.dao.UserDao userDao, @org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.dao.RoomDao roomDao, @org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.dao.MaintenanceDao maintenanceDao, @org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.dao.RoomServiceDao roomServiceDao) {
        super(null, null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> $completion) {
        return null;
    }
    
    private final java.lang.Object syncHotels(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object syncUsers(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object syncRooms(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object syncMaintenanceTickets(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object syncRoomServiceOrders(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}