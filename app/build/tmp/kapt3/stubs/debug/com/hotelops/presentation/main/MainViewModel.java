package com.hotelops.presentation.main;

import androidx.lifecycle.ViewModel;
import com.hotelops.domain.model.User;
import com.hotelops.domain.usecase.auth.GetCurrentUserUseCase;
import com.hotelops.domain.usecase.auth.LogoutUseCase;
import com.hotelops.domain.util.Resource;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\u000fH\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0011"}, d2 = {"Lcom/hotelops/presentation/main/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "getCurrentUserUseCase", "Lcom/hotelops/domain/usecase/auth/GetCurrentUserUseCase;", "logoutUseCase", "Lcom/hotelops/domain/usecase/auth/LogoutUseCase;", "(Lcom/hotelops/domain/usecase/auth/GetCurrentUserUseCase;Lcom/hotelops/domain/usecase/auth/LogoutUseCase;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/hotelops/presentation/main/MainState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "logout", "", "observeCurrentUser", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class MainViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.auth.GetCurrentUserUseCase getCurrentUserUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.auth.LogoutUseCase logoutUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hotelops.presentation.main.MainState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hotelops.presentation.main.MainState> state = null;
    
    @javax.inject.Inject()
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.auth.GetCurrentUserUseCase getCurrentUserUseCase, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.auth.LogoutUseCase logoutUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hotelops.presentation.main.MainState> getState() {
        return null;
    }
    
    private final void observeCurrentUser() {
    }
    
    public final void logout() {
    }
}