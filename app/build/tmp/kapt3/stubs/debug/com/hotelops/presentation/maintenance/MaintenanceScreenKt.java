package com.hotelops.presentation.maintenance;

import android.net.Uri;
import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.window.DialogProperties;
import com.hotelops.domain.model.MaintenanceTicket;
import com.hotelops.domain.model.Room;
import com.hotelops.domain.model.TicketCategory;
import com.hotelops.domain.model.TicketStatus;
import com.hotelops.domain.model.User;
import com.hotelops.presentation.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000P\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001ah\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\b2*\u0010\n\u001a&\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000bH\u0003\u001a\u001c\u0010\u000e\u001a\u00020\u00012\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007\u001a$\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00010\u0017H\u0003\u001a\f\u0010\u0019\u001a\u00020\f*\u00020\rH\u0002\u001a\f\u0010\u0019\u001a\u00020\f*\u00020\u0018H\u0002\u00a8\u0006\u001a"}, d2 = {"CreateTicketDialog", "", "rooms", "", "Lcom/hotelops/domain/model/Room;", "capturedImageUri", "Landroid/net/Uri;", "onOpenCamera", "Lkotlin/Function0;", "onDismiss", "onConfirm", "Lkotlin/Function5;", "", "Lcom/hotelops/domain/model/TicketCategory;", "MaintenanceScreen", "currentUser", "Lcom/hotelops/domain/model/User;", "viewModel", "Lcom/hotelops/presentation/maintenance/MaintenanceViewModel;", "TicketCard", "ticket", "Lcom/hotelops/domain/model/MaintenanceTicket;", "onStatusChange", "Lkotlin/Function1;", "Lcom/hotelops/domain/model/TicketStatus;", "displayName", "app_debug"})
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