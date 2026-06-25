package com.hotelops.data.local.dao;

import androidx.room.*;
import com.hotelops.data.local.entity.RoomServiceOrderEntity;
import com.hotelops.domain.model.OrderStatus;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000f0\u000e2\u0006\u0010\f\u001a\u00020\tH\'J\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u0018\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000e2\u0006\u0010\b\u001a\u00020\tH\'J\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000f0\u000e2\u0006\u0010\f\u001a\u00020\tH\'J$\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000f0\u000e2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\tH\'J$\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000f0\u000e2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0018H\'J\u0016\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u001a\u001a\u00020\u00032\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u001cJ\u001e\u0010\u001d\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001fH\u00a7@\u00a2\u0006\u0002\u0010 J\u0016\u0010!\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J0\u0010\"\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010\u001fH\u00a7@\u00a2\u0006\u0002\u0010%\u00a8\u0006&"}, d2 = {"Lcom/hotelops/data/local/dao/RoomServiceDao;", "", "deleteOrder", "", "order", "Lcom/hotelops/data/local/entity/RoomServiceOrderEntity;", "(Lcom/hotelops/data/local/entity/RoomServiceOrderEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOrderById", "orderId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOrdersByHotel", "hotelId", "getActiveOrders", "Lkotlinx/coroutines/flow/Flow;", "", "getDirtyOrders", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrderById", "getOrdersByHotel", "getOrdersByRoom", "roomId", "getOrdersByStatus", "status", "Lcom/hotelops/domain/model/OrderStatus;", "insertOrder", "insertOrders", "orders", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markAsSynced", "syncedAt", "", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateOrder", "updateOrderStatus", "updatedAt", "deliveredAt", "(Ljava/lang/String;Lcom/hotelops/domain/model/OrderStatus;JLjava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface RoomServiceDao {
    
    @androidx.room.Query(value = "SELECT * FROM room_service_orders WHERE hotelId = :hotelId ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hotelops.data.local.entity.RoomServiceOrderEntity>> getOrdersByHotel(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId);
    
    @androidx.room.Query(value = "SELECT * FROM room_service_orders WHERE id = :orderId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.hotelops.data.local.entity.RoomServiceOrderEntity> getOrderById(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId);
    
    @androidx.room.Query(value = "SELECT * FROM room_service_orders WHERE hotelId = :hotelId AND status = :status ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hotelops.data.local.entity.RoomServiceOrderEntity>> getOrdersByStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.OrderStatus status);
    
    @androidx.room.Query(value = "SELECT * FROM room_service_orders WHERE hotelId = :hotelId AND roomId = :roomId ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hotelops.data.local.entity.RoomServiceOrderEntity>> getOrdersByRoom(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    java.lang.String roomId);
    
    @androidx.room.Query(value = "SELECT * FROM room_service_orders WHERE hotelId = :hotelId AND status IN (\'PENDING\', \'PREPARING\') ORDER BY createdAt ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hotelops.data.local.entity.RoomServiceOrderEntity>> getActiveOrders(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertOrder(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.entity.RoomServiceOrderEntity order, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertOrders(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.data.local.entity.RoomServiceOrderEntity> orders, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateOrder(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.entity.RoomServiceOrderEntity order, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOrder(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.entity.RoomServiceOrderEntity order, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM room_service_orders WHERE id = :orderId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOrderById(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM room_service_orders WHERE isDirty = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDirtyOrders(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hotelops.data.local.entity.RoomServiceOrderEntity>> $completion);
    
    @androidx.room.Query(value = "UPDATE room_service_orders SET syncedAt = :syncedAt, isDirty = 0 WHERE id = :orderId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markAsSynced(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, long syncedAt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE room_service_orders SET status = :status, updatedAt = :updatedAt, deliveredAt = :deliveredAt, isDirty = 1 WHERE id = :orderId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateOrderStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.OrderStatus status, long updatedAt, @org.jetbrains.annotations.Nullable()
    java.lang.Long deliveredAt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM room_service_orders WHERE hotelId = :hotelId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOrdersByHotel(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}