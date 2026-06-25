package com.hotelops.presentation.roomservice;

import androidx.lifecycle.ViewModel;
import com.hotelops.data.local.dao.RoomDao;
import com.hotelops.data.local.dao.RoomServiceDao;
import com.hotelops.data.local.entity.RoomServiceOrderEntity;
import com.hotelops.domain.model.OrderItem;
import com.hotelops.domain.model.OrderStatus;
import com.hotelops.domain.model.Room;
import com.hotelops.domain.model.RoomServiceOrder;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import java.util.UUID;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J4\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u0011J\u0006\u0010\u0019\u001a\u00020\u000fJ\u000e\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u001b\u001a\u00020\u000f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ\u0006\u0010\u001e\u001a\u00020\u000fJ\u0016\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u001dR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\""}, d2 = {"Lcom/hotelops/presentation/roomservice/RoomServiceViewModel;", "Landroidx/lifecycle/ViewModel;", "roomServiceDao", "Lcom/hotelops/data/local/dao/RoomServiceDao;", "roomDao", "Lcom/hotelops/data/local/dao/RoomDao;", "(Lcom/hotelops/data/local/dao/RoomServiceDao;Lcom/hotelops/data/local/dao/RoomDao;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/hotelops/presentation/roomservice/RoomServiceState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "createOrder", "", "hotelId", "", "room", "Lcom/hotelops/domain/model/Room;", "guestName", "items", "", "Lcom/hotelops/domain/model/OrderItem;", "instructions", "hideDialog", "loadData", "setFilter", "status", "Lcom/hotelops/domain/model/OrderStatus;", "showCreateDialog", "updateOrderStatus", "orderId", "newStatus", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class RoomServiceViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.data.local.dao.RoomServiceDao roomServiceDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.data.local.dao.RoomDao roomDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hotelops.presentation.roomservice.RoomServiceState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hotelops.presentation.roomservice.RoomServiceState> state = null;
    
    @javax.inject.Inject()
    public RoomServiceViewModel(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.dao.RoomServiceDao roomServiceDao, @org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.dao.RoomDao roomDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hotelops.presentation.roomservice.RoomServiceState> getState() {
        return null;
    }
    
    public final void loadData(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId) {
    }
    
    public final void setFilter(@org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.OrderStatus status) {
    }
    
    public final void showCreateDialog() {
    }
    
    public final void hideDialog() {
    }
    
    public final void createOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.Room room, @org.jetbrains.annotations.NotNull()
    java.lang.String guestName, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.OrderItem> items, @org.jetbrains.annotations.NotNull()
    java.lang.String instructions) {
    }
    
    public final void updateOrderStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String orderId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.OrderStatus newStatus) {
    }
}