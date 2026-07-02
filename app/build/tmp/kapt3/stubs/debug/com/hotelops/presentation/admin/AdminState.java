package com.hotelops.presentation.admin;

import com.hotelops.domain.model.MaintenanceTicket;
import com.hotelops.domain.model.Room;
import com.hotelops.domain.model.RoomServiceOrder;
import com.hotelops.domain.model.User;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001d\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B{\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\u0012J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H\u00c6\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\n0\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\fH\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003J\t\u0010%\u001a\u00020\fH\u00c6\u0003J\t\u0010&\u001a\u00020\fH\u00c6\u0003J\u000b\u0010\'\u001a\u0004\u0018\u00010\u0004H\u00c6\u0003J\u007f\u0010(\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u00c6\u0001J\u0013\u0010)\u001a\u00020\f2\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010+\u001a\u00020,H\u00d6\u0001J\t\u0010-\u001a\u00020\u000eH\u00d6\u0001R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0017R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0010\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\u000f\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019\u00a8\u0006."}, d2 = {"Lcom/hotelops/presentation/admin/AdminState;", "", "users", "", "Lcom/hotelops/domain/model/User;", "rooms", "Lcom/hotelops/domain/model/Room;", "tickets", "Lcom/hotelops/domain/model/MaintenanceTicket;", "orders", "Lcom/hotelops/domain/model/RoomServiceOrder;", "isLoading", "", "error", "", "showAddUserDialog", "showAddRoomDialog", "editingUser", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLjava/lang/String;ZZLcom/hotelops/domain/model/User;)V", "getEditingUser", "()Lcom/hotelops/domain/model/User;", "getError", "()Ljava/lang/String;", "()Z", "getOrders", "()Ljava/util/List;", "getRooms", "getShowAddRoomDialog", "getShowAddUserDialog", "getTickets", "getUsers", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class AdminState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hotelops.domain.model.User> users = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hotelops.domain.model.Room> rooms = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hotelops.domain.model.MaintenanceTicket> tickets = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hotelops.domain.model.RoomServiceOrder> orders = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    private final boolean showAddUserDialog = false;
    private final boolean showAddRoomDialog = false;
    @org.jetbrains.annotations.Nullable()
    private final com.hotelops.domain.model.User editingUser = null;
    
    public AdminState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.User> users, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.Room> rooms, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.MaintenanceTicket> tickets, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.RoomServiceOrder> orders, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean showAddUserDialog, boolean showAddRoomDialog, @org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.User editingUser) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hotelops.domain.model.User> getUsers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hotelops.domain.model.Room> getRooms() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hotelops.domain.model.MaintenanceTicket> getTickets() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hotelops.domain.model.RoomServiceOrder> getOrders() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public final boolean getShowAddUserDialog() {
        return false;
    }
    
    public final boolean getShowAddRoomDialog() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hotelops.domain.model.User getEditingUser() {
        return null;
    }
    
    public AdminState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hotelops.domain.model.User> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hotelops.domain.model.Room> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hotelops.domain.model.MaintenanceTicket> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hotelops.domain.model.RoomServiceOrder> component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    public final boolean component8() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hotelops.domain.model.User component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.presentation.admin.AdminState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.User> users, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.Room> rooms, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.MaintenanceTicket> tickets, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.RoomServiceOrder> orders, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean showAddUserDialog, boolean showAddRoomDialog, @org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.User editingUser) {
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