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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u00a2\u0006\u0002\u0010\fJ\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\bH\u00c6\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\t\u0010\u0018\u001a\u00020\bH\u00c6\u0003JI\u0010\u0019\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001R\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f\u00a8\u0006 "}, d2 = {"Lcom/hotelops/presentation/roomservice/RoomServiceState;", "", "orders", "", "Lcom/hotelops/domain/model/RoomServiceOrder;", "rooms", "Lcom/hotelops/domain/model/Room;", "isLoading", "", "filterStatus", "Lcom/hotelops/domain/model/OrderStatus;", "showCreateDialog", "(Ljava/util/List;Ljava/util/List;ZLcom/hotelops/domain/model/OrderStatus;Z)V", "getFilterStatus", "()Lcom/hotelops/domain/model/OrderStatus;", "()Z", "getOrders", "()Ljava/util/List;", "getRooms", "getShowCreateDialog", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "app_debug"})
public final class RoomServiceState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hotelops.domain.model.RoomServiceOrder> orders = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hotelops.domain.model.Room> rooms = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final com.hotelops.domain.model.OrderStatus filterStatus = null;
    private final boolean showCreateDialog = false;
    
    public RoomServiceState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.RoomServiceOrder> orders, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.Room> rooms, boolean isLoading, @org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.OrderStatus filterStatus, boolean showCreateDialog) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hotelops.domain.model.RoomServiceOrder> getOrders() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hotelops.domain.model.Room> getRooms() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hotelops.domain.model.OrderStatus getFilterStatus() {
        return null;
    }
    
    public final boolean getShowCreateDialog() {
        return false;
    }
    
    public RoomServiceState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hotelops.domain.model.RoomServiceOrder> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hotelops.domain.model.Room> component2() {
        return null;
    }
    
    public final boolean component3() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hotelops.domain.model.OrderStatus component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.presentation.roomservice.RoomServiceState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.RoomServiceOrder> orders, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.Room> rooms, boolean isLoading, @org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.OrderStatus filterStatus, boolean showCreateDialog) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}