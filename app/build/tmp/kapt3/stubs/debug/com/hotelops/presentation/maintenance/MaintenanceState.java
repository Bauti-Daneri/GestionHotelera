package com.hotelops.presentation.maintenance;

import android.net.Uri;
import androidx.lifecycle.ViewModel;
import com.hotelops.domain.model.*;
import com.hotelops.domain.usecase.maintenance.*;
import com.hotelops.domain.usecase.room.GetRoomsUseCase;
import com.hotelops.domain.util.Resource;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u007f\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\r\u001a\u00020\b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\b\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\u0002\u0010\u0014J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0013H\u00c6\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\t\u0010\'\u001a\u00020\bH\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\fH\u00c6\u0003J\t\u0010*\u001a\u00020\bH\u00c6\u0003J\t\u0010+\u001a\u00020\bH\u00c6\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0010H\u00c6\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0010H\u00c6\u0003J\u0083\u0001\u0010.\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u00c6\u0001J\u0013\u0010/\u001a\u00020\b2\b\u00100\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00101\u001a\u000202H\u00d6\u0001J\t\u00103\u001a\u00020\u0013H\u00d6\u0001R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u001dR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0011\u0010\u000e\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR\u0011\u0010\r\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001f\u00a8\u00064"}, d2 = {"Lcom/hotelops/presentation/maintenance/MaintenanceState;", "", "tickets", "", "Lcom/hotelops/domain/model/MaintenanceTicket;", "rooms", "Lcom/hotelops/domain/model/Room;", "isLoading", "", "filterStatus", "Lcom/hotelops/domain/model/TicketStatus;", "filterCategory", "Lcom/hotelops/domain/model/TicketCategory;", "showCreateDialog", "showCamera", "selectedImageUri", "Landroid/net/Uri;", "capturedImageUri", "error", "", "(Ljava/util/List;Ljava/util/List;ZLcom/hotelops/domain/model/TicketStatus;Lcom/hotelops/domain/model/TicketCategory;ZZLandroid/net/Uri;Landroid/net/Uri;Ljava/lang/String;)V", "getCapturedImageUri", "()Landroid/net/Uri;", "getError", "()Ljava/lang/String;", "getFilterCategory", "()Lcom/hotelops/domain/model/TicketCategory;", "getFilterStatus", "()Lcom/hotelops/domain/model/TicketStatus;", "()Z", "getRooms", "()Ljava/util/List;", "getSelectedImageUri", "getShowCamera", "getShowCreateDialog", "getTickets", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class MaintenanceState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hotelops.domain.model.MaintenanceTicket> tickets = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hotelops.domain.model.Room> rooms = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final com.hotelops.domain.model.TicketStatus filterStatus = null;
    @org.jetbrains.annotations.Nullable()
    private final com.hotelops.domain.model.TicketCategory filterCategory = null;
    private final boolean showCreateDialog = false;
    private final boolean showCamera = false;
    @org.jetbrains.annotations.Nullable()
    private final android.net.Uri selectedImageUri = null;
    @org.jetbrains.annotations.Nullable()
    private final android.net.Uri capturedImageUri = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    
    public MaintenanceState(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.MaintenanceTicket> tickets, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.Room> rooms, boolean isLoading, @org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.TicketStatus filterStatus, @org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.TicketCategory filterCategory, boolean showCreateDialog, boolean showCamera, @org.jetbrains.annotations.Nullable()
    android.net.Uri selectedImageUri, @org.jetbrains.annotations.Nullable()
    android.net.Uri capturedImageUri, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hotelops.domain.model.MaintenanceTicket> getTickets() {
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
    public final com.hotelops.domain.model.TicketStatus getFilterStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hotelops.domain.model.TicketCategory getFilterCategory() {
        return null;
    }
    
    public final boolean getShowCreateDialog() {
        return false;
    }
    
    public final boolean getShowCamera() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri getSelectedImageUri() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri getCapturedImageUri() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public MaintenanceState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hotelops.domain.model.MaintenanceTicket> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component10() {
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
    public final com.hotelops.domain.model.TicketStatus component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hotelops.domain.model.TicketCategory component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.presentation.maintenance.MaintenanceState copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.MaintenanceTicket> tickets, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.Room> rooms, boolean isLoading, @org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.TicketStatus filterStatus, @org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.TicketCategory filterCategory, boolean showCreateDialog, boolean showCamera, @org.jetbrains.annotations.Nullable()
    android.net.Uri selectedImageUri, @org.jetbrains.annotations.Nullable()
    android.net.Uri capturedImageUri, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
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