package com.hotelops.presentation.admin;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.text.font.FontWeight;
import com.hotelops.domain.model.Room;
import com.hotelops.domain.model.RoomStatus;
import com.hotelops.domain.model.User;
import com.hotelops.domain.model.UserRole;
import com.hotelops.presentation.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a6\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u001e\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a<\u0010\b\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032$\u0010\u0004\u001a \u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001a\u001c\u0010\u000b\u001a\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007\u001a>\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\r2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u001e\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a\u0018\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0007\u001a2\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u001aH\u0007\u001aZ\u0010\u001c\u001a\u00020\u00012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00170\u001e2\u0006\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\u001a2\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\"H\u0007\u001a,\u0010#\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\r2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001aT\u0010%\u001a\u00020\u00012\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\r0\u001e2\u0006\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u001a2\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u001aH\u0007\u00a8\u0006\'"}, d2 = {"AddRoomDialog", "", "onDismiss", "Lkotlin/Function0;", "onConfirm", "Lkotlin/Function3;", "", "", "AddUserDialog", "Lkotlin/Function4;", "Lcom/hotelops/domain/model/UserRole;", "AdminScreen", "currentUser", "Lcom/hotelops/domain/model/User;", "viewModel", "Lcom/hotelops/presentation/admin/AdminViewModel;", "EditUserDialog", "user", "HotelHeaderCard", "hotelName", "hotelId", "RoomCard", "room", "Lcom/hotelops/domain/model/Room;", "onDeleteClick", "onStatusChange", "Lkotlin/Function1;", "Lcom/hotelops/domain/model/RoomStatus;", "RoomsTab", "rooms", "", "isLoading", "", "onAddClick", "Lkotlin/Function2;", "UserCard", "onEditClick", "UsersTab", "users", "app_debug"})
public final class AdminScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void AdminScreen(@org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.User currentUser, @org.jetbrains.annotations.NotNull()
    com.hotelops.presentation.admin.AdminViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void HotelHeaderCard(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelName, @org.jetbrains.annotations.NotNull()
    java.lang.String hotelId) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void UsersTab(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.User> users, boolean isLoading, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.hotelops.domain.model.User, kotlin.Unit> onEditClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.hotelops.domain.model.User, kotlin.Unit> onDeleteClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void UserCard(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.User user, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onEditClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDeleteClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RoomsTab(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.Room> rooms, boolean isLoading, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.hotelops.domain.model.Room, kotlin.Unit> onDeleteClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super com.hotelops.domain.model.Room, ? super com.hotelops.domain.model.RoomStatus, kotlin.Unit> onStatusChange) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void RoomCard(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.Room room, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDeleteClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.hotelops.domain.model.RoomStatus, kotlin.Unit> onStatusChange) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void AddUserDialog(kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function4<? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super com.hotelops.domain.model.UserRole, kotlin.Unit> onConfirm) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void EditUserDialog(com.hotelops.domain.model.User user, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function3<? super java.lang.String, ? super java.lang.String, ? super com.hotelops.domain.model.UserRole, kotlin.Unit> onConfirm) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void AddRoomDialog(kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function3<? super java.lang.String, ? super java.lang.Integer, ? super java.lang.String, kotlin.Unit> onConfirm) {
    }
}