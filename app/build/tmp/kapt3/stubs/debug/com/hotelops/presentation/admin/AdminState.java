package com.hotelops.presentation.admin;

import com.hotelops.domain.model.Room;
import com.hotelops.domain.model.User;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\b\u00a2\u0006\u0002\u0010\rJ\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\bH\u00c6\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\t\u0010\u001a\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001b\u001a\u00020\bH\u00c6\u0003JS\u0010\u001c\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\nH\u00d6\u0001R\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0010R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\f\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012\u00a8\u0006\""}, d2 = {"Lcom/hotelops/presentation/admin/AdminState;", "", "users", "", "Lcom/hotelops/domain/model/User;", "rooms", "Lcom/hotelops/domain/model/Room;", "isLoading", "", "error", "", "showAddUserDialog", "showAddRoomDialog", "(Ljava/util/List;Ljava/util/List;ZLjava/lang/String;ZZ)V", "getError", "()Ljava/lang/String;", "()Z", "getRooms", "()Ljava/util/List;", "getShowAddRoomDialog", "getShowAddUserDialog", "getUsers", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class AdminState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hotelops.domain.model.User> users = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hotelops.domain.model.Room> rooms = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    private final boolean showAddUserDialog = false;
    private final boolean showAddRoomDialog = false;
    
    public AdminState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.User> users, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.Room> rooms, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean showAddUserDialog, boolean showAddRoomDialog) {
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
    
    public final boolean component3() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.presentation.admin.AdminState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.User> users, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.Room> rooms, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean showAddUserDialog, boolean showAddRoomDialog) {
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