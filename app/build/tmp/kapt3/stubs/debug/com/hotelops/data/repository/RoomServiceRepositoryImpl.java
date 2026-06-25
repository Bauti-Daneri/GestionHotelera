package com.hotelops.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import com.hotelops.data.local.dao.RoomServiceDao;
import com.hotelops.data.local.entity.RoomServiceOrderEntity;
import com.hotelops.domain.model.OrderItem;
import com.hotelops.domain.model.OrderStatus;
import com.hotelops.domain.model.RoomServiceOrder;
import com.hotelops.domain.repository.RoomServiceRepository;
import com.hotelops.domain.util.Resource;
import kotlinx.coroutines.flow.Flow;
import java.util.UUID;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006JD\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0014\u001a\u00020\fH\u0016J\"\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00100\t0\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016J$\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/hotelops/data/repository/RoomServiceRepositoryImpl;", "Lcom/hotelops/domain/repository/RoomServiceRepository;", "roomServiceDao", "Lcom/hotelops/data/local/dao/RoomServiceDao;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/hotelops/data/local/dao/RoomServiceDao;Lcom/google/firebase/firestore/FirebaseFirestore;)V", "createOrder", "Lkotlinx/coroutines/flow/Flow;", "Lcom/hotelops/domain/util/Resource;", "Lcom/hotelops/domain/model/RoomServiceOrder;", "hotelId", "", "roomId", "guestName", "items", "", "Lcom/hotelops/domain/model/OrderItem;", "specialInstructions", "getOrderById", "orderId", "getOrders", "updateOrderStatus", "status", "Lcom/hotelops/domain/model/OrderStatus;", "Companion", "app_debug"})
public final class RoomServiceRepositoryImpl implements com.hotelops.domain.repository.RoomServiceRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.data.local.dao.RoomServiceDao roomServiceDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COL_ORDERS = "room_service_orders";
    @org.jetbrains.annotations.NotNull()
    public static final com.hotelops.data.repository.RoomServiceRepositoryImpl.Companion Companion = null;
    
    @javax.inject.Inject()
    public RoomServiceRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.dao.RoomServiceDao roomServiceDao, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<java.util.List<com.hotelops.domain.model.RoomServiceOrder>>> getOrders(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.RoomServiceOrder>> getOrderById(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.RoomServiceOrder>> createOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    java.lang.String roomId, @org.jetbrains.annotations.NotNull()
    java.lang.String guestName, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.OrderItem> items, @org.jetbrains.annotations.Nullable()
    java.lang.String specialInstructions) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.RoomServiceOrder>> updateOrderStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.OrderStatus status) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/hotelops/data/repository/RoomServiceRepositoryImpl$Companion;", "", "()V", "COL_ORDERS", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}