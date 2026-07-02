package com.hotelops.presentation.admin;

import androidx.lifecycle.ViewModel;
import com.hotelops.domain.model.*;
import com.hotelops.domain.usecase.maintenance.DeleteTicketUseCase;
import com.hotelops.domain.usecase.maintenance.GetTicketsUseCase;
import com.hotelops.domain.usecase.room.*;
import com.hotelops.domain.usecase.roomservice.DeleteOrderUseCase;
import com.hotelops.domain.usecase.roomservice.GetOrdersUseCase;
import com.hotelops.domain.usecase.user.*;
import com.hotelops.domain.util.Resource;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import java.security.MessageDigest;
import java.util.UUID;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001Bg\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u00a2\u0006\u0002\u0010\u001aJ\u001e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001f2\u0006\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001fJ&\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/J\u0006\u00100\u001a\u00020%J\u0006\u00101\u001a\u00020%J\u000e\u00102\u001a\u00020%2\u0006\u00103\u001a\u000204J\u000e\u00105\u001a\u00020%2\u0006\u00106\u001a\u000207J\u0006\u00108\u001a\u00020%J\u000e\u00109\u001a\u00020%2\u0006\u0010:\u001a\u00020\u001fJ\u0006\u0010;\u001a\u00020%J\u0006\u0010<\u001a\u00020%J\u000e\u0010=\u001a\u00020%2\u0006\u00106\u001a\u000207J\u0016\u0010>\u001a\u00020%2\u0006\u0010?\u001a\u00020\u001f2\u0006\u0010@\u001a\u00020AJ\u001e\u0010B\u001a\u00020%2\u0006\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001d0!\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006C"}, d2 = {"Lcom/hotelops/presentation/admin/AdminViewModel;", "Landroidx/lifecycle/ViewModel;", "getUsersUseCase", "Lcom/hotelops/domain/usecase/user/GetUsersUseCase;", "getRoomsUseCase", "Lcom/hotelops/domain/usecase/room/GetRoomsUseCase;", "getTicketsUseCase", "Lcom/hotelops/domain/usecase/maintenance/GetTicketsUseCase;", "getOrdersUseCase", "Lcom/hotelops/domain/usecase/roomservice/GetOrdersUseCase;", "createUserUseCase", "Lcom/hotelops/domain/usecase/user/CreateUserUseCase;", "deleteUserUseCase", "Lcom/hotelops/domain/usecase/user/DeleteUserUseCase;", "addRoomUseCase", "Lcom/hotelops/domain/usecase/room/AddRoomUseCase;", "deleteRoomUseCase", "Lcom/hotelops/domain/usecase/room/DeleteRoomUseCase;", "updateRoomStatusUseCase", "Lcom/hotelops/domain/usecase/room/UpdateRoomStatusUseCase;", "deleteTicketUseCase", "Lcom/hotelops/domain/usecase/maintenance/DeleteTicketUseCase;", "deleteOrderUseCase", "Lcom/hotelops/domain/usecase/roomservice/DeleteOrderUseCase;", "updateUserUseCase", "Lcom/hotelops/domain/usecase/user/UpdateUserUseCase;", "(Lcom/hotelops/domain/usecase/user/GetUsersUseCase;Lcom/hotelops/domain/usecase/room/GetRoomsUseCase;Lcom/hotelops/domain/usecase/maintenance/GetTicketsUseCase;Lcom/hotelops/domain/usecase/roomservice/GetOrdersUseCase;Lcom/hotelops/domain/usecase/user/CreateUserUseCase;Lcom/hotelops/domain/usecase/user/DeleteUserUseCase;Lcom/hotelops/domain/usecase/room/AddRoomUseCase;Lcom/hotelops/domain/usecase/room/DeleteRoomUseCase;Lcom/hotelops/domain/usecase/room/UpdateRoomStatusUseCase;Lcom/hotelops/domain/usecase/maintenance/DeleteTicketUseCase;Lcom/hotelops/domain/usecase/roomservice/DeleteOrderUseCase;Lcom/hotelops/domain/usecase/user/UpdateUserUseCase;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/hotelops/presentation/admin/AdminState;", "currentHotelId", "", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "addRoom", "", "roomNumber", "floor", "", "type", "addUser", "name", "email", "password", "role", "Lcom/hotelops/domain/model/UserRole;", "deleteCompletedTickets", "deleteDeliveredOrders", "deleteRoom", "room", "Lcom/hotelops/domain/model/Room;", "deleteUser", "user", "Lcom/hotelops/domain/model/User;", "hideDialogs", "loadData", "hotelId", "showAddRoomDialog", "showAddUserDialog", "showEditUserDialog", "updateRoomStatus", "roomId", "status", "Lcom/hotelops/domain/model/RoomStatus;", "updateUser", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AdminViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.user.GetUsersUseCase getUsersUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.room.GetRoomsUseCase getRoomsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.maintenance.GetTicketsUseCase getTicketsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.roomservice.GetOrdersUseCase getOrdersUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.user.CreateUserUseCase createUserUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.user.DeleteUserUseCase deleteUserUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.room.AddRoomUseCase addRoomUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.room.DeleteRoomUseCase deleteRoomUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.room.UpdateRoomStatusUseCase updateRoomStatusUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.maintenance.DeleteTicketUseCase deleteTicketUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.roomservice.DeleteOrderUseCase deleteOrderUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.user.UpdateUserUseCase updateUserUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hotelops.presentation.admin.AdminState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hotelops.presentation.admin.AdminState> state = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String currentHotelId;
    
    @javax.inject.Inject()
    public AdminViewModel(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.user.GetUsersUseCase getUsersUseCase, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.room.GetRoomsUseCase getRoomsUseCase, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.maintenance.GetTicketsUseCase getTicketsUseCase, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.roomservice.GetOrdersUseCase getOrdersUseCase, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.user.CreateUserUseCase createUserUseCase, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.user.DeleteUserUseCase deleteUserUseCase, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.room.AddRoomUseCase addRoomUseCase, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.room.DeleteRoomUseCase deleteRoomUseCase, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.room.UpdateRoomStatusUseCase updateRoomStatusUseCase, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.maintenance.DeleteTicketUseCase deleteTicketUseCase, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.roomservice.DeleteOrderUseCase deleteOrderUseCase, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.user.UpdateUserUseCase updateUserUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hotelops.presentation.admin.AdminState> getState() {
        return null;
    }
    
    public final void loadData(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId) {
    }
    
    public final void showAddUserDialog() {
    }
    
    public final void showAddRoomDialog() {
    }
    
    public final void showEditUserDialog(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.User user) {
    }
    
    public final void hideDialogs() {
    }
    
    public final void addUser(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.UserRole role) {
    }
    
    public final void updateUser(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.UserRole role) {
    }
    
    public final void deleteUser(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.User user) {
    }
    
    public final void addRoom(@org.jetbrains.annotations.NotNull()
    java.lang.String roomNumber, int floor, @org.jetbrains.annotations.NotNull()
    java.lang.String type) {
    }
    
    public final void deleteRoom(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.Room room) {
    }
    
    public final void updateRoomStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String roomId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.RoomStatus status) {
    }
    
    public final void deleteCompletedTickets() {
    }
    
    public final void deleteDeliveredOrders() {
    }
}