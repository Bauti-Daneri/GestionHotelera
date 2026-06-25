package com.hotelops.presentation.main;

import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.ImageVector;
import com.hotelops.domain.model.UserRole;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0005\u0012\u0013\u0014\u0015\u0016B-\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\u0002\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e\u0082\u0001\u0005\u0017\u0018\u0019\u001a\u001b\u00a8\u0006\u001c"}, d2 = {"Lcom/hotelops/presentation/main/BottomNavItem;", "", "route", "", "label", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "roles", "", "Lcom/hotelops/domain/model/UserRole;", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/util/List;)V", "getIcon", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "getLabel", "()Ljava/lang/String;", "getRoles", "()Ljava/util/List;", "getRoute", "Admin", "Housekeeping", "Maintenance", "Profile", "RoomService", "Lcom/hotelops/presentation/main/BottomNavItem$Admin;", "Lcom/hotelops/presentation/main/BottomNavItem$Housekeeping;", "Lcom/hotelops/presentation/main/BottomNavItem$Maintenance;", "Lcom/hotelops/presentation/main/BottomNavItem$Profile;", "Lcom/hotelops/presentation/main/BottomNavItem$RoomService;", "app_debug"})
abstract class BottomNavItem {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String route = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String label = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.ui.graphics.vector.ImageVector icon = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hotelops.domain.model.UserRole> roles = null;
    
    private BottomNavItem(java.lang.String route, java.lang.String label, androidx.compose.ui.graphics.vector.ImageVector icon, java.util.List<? extends com.hotelops.domain.model.UserRole> roles) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRoute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.ui.graphics.vector.ImageVector getIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hotelops.domain.model.UserRole> getRoles() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/hotelops/presentation/main/BottomNavItem$Admin;", "Lcom/hotelops/presentation/main/BottomNavItem;", "()V", "app_debug"})
    public static final class Admin extends com.hotelops.presentation.main.BottomNavItem {
        @org.jetbrains.annotations.NotNull()
        public static final com.hotelops.presentation.main.BottomNavItem.Admin INSTANCE = null;
        
        private Admin() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/hotelops/presentation/main/BottomNavItem$Housekeeping;", "Lcom/hotelops/presentation/main/BottomNavItem;", "()V", "app_debug"})
    public static final class Housekeeping extends com.hotelops.presentation.main.BottomNavItem {
        @org.jetbrains.annotations.NotNull()
        public static final com.hotelops.presentation.main.BottomNavItem.Housekeeping INSTANCE = null;
        
        private Housekeeping() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/hotelops/presentation/main/BottomNavItem$Maintenance;", "Lcom/hotelops/presentation/main/BottomNavItem;", "()V", "app_debug"})
    public static final class Maintenance extends com.hotelops.presentation.main.BottomNavItem {
        @org.jetbrains.annotations.NotNull()
        public static final com.hotelops.presentation.main.BottomNavItem.Maintenance INSTANCE = null;
        
        private Maintenance() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/hotelops/presentation/main/BottomNavItem$Profile;", "Lcom/hotelops/presentation/main/BottomNavItem;", "()V", "app_debug"})
    public static final class Profile extends com.hotelops.presentation.main.BottomNavItem {
        @org.jetbrains.annotations.NotNull()
        public static final com.hotelops.presentation.main.BottomNavItem.Profile INSTANCE = null;
        
        private Profile() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/hotelops/presentation/main/BottomNavItem$RoomService;", "Lcom/hotelops/presentation/main/BottomNavItem;", "()V", "app_debug"})
    public static final class RoomService extends com.hotelops.presentation.main.BottomNavItem {
        @org.jetbrains.annotations.NotNull()
        public static final com.hotelops.presentation.main.BottomNavItem.RoomService INSTANCE = null;
        
        private RoomService() {
        }
    }
}