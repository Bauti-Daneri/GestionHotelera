package com.hotelops.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import com.hotelops.data.local.dao.RoomDao;
import com.hotelops.data.local.entity.RoomEntity;
import com.hotelops.domain.model.Room;
import com.hotelops.domain.model.RoomStatus;
import com.hotelops.domain.model.RoomType;
import com.hotelops.domain.repository.RoomRepository;
import com.hotelops.domain.util.Resource;
import kotlinx.coroutines.flow.Flow;
import java.util.UUID;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J4\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\t0\b2\u0006\u0010\u0014\u001a\u00020\fH\u0016J\u001c\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0014\u001a\u00020\fH\u0016J\"\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00170\t0\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016J.\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/hotelops/data/repository/RoomRepositoryImpl;", "Lcom/hotelops/domain/repository/RoomRepository;", "roomDao", "Lcom/hotelops/data/local/dao/RoomDao;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/hotelops/data/local/dao/RoomDao;Lcom/google/firebase/firestore/FirebaseFirestore;)V", "addRoom", "Lkotlinx/coroutines/flow/Flow;", "Lcom/hotelops/domain/util/Resource;", "Lcom/hotelops/domain/model/Room;", "hotelId", "", "roomNumber", "floor", "", "type", "Lcom/hotelops/domain/model/RoomType;", "deleteRoom", "", "roomId", "getRoomById", "getRooms", "", "updateRoomStatus", "status", "Lcom/hotelops/domain/model/RoomStatus;", "cleanedBy", "Companion", "app_debug"})
public final class RoomRepositoryImpl implements com.hotelops.domain.repository.RoomRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.data.local.dao.RoomDao roomDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COL_ROOMS = "rooms";
    @org.jetbrains.annotations.NotNull()
    public static final com.hotelops.data.repository.RoomRepositoryImpl.Companion Companion = null;
    
    @javax.inject.Inject()
    public RoomRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.dao.RoomDao roomDao, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<java.util.List<com.hotelops.domain.model.Room>>> getRooms(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.Room>> getRoomById(@org.jetbrains.annotations.NotNull()
    java.lang.String roomId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.Room>> updateRoomStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String roomId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.RoomStatus status, @org.jetbrains.annotations.Nullable()
    java.lang.String cleanedBy) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.Room>> addRoom(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    java.lang.String roomNumber, int floor, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.RoomType type) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<kotlin.Unit>> deleteRoom(@org.jetbrains.annotations.NotNull()
    java.lang.String roomId) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/hotelops/data/repository/RoomRepositoryImpl$Companion;", "", "()V", "COL_ROOMS", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}