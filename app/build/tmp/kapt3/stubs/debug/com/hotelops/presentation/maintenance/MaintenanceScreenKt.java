package com.hotelops.presentation.maintenance;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import com.hotelops.domain.model.MaintenanceTicket;
import com.hotelops.domain.model.Room;
import com.hotelops.domain.model.TicketCategory;
import com.hotelops.domain.model.TicketStatus;
import com.hotelops.domain.model.User;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aP\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062*\u0010\u0007\u001a&\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\bH\u0003\u001a\u001c\u0010\u000b\u001a\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007\u001a2\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019\u001a$\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u001c2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u001eH\u0003\u001a\f\u0010 \u001a\u00020\t*\u00020\nH\u0002\u001a\f\u0010 \u001a\u00020\t*\u00020\u001fH\u0002\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006!"}, d2 = {"CreateTicketDialog", "", "rooms", "", "Lcom/hotelops/domain/model/Room;", "onDismiss", "Lkotlin/Function0;", "onConfirm", "Lkotlin/Function5;", "", "Lcom/hotelops/domain/model/TicketCategory;", "MaintenanceScreen", "currentUser", "Lcom/hotelops/domain/model/User;", "viewModel", "Lcom/hotelops/presentation/maintenance/MaintenanceViewModel;", "StatCard", "label", "count", "", "containerColor", "Landroidx/compose/ui/graphics/Color;", "modifier", "Landroidx/compose/ui/Modifier;", "StatCard-9LQNqLg", "(Ljava/lang/String;IJLandroidx/compose/ui/Modifier;)V", "TicketCard", "ticket", "Lcom/hotelops/domain/model/MaintenanceTicket;", "onStatusChange", "Lkotlin/Function1;", "Lcom/hotelops/domain/model/TicketStatus;", "displayName", "app_debug"})
public final class MaintenanceScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void MaintenanceScreen(@org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.User currentUser, @org.jetbrains.annotations.NotNull()
    com.hotelops.presentation.maintenance.MaintenanceViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TicketCard(com.hotelops.domain.model.MaintenanceTicket ticket, kotlin.jvm.functions.Function1<? super com.hotelops.domain.model.TicketStatus, kotlin.Unit> onStatusChange) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void CreateTicketDialog(java.util.List<com.hotelops.domain.model.Room> rooms, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function5<? super com.hotelops.domain.model.Room, ? super java.lang.String, ? super java.lang.String, ? super com.hotelops.domain.model.TicketCategory, ? super java.lang.String, kotlin.Unit> onConfirm) {
    }
    
    private static final java.lang.String displayName(com.hotelops.domain.model.TicketStatus $this$displayName) {
        return null;
    }
    
    private static final java.lang.String displayName(com.hotelops.domain.model.TicketCategory $this$displayName) {
        return null;
    }
}