package com.hotelops.domain.usecase.room;

import com.hotelops.domain.model.Room;
import com.hotelops.domain.model.RoomType;
import com.hotelops.domain.repository.RoomRepository;
import com.hotelops.domain.util.Resource;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J5\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/hotelops/domain/usecase/room/AddRoomUseCase;", "", "roomRepository", "Lcom/hotelops/domain/repository/RoomRepository;", "(Lcom/hotelops/domain/repository/RoomRepository;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "Lcom/hotelops/domain/util/Resource;", "Lcom/hotelops/domain/model/Room;", "hotelId", "", "roomNumber", "floor", "", "type", "Lcom/hotelops/domain/model/RoomType;", "app_debug"})
public final class AddRoomUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.repository.RoomRepository roomRepository = null;
    
    @javax.inject.Inject()
    public AddRoomUseCase(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.repository.RoomRepository roomRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.Room>> invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    java.lang.String roomNumber, int floor, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.RoomType type) {
        return null;
    }
}