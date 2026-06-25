package com.hotelops.data.repository;

import android.content.SharedPreferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hotelops.data.local.dao.HotelDao;
import com.hotelops.data.local.dao.UserDao;
import com.hotelops.data.local.entity.HotelEntity;
import com.hotelops.data.local.entity.UserEntity;
import com.hotelops.domain.model.Hotel;
import com.hotelops.domain.model.User;
import com.hotelops.domain.model.UserRole;
import com.hotelops.domain.repository.AuthRepository;
import com.hotelops.domain.util.Resource;
import kotlinx.coroutines.flow.Flow;
import java.security.MessageDigest;
import java.util.UUID;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 *2\u00020\u0001:\u0001*B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J$\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00140\u000e2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016J\u0014\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00140\u000eH\u0016J\u0012\u0010\u0018\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u0011H\u0002Jh\u0010\u001a\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u000f0\u001b0\u00140\u000e2\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020\u0011H\u0016J\u0014\u0010&\u001a\u00020\'*\u00020(2\u0006\u0010)\u001a\u00020\u0011H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/hotelops/data/repository/AuthRepositoryImpl;", "Lcom/hotelops/domain/repository/AuthRepository;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "userDao", "Lcom/hotelops/data/local/dao/UserDao;", "hotelDao", "Lcom/hotelops/data/local/dao/HotelDao;", "prefs", "Landroid/content/SharedPreferences;", "(Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/hotelops/data/local/dao/UserDao;Lcom/hotelops/data/local/dao/HotelDao;Landroid/content/SharedPreferences;)V", "getCurrentUser", "Lkotlinx/coroutines/flow/Flow;", "Lcom/hotelops/domain/model/User;", "hashPassword", "", "password", "login", "Lcom/hotelops/domain/util/Resource;", "email", "logout", "", "mapFirebaseError", "message", "registerHotel", "Lkotlin/Pair;", "Lcom/hotelops/domain/model/Hotel;", "hotelName", "hotelAddress", "hotelCity", "hotelCountry", "hotelPhone", "hotelEmail", "adminName", "adminEmail", "adminPassword", "toUserEntity", "Lcom/hotelops/data/local/entity/UserEntity;", "Lcom/google/firebase/firestore/DocumentSnapshot;", "uid", "Companion", "app_debug"})
public final class AuthRepositoryImpl implements com.hotelops.domain.repository.AuthRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.data.local.dao.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.data.local.dao.HotelDao hotelDao = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_CURRENT_USER_ID = "current_user_id";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_CURRENT_HOTEL_ID = "current_hotel_id";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COL_HOTELS = "hotels";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COL_USERS = "users";
    @org.jetbrains.annotations.NotNull()
    public static final com.hotelops.data.repository.AuthRepositoryImpl.Companion Companion = null;
    
    @javax.inject.Inject()
    public AuthRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth firebaseAuth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore, @org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.dao.UserDao userDao, @org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.dao.HotelDao hotelDao, @org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences prefs) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.User>> login(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<kotlin.Pair<com.hotelops.domain.model.Hotel, com.hotelops.domain.model.User>>> registerHotel(@org.jetbrains.annotations.NotNull()
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
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<kotlin.Unit>> logout() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.model.User> getCurrentUser() {
        return null;
    }
    
    private final java.lang.String hashPassword(java.lang.String password) {
        return null;
    }
    
    private final java.lang.String mapFirebaseError(java.lang.String message) {
        return null;
    }
    
    private final com.hotelops.data.local.entity.UserEntity toUserEntity(com.google.firebase.firestore.DocumentSnapshot $this$toUserEntity, java.lang.String uid) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/hotelops/data/repository/AuthRepositoryImpl$Companion;", "", "()V", "COL_HOTELS", "", "COL_USERS", "KEY_CURRENT_HOTEL_ID", "KEY_CURRENT_USER_ID", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}