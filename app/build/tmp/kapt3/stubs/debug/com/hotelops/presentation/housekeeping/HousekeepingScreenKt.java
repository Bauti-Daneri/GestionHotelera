package com.hotelops.presentation.housekeeping;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import com.hotelops.domain.model.Room;
import com.hotelops.domain.model.RoomStatus;
import com.hotelops.domain.model.User;
import com.hotelops.presentation.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\u001c\u0010\u0007\u001a\u00020\u00012\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007\u001a2\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a\f\u0010\u0016\u001a\u00020\u0010*\u00020\u0006H\u0002\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0017"}, d2 = {"HousekeepingRoomCard", "", "room", "Lcom/hotelops/domain/model/Room;", "onStatusChange", "Lkotlin/Function1;", "Lcom/hotelops/domain/model/RoomStatus;", "HousekeepingScreen", "currentUser", "Lcom/hotelops/domain/model/User;", "viewModel", "Lcom/hotelops/presentation/housekeeping/HousekeepingViewModel;", "HousekeepingStatCard", "modifier", "Landroidx/compose/ui/Modifier;", "count", "", "label", "color", "Landroidx/compose/ui/graphics/Color;", "HousekeepingStatCard-g2O1Hgs", "(Landroidx/compose/ui/Modifier;Ljava/lang/String;Ljava/lang/String;J)V", "displayName", "app_debug"})
public final class HousekeepingScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void HousekeepingScreen(@org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.User currentUser, @org.jetbrains.annotations.NotNull()
    com.hotelops.presentation.housekeeping.HousekeepingViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void HousekeepingRoomCard(com.hotelops.domain.model.Room room, kotlin.jvm.functions.Function1<? super com.hotelops.domain.model.RoomStatus, kotlin.Unit> onStatusChange) {
    }
    
    private static final java.lang.String displayName(com.hotelops.domain.model.RoomStatus $this$displayName) {
        return null;
    }
}