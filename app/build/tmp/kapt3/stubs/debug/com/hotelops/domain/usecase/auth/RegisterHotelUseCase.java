package com.hotelops.domain.usecase.auth;

import com.hotelops.domain.model.Hotel;
import com.hotelops.domain.model.User;
import com.hotelops.domain.repository.AuthRepository;
import com.hotelops.domain.util.Resource;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004Ji\u0010\u0005\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u00070\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/hotelops/domain/usecase/auth/RegisterHotelUseCase;", "", "authRepository", "Lcom/hotelops/domain/repository/AuthRepository;", "(Lcom/hotelops/domain/repository/AuthRepository;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "Lcom/hotelops/domain/util/Resource;", "Lkotlin/Pair;", "Lcom/hotelops/domain/model/Hotel;", "Lcom/hotelops/domain/model/User;", "hotelName", "", "hotelAddress", "hotelCity", "hotelCountry", "hotelPhone", "hotelEmail", "adminName", "adminEmail", "adminPassword", "app_debug"})
public final class RegisterHotelUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.domain.repository.AuthRepository authRepository = null;
    
    @javax.inject.Inject()
    public RegisterHotelUseCase(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.repository.AuthRepository authRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<kotlin.Pair<com.hotelops.domain.model.Hotel, com.hotelops.domain.model.User>>> invoke(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelName, @org.jetbrains.annotations.NotNull()
    java.lang.String hotelAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String hotelCity, @org.jetbrains.annotations.NotNull()
    java.lang.String hotelCountry, @org.jetbrains.annotations.NotNull()
    java.lang.String hotelPhone, @org.jetbrains.annotations.NotNull()
    java.lang.String hotelEmail, @org.jetbrains.annotations.NotNull()
    java.lang.String adminName, @org.jetbrains.annotations.NotNull()
    java.lang.String adminEmail, @org.jetbrains.annotations.NotNull()
    java.lang.String adminPassword) {
        return null;
    }
}