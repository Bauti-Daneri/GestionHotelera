package com.hotelops.presentation.housekeeping;

import androidx.lifecycle.ViewModel;
import com.hotelops.domain.model.Room;
import com.hotelops.domain.model.RoomStatus;
import com.hotelops.domain.usecase.room.GetRoomsUseCase;
import com.hotelops.domain.usecase.room.UpdateRoomStatusUseCase;
import com.hotelops.domain.util.Resource;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u001e\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0011R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/hotelops/presentation/housekeeping/HousekeepingViewModel;", "Landroidx/lifecycle/ViewModel;", "getRoomsUseCase", "Lcom/hotelops/domain/usecase/room/GetRoomsUseCase;", "updateRoomStatusUseCase", "Lcom/hotelops/domain/usecase/room/UpdateRoomStatusUseCase;", "(Lcom/hotelops/domain/usecase/room/GetRoomsUseCase;Lcom/hotelops/domain/usecase/room/UpdateRoomStatusUseCase;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/hotelops/presentation/housekeeping/HousekeepingState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadRooms", "", "hotelId", "", "setFilter", "status", "Lcom/hotelops/domain/model/RoomStatus;", "updateRoomStatus", "roomId", "newStatus", "cleanedBy", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class HousekeepingViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.room.GetRoomsUseCase getRoomsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.room.UpdateRoomStatusUseCase updateRoomStatusUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hotelops.presentation.housekeeping.HousekeepingState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hotelops.presentation.housekeeping.HousekeepingState> state = null;
    
    @javax.inject.Inject()
    public HousekeepingViewModel(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.room.GetRoomsUseCase getRoomsUseCase, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.room.UpdateRoomStatusUseCase updateRoomStatusUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hotelops.presentation.housekeeping.HousekeepingState> getState() {
        return null;
    }
    
    public final void loadRooms(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId) {
    }
    
    public final void setFilter(@org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.RoomStatus status) {
    }
    
    public final void updateRoomStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String roomId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.RoomStatus newStatus, @org.jetbrains.annotations.NotNull()
    java.lang.String cleanedBy) {
    }
}