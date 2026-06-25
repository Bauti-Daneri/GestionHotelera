package com.hotelops.domain.repository;

import com.hotelops.domain.model.Hotel;
import com.hotelops.domain.model.User;
import com.hotelops.domain.util.Resource;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H&J$\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00060\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH&J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00060\u0003H&Jh\u0010\f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00040\r0\u00060\u00032\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\bH&\u00a8\u0006\u0018"}, d2 = {"Lcom/hotelops/domain/repository/AuthRepository;", "", "getCurrentUser", "Lkotlinx/coroutines/flow/Flow;", "Lcom/hotelops/domain/model/User;", "login", "Lcom/hotelops/domain/util/Resource;", "email", "", "password", "logout", "", "registerHotel", "Lkotlin/Pair;", "Lcom/hotelops/domain/model/Hotel;", "hotelName", "hotelAddress", "hotelCity", "hotelCountry", "hotelPhone", "hotelEmail", "adminName", "adminEmail", "adminPassword", "app_debug"})
public abstract interface AuthRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.User>> login(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<kotlin.Pair<com.hotelops.domain.model.Hotel, com.hotelops.domain.model.User>>> registerHotel(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelName, @org.jetbrains.annotations.NotNull()
    java.lang.String hotelAddress, @org.jetbrains.annotations.NotNull()
    java.lang.String hotelCity, @org.jetbrains.annotations.NotNull()
    java.lang.String hotelCountry, @org.jetbrains.annotations.NotNull()
    java.lang.String hotelPhone, @org.jetbrains.annotations.NotNull()
    java.lang.String hotelEmail, @org.jetbrains.annotations.NotNull()
    java.lang.String adminName, @org.jetbrains.annotations.NotNull()
    java.lang.String adminEmail, @org.jetbrains.annotations.NotNull()
    java.lang.String adminPassword);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<kotlin.Unit>> logout();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.hotelops.domain.model.User> getCurrentUser();
}