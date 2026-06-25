package com.hotelops.presentation.profile;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.text.font.FontWeight;
import com.hotelops.domain.model.User;
import com.hotelops.presentation.theme.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001c\u0010\u0004\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005\u00a2\u0006\u0002\b\u0007\u00a2\u0006\u0002\b\bH\u0007\u001a \u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H\u0003\u001a \u0010\u000e\u001a\u00020\u00012\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0012H\u0007\u00a8\u0006\u0013"}, d2 = {"InfoSectionCard", "", "title", "", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "ProfileRow", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "value", "ProfileScreen", "currentUser", "Lcom/hotelops/domain/model/User;", "onLogout", "Lkotlin/Function0;", "app_debug"})
public final class ProfileScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ProfileScreen(@org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.User currentUser, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onLogout) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void InfoSectionCard(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super androidx.compose.foundation.layout.ColumnScope, kotlin.Unit> content) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ProfileRow(androidx.compose.ui.graphics.vector.ImageVector icon, java.lang.String label, java.lang.String value) {
    }
}