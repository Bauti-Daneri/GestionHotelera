package com.hotelops.presentation.register;

import androidx.lifecycle.ViewModel;
import com.hotelops.domain.usecase.auth.RegisterHotelUseCase;
import com.hotelops.domain.util.Resource;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0010\u001a\u00020\rH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0011"}, d2 = {"Lcom/hotelops/presentation/register/RegisterHotelViewModel;", "Landroidx/lifecycle/ViewModel;", "registerHotelUseCase", "Lcom/hotelops/domain/usecase/auth/RegisterHotelUseCase;", "(Lcom/hotelops/domain/usecase/auth/RegisterHotelUseCase;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/hotelops/presentation/register/RegisterHotelState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "onEvent", "", "event", "Lcom/hotelops/presentation/register/RegisterHotelEvent;", "register", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class RegisterHotelViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.usecase.auth.RegisterHotelUseCase registerHotelUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hotelops.presentation.register.RegisterHotelState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hotelops.presentation.register.RegisterHotelState> state = null;
    
    @javax.inject.Inject()
    public RegisterHotelViewModel(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.usecase.auth.RegisterHotelUseCase registerHotelUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hotelops.presentation.register.RegisterHotelState> getState() {
        return null;
    }
    
    public final void onEvent(@org.jetbrains.annotations.NotNull()
    com.hotelops.presentation.register.RegisterHotelEvent event) {
    }
    
    private final void register() {
    }
}