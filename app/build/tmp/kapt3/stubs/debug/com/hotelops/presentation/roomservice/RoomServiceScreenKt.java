package com.hotelops.presentation.roomservice;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import com.hotelops.domain.model.*;
import com.hotelops.presentation.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aP\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062*\u0010\u0007\u001a&\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\bH\u0003\u001a$\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u000fH\u0003\u001a\u001c\u0010\u0011\u001a\u00020\u00012\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007\u001a\f\u0010\u0016\u001a\u00020\t*\u00020\u0010H\u0002\u00a8\u0006\u0017"}, d2 = {"CreateOrderDialog", "", "rooms", "", "Lcom/hotelops/domain/model/Room;", "onDismiss", "Lkotlin/Function0;", "onConfirm", "Lkotlin/Function4;", "", "Lcom/hotelops/domain/model/OrderItem;", "OrderCard", "order", "Lcom/hotelops/domain/model/RoomServiceOrder;", "onStatusChange", "Lkotlin/Function1;", "Lcom/hotelops/domain/model/OrderStatus;", "RoomServiceScreen", "currentUser", "Lcom/hotelops/domain/model/User;", "viewModel", "Lcom/hotelops/presentation/roomservice/RoomServiceViewModel;", "displayName", "app_debug"})
public final class RoomServiceScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void RoomServiceScreen(@org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.User currentUser, @org.jetbrains.annotations.NotNull()
    com.hotelops.presentation.roomservice.RoomServiceViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void OrderCard(com.hotelops.domain.model.RoomServiceOrder order, kotlin.jvm.functions.Function1<? super com.hotelops.domain.model.OrderStatus, kotlin.Unit> onStatusChange) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void CreateOrderDialog(java.util.List<com.hotelops.domain.model.Room> rooms, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function4<? super com.hotelops.domain.model.Room, ? super java.lang.String, ? super java.util.List<com.hotelops.domain.model.OrderItem>, ? super java.lang.String, kotlin.Unit> onConfirm) {
    }
    
    private static final java.lang.String displayName(com.hotelops.domain.model.OrderStatus $this$displayName) {
        return null;
    }
}