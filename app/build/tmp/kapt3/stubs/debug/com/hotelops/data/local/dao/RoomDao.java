package com.hotelops.data.local.dao;

import androidx.room.*;
import com.hotelops.data.local.entity.RoomEntity;
import com.hotelops.domain.model.RoomStatus;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00112\u0006\u0010\b\u001a\u00020\tH\'J \u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\u0014J$\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\u00112\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017H\'J\u001c\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\u00112\u0006\u0010\f\u001a\u00020\tH\'J$\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\u00112\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001bH\'J\u0016\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u001d\u001a\u00020\u00032\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u001fJ\u001e\u0010 \u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010!\u001a\u00020\"H\u00a7@\u00a2\u0006\u0002\u0010#J\u0016\u0010$\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J2\u0010%\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010&\u001a\u0004\u0018\u00010\"2\b\u0010\'\u001a\u0004\u0018\u00010\tH\u00a7@\u00a2\u0006\u0002\u0010(\u00a8\u0006)"}, d2 = {"Lcom/hotelops/data/local/dao/RoomDao;", "", "deleteRoom", "", "room", "Lcom/hotelops/data/local/entity/RoomEntity;", "(Lcom/hotelops/data/local/entity/RoomEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteRoomById", "roomId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteRoomsByHotel", "hotelId", "getDirtyRooms", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRoomById", "Lkotlinx/coroutines/flow/Flow;", "getRoomByNumber", "roomNumber", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRoomsByFloor", "floor", "", "getRoomsByHotel", "getRoomsByStatus", "status", "Lcom/hotelops/domain/model/RoomStatus;", "insertRoom", "insertRooms", "rooms", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markAsSynced", "syncedAt", "", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateRoom", "updateRoomStatus", "cleanedAt", "cleanedBy", "(Ljava/lang/String;Lcom/hotelops/domain/model/RoomStatus;Ljava/lang/Long;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface RoomDao {
    
    @androidx.room.Query(value = "SELECT * FROM rooms WHERE hotelId = :hotelId ORDER BY floor ASC, roomNumber ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hotelops.data.local.entity.RoomEntity>> getRoomsByHotel(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId);
    
    @androidx.room.Query(value = "SELECT * FROM rooms WHERE id = :roomId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.hotelops.data.local.entity.RoomEntity> getRoomById(@org.jetbrains.annotations.NotNull()
    java.lang.String roomId);
    
    @androidx.room.Query(value = "SELECT * FROM rooms WHERE hotelId = :hotelId AND status = :status ORDER BY floor ASC, roomNumber ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hotelops.data.local.entity.RoomEntity>> getRoomsByStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.RoomStatus status);
    
    @androidx.room.Query(value = "SELECT * FROM rooms WHERE hotelId = :hotelId AND floor = :floor ORDER BY roomNumber ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hotelops.data.local.entity.RoomEntity>> getRoomsByFloor(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, int floor);
    
    @androidx.room.Query(value = "SELECT * FROM rooms WHERE roomNumber = :roomNumber AND hotelId = :hotelId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRoomByNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String roomNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.hotelops.data.local.entity.RoomEntity> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertRoom(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.entity.RoomEntity room, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertRooms(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.data.local.entity.RoomEntity> rooms, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateRoom(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.entity.RoomEntity room, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteRoom(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.entity.RoomEntity room, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM rooms WHERE id = :roomId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteRoomById(@org.jetbrains.annotations.NotNull()
    java.lang.String roomId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM rooms WHERE isDirty = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDirtyRooms(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hotelops.data.local.entity.RoomEntity>> $completion);
    
    @androidx.room.Query(value = "UPDATE rooms SET syncedAt = :syncedAt, isDirty = 0 WHERE id = :roomId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markAsSynced(@org.jetbrains.annotations.NotNull()
    java.lang.String roomId, long syncedAt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE rooms SET status = :status, lastCleanedAt = :cleanedAt, lastCleanedBy = :cleanedBy, isDirty = 1 WHERE id = :roomId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateRoomStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String roomId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.RoomStatus status, @org.jetbrains.annotations.Nullable()
    java.lang.Long cleanedAt, @org.jetbrains.annotations.Nullable()
    java.lang.String cleanedBy, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM rooms WHERE hotelId = :hotelId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteRoomsByHotel(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}