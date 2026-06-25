package com.hotelops.presentation.maintenance;

import androidx.lifecycle.ViewModel;
import android.net.Uri;
import com.hotelops.domain.model.*;
import com.hotelops.domain.usecase.maintenance.*;
import com.hotelops.domain.usecase.room.GetRoomsUseCase;
import com.hotelops.domain.util.Resource;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJF\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u0015J\u0006\u0010\u001f\u001a\u00020\u0013J\u0006\u0010 \u001a\u00020\u0013J\u000e\u0010!\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$J\u0010\u0010%\u001a\u00020\u00132\b\u0010&\u001a\u0004\u0018\u00010\'J\u0006\u0010(\u001a\u00020\u0013J\u0006\u0010)\u001a\u00020\u0013J\u0016\u0010*\u001a\u00020\u00132\u0006\u0010+\u001a\u00020\u00152\u0006\u0010,\u001a\u00020\'R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/hotelops/presentation/maintenance/MaintenanceViewModel;", "Landroidx/lifecycle/ViewModel;", "getTicketsUseCase", "Lcom/hotelops/domain/usecase/maintenance/GetTicketsUseCase;", "getRoomsUseCase", "Lcom/hotelops/domain/usecase/room/GetRoomsUseCase;", "createTicketUseCase", "Lcom/hotelops/domain/usecase/maintenance/CreateTicketUseCase;", "updateTicketStatusUseCase", "Lcom/hotelops/domain/usecase/maintenance/UpdateTicketStatusUseCase;", "(Lcom/hotelops/domain/usecase/maintenance/GetTicketsUseCase;Lcom/hotelops/domain/usecase/room/GetRoomsUseCase;Lcom/hotelops/domain/usecase/maintenance/CreateTicketUseCase;Lcom/hotelops/domain/usecase/maintenance/UpdateTicketStatusUseCase;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/hotelops/presentation/maintenance/MaintenanceState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "createTicket", "", "hotelId", "", "room", "Lcom/hotelops/domain/model/Room;", "title", "description", "category", "Lcom/hotelops/domain/model/TicketCategory;", "priority", "reportedByName", "reportedById", "hideCamera", "hideDialog", "loadData", "onImageCaptured", "uri", "Landroid/net/Uri;", "setFilter", "status", "Lcom/hotelops/domain/model/TicketStatus;", "showCamera", "showCreateDialog", "updateStatus", "ticketId", "newStatus", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class MaintenanceViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.maintenance.GetTicketsUseCase getTicketsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.room.GetRoomsUseCase getRoomsUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.maintenance.CreateTicketUseCase createTicketUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.maintenance.UpdateTicketStatusUseCase updateTicketStatusUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hotelops.presentation.maintenance.MaintenanceState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hotelops.presentation.maintenance.MaintenanceState> state = null;
    
    @javax.inject.Inject()
    public MaintenanceViewModel(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.maintenance.GetTicketsUseCase getTicketsUseCase, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.room.GetRoomsUseCase getRoomsUseCase, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.maintenance.CreateTicketUseCase createTicketUseCase, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.maintenance.UpdateTicketStatusUseCase updateTicketStatusUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hotelops.presentation.maintenance.MaintenanceState> getState() {
        return null;
    }
    
    public final void loadData(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId) {
    }
    
    public final void setFilter(@org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.TicketStatus status) {
    }
    
    public final void showCreateDialog() {
    }
    
    public final void hideDialog() {
    }
    
    public final void showCamera() {
    }
    
    public final void hideCamera() {
    }
    
    public final void onImageCaptured(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
    }
    
    public final void createTicket(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.Room room, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.TicketCategory category, @org.jetbrains.annotations.NotNull()
    java.lang.String priority, @org.jetbrains.annotations.NotNull()
    java.lang.String reportedByName, @org.jetbrains.annotations.NotNull()
    java.lang.String reportedById) {
    }
    
    public final void updateStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String ticketId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.TicketStatus newStatus) {
    }
}