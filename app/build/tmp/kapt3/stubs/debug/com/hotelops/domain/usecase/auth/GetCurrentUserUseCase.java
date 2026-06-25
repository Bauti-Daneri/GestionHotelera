package com.hotelops.domain.usecase.auth;

import com.hotelops.domain.model.User;
import com.hotelops.domain.repository.AuthRepository;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/hotelops/domain/usecase/auth/GetCurrentUserUseCase;", "", "authRepository", "Lcom/hotelops/domain/repository/AuthRepository;", "(Lcom/hotelops/domain/repository/AuthRepository;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "Lcom/hotelops/domain/model/User;", "app_debug"})
public final class GetCurrentUserUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.repository.AuthRepository authRepository = null;
    
    @javax.inject.Inject()
    public GetCurrentUserUseCase(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.repository.AuthRepository authRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.hotelops.domain.model.User> invoke() {
        return null;
    }
}