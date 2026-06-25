package com.hotelops.data.local.dao;

import androidx.room.*;
import com.hotelops.data.local.entity.HotelEntity;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\rH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\f2\u0006\u0010\b\u001a\u00020\tH\'J\u0016\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0012\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u0017"}, d2 = {"Lcom/hotelops/data/local/dao/HotelDao;", "", "deleteHotel", "", "hotel", "Lcom/hotelops/data/local/entity/HotelEntity;", "(Lcom/hotelops/data/local/entity/HotelEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteHotelById", "hotelId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllHotels", "Lkotlinx/coroutines/flow/Flow;", "", "getDirtyHotels", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getHotelById", "insertHotel", "markAsSynced", "syncedAt", "", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateHotel", "app_debug"})
@androidx.room.Dao()
public abstract interface HotelDao {
    
    @androidx.room.Query(value = "SELECT * FROM hotels WHERE id = :hotelId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.hotelops.data.local.entity.HotelEntity> getHotelById(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId);
    
    @androidx.room.Query(value = "SELECT * FROM hotels")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hotelops.data.local.entity.HotelEntity>> getAllHotels();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertHotel(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.entity.HotelEntity hotel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateHotel(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.entity.HotelEntity hotel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteHotel(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.entity.HotelEntity hotel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM hotels WHERE id = :hotelId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteHotelById(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM hotels WHERE isDirty = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDirtyHotels(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hotelops.data.local.entity.HotelEntity>> $completion);
    
    @androidx.room.Query(value = "UPDATE hotels SET syncedAt = :syncedAt, isDirty = 0 WHERE id = :hotelId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markAsSynced(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, long syncedAt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}