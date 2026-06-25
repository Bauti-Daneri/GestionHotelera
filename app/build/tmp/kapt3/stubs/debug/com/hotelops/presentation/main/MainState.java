package com.hotelops.presentation.main;

import androidx.lifecycle.ViewModel;
import com.hotelops.domain.model.User;
import com.hotelops.domain.usecase.auth.GetCurrentUserUseCase;
import com.hotelops.domain.usecase.auth.LogoutUseCase;
import com.hotelops.domain.util.Resource;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0007J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0005H\u00c6\u0003J)\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/hotelops/presentation/main/MainState;", "", "currentUser", "Lcom/hotelops/domain/model/User;", "isLoggedOut", "", "isReady", "(Lcom/hotelops/domain/model/User;ZZ)V", "getCurrentUser", "()Lcom/hotelops/domain/model/User;", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "app_debug"})
public final class MainState {
    @org.jetbrains.annotations.Nullable()
    private final com.hotelops.domain.model.User currentUser = null;
    private final boolean isLoggedOut = false;
    private final boolean isReady = false;
    
    public MainState(@org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.User currentUser, boolean isLoggedOut, boolean isReady) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hotelops.domain.model.User getCurrentUser() {
        return null;
    }
    
    public final boolean isLoggedOut() {
        return false;
    }
    
    public final boolean isReady() {
        return false;
    }
    
    public MainState() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hotelops.domain.model.User component1() {
        return null;
    }
    
    public final boolean component2() {
        return false;
    }
    
    public final boolean component3() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.presentation.main.MainState copy(@org.jetbrains.annotations.Nullable()
    com.hotelops.domain.model.User currentUser, boolean isLoggedOut, boolean isReady) {
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