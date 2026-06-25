package com.hotelops.domain.usecase.user;

import com.hotelops.domain.model.User;
import com.hotelops.domain.repository.UserRepository;
import com.hotelops.domain.util.Resource;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J#\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00070\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/hotelops/domain/usecase/user/GetUsersUseCase;", "", "userRepository", "Lcom/hotelops/domain/repository/UserRepository;", "(Lcom/hotelops/domain/repository/UserRepository;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "Lcom/hotelops/domain/util/Resource;", "", "Lcom/hotelops/domain/model/User;", "hotelId", "", "app_debug"})
public final class GetUsersUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.repository.UserRepository userRepository = null;
    
    @javax.inject.Inject()
    public GetUsersUseCase(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.repository.UserRepository userRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<java.util.List<com.hotelops.domain.model.User>>> invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId) {
        return null;
    }
}