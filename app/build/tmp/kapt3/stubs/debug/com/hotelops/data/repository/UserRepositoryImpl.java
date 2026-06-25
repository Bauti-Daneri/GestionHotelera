package com.hotelops.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hotelops.data.local.dao.UserDao;
import com.hotelops.data.local.entity.UserEntity;
import com.hotelops.domain.model.User;
import com.hotelops.domain.model.UserRole;
import com.hotelops.domain.repository.UserRepository;
import com.hotelops.domain.util.Resource;
import kotlinx.coroutines.flow.Flow;
import java.security.MessageDigest;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 $2\u00020\u0001:\u0001$B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJV\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0016\u001a\u00020\u000eH\u0016J\u001c\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u000b0\n2\u0006\u0010\u0019\u001a\u00020\u000eH\u0016J\u001c\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\u0019\u001a\u00020\u000eH\u0016J\"\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u001c0\u000b0\n2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000eH\u0002J\u0012\u0010\u001e\u001a\u00020\u000e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u000eH\u0002J\u001c\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010!\u001a\u00020\fH\u0016J\f\u0010\"\u001a\u00020#*\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/hotelops/data/repository/UserRepositoryImpl;", "Lcom/hotelops/domain/repository/UserRepository;", "userDao", "Lcom/hotelops/data/local/dao/UserDao;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/hotelops/data/local/dao/UserDao;Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/firestore/FirebaseFirestore;)V", "createUser", "Lkotlinx/coroutines/flow/Flow;", "Lcom/hotelops/domain/util/Resource;", "Lcom/hotelops/domain/model/User;", "hotelId", "", "name", "email", "password", "role", "Lcom/hotelops/domain/model/UserRole;", "department", "phone", "employeeId", "deleteUser", "", "userId", "getUserById", "getUsers", "", "hashPassword", "mapError", "message", "updateUser", "user", "toEntity", "Lcom/hotelops/data/local/entity/UserEntity;", "Companion", "app_debug"})
public final class UserRepositoryImpl implements com.hotelops.domain.repository.UserRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.data.local.dao.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COL_USERS = "users";
    @org.jetbrains.annotations.NotNull()
    public static final com.hotelops.data.repository.UserRepositoryImpl.Companion Companion = null;
    
    @javax.inject.Inject()
    public UserRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.dao.UserDao userDao, @org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth firebaseAuth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<java.util.List<com.hotelops.domain.model.User>>> getUsers(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.User>> getUserById(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.User>> createUser(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.UserRole role, @org.jetbrains.annotations.NotNull()
    java.lang.String department, @org.jetbrains.annotations.Nullable()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String employeeId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.User>> updateUser(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.User user) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<kotlin.Unit>> deleteUser(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    private final java.lang.String hashPassword(java.lang.String password) {
        return null;
    }
    
    private final java.lang.String mapError(java.lang.String message) {
        return null;
    }
    
    private final com.hotelops.data.local.entity.UserEntity toEntity(com.hotelops.domain.model.User $this$toEntity) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/hotelops/data/repository/UserRepositoryImpl$Companion;", "", "()V", "COL_USERS", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}