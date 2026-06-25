package com.hotelops.presentation.maintenance;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.window.DialogProperties;
import com.hotelops.domain.model.MaintenanceTicket;
import com.hotelops.domain.model.Room;
import com.hotelops.domain.model.TicketCategory;
import com.hotelops.domain.model.TicketStatus;
import com.hotelops.domain.model.User;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000d\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001ah\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\b2*\u0010\n\u001a&\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000bH\u0003\u001a\u001c\u0010\u000e\u001a\u00020\u00012\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007\u001a2\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001a$\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u001f2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00010!H\u0003\u001a\f\u0010#\u001a\u00020\f*\u00020\rH\u0002\u001a\f\u0010#\u001a\u00020\f*\u00020\"H\u0002\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006$"}, d2 = {"CreateTicketDialog", "", "rooms", "", "Lcom/hotelops/domain/model/Room;", "capturedImageUri", "Landroid/net/Uri;", "onOpenCamera", "Lkotlin/Function0;", "onDismiss", "onConfirm", "Lkotlin/Function5;", "", "Lcom/hotelops/domain/model/TicketCategory;", "MaintenanceScreen", "currentUser", "Lcom/hotelops/domain/model/User;", "viewModel", "Lcom/hotelops/presentation/maintenance/MaintenanceViewModel;", "StatCard", "label", "count", "", "containerColor", "Landroidx/compose/ui/graphics/Color;", "modifier", "Landroidx/compose/ui/Modifier;", "StatCard-9LQNqLg", "(Ljava/lang/String;IJLandroidx/compose/ui/Modifier;)V", "TicketCard", "ticket", "Lcom/hotelops/domain/model/MaintenanceTicket;", "onStatusChange", "Lkotlin/Function1;", "Lcom/hotelops/domain/model/TicketStatus;", "displayName", "app_debug"})
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
    private static final void CreateTicketDialog(java.util.List<com.hotelops.domain.model.Room> rooms, android.net.Uri capturedImageUri, kotlin.jvm.functions.Function0<kotlin.Unit> onOpenCamera, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function5<? super com.hotelops.domain.model.Room, ? super java.lang.String, ? super java.lang.String, ? super com.hotelops.domain.model.TicketCategory, ? super java.lang.String, kotlin.Unit> onConfirm) {
    }
    
    private static final java.lang.String displayName(com.hotelops.domain.model.TicketStatus $this$displayName) {
        return null;
    }
    
    private static final java.lang.String displayName(com.hotelops.domain.model.TicketCategory $this$displayName) {
        return null;
    }
}