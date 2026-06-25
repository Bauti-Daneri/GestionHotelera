package com.hotelops.domain.repository;

import com.hotelops.domain.model.Room;
import com.hotelops.domain.model.RoomStatus;
import com.hotelops.domain.model.RoomType;
import com.hotelops.domain.util.Resource;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J4\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&J\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00040\u00032\u0006\u0010\u000f\u001a\u00020\u0007H&J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u000f\u001a\u00020\u0007H&J\"\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00120\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J.\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0007H&\u00a8\u0006\u0017"}, d2 = {"Lcom/hotelops/domain/repository/RoomRepository;", "", "addRoom", "Lkotlinx/coroutines/flow/Flow;", "Lcom/hotelops/domain/util/Resource;", "Lcom/hotelops/domain/model/Room;", "hotelId", "", "roomNumber", "floor", "", "type", "Lcom/hotelops/domain/model/RoomType;", "deleteRoom", "", "roomId", "getRoomById", "getRooms", "", "updateRoomStatus", "status", "Lcom/hotelops/domain/model/RoomStatus;", "cleanedBy", "app_debug"})
public abstract interface RoomRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<java.util.List<com.hotelops.domain.model.Room>>> getRooms(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.Room>> getRoomById(@org.jetbrains.annotations.NotNull()
    java.lang.String roomId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.Room>> updateRoomStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String roomId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.RoomStatus status, @org.jetbrains.annotations.Nullable()
    java.lang.String cleanedBy);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.Room>> addRoom(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    java.lang.String roomNumber, int floor, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.RoomType type);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<kotlin.Unit>> deleteRoom(@org.jetbrains.annotations.NotNull()
    java.lang.String roomId);
}