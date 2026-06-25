package com.hotelops.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import com.hotelops.data.local.dao.MaintenanceDao;
import com.hotelops.data.local.entity.MaintenanceTicketEntity;
import com.hotelops.domain.model.MaintenanceTicket;
import com.hotelops.domain.model.TicketCategory;
import com.hotelops.domain.model.TicketStatus;
import com.hotelops.domain.repository.MaintenanceRepository;
import com.hotelops.domain.util.Resource;
import kotlinx.coroutines.flow.Flow;
import java.util.UUID;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006JV\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\t0\b2\u0006\u0010\u0017\u001a\u00020\fH\u0016J\u001c\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0017\u001a\u00020\fH\u0016J\"\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u001a0\t0\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016J.\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/hotelops/data/repository/MaintenanceRepositoryImpl;", "Lcom/hotelops/domain/repository/MaintenanceRepository;", "maintenanceDao", "Lcom/hotelops/data/local/dao/MaintenanceDao;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/hotelops/data/local/dao/MaintenanceDao;Lcom/google/firebase/firestore/FirebaseFirestore;)V", "createTicket", "Lkotlinx/coroutines/flow/Flow;", "Lcom/hotelops/domain/util/Resource;", "Lcom/hotelops/domain/model/MaintenanceTicket;", "hotelId", "", "roomId", "title", "description", "category", "Lcom/hotelops/domain/model/TicketCategory;", "priority", "reportedBy", "imageUrl", "deleteTicket", "", "ticketId", "getTicketById", "getTickets", "", "updateTicketStatus", "status", "Lcom/hotelops/domain/model/TicketStatus;", "assignedTo", "Companion", "app_debug"})
public final class MaintenanceRepositoryImpl implements com.hotelops.domain.repository.MaintenanceRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.hotelops.data.local.dao.MaintenanceDao maintenanceDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String COL_TICKETS = "maintenance_tickets";
    @org.jetbrains.annotations.NotNull()
    public static final com.hotelops.data.repository.MaintenanceRepositoryImpl.Companion Companion = null;
    
    @javax.inject.Inject()
    public MaintenanceRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.dao.MaintenanceDao maintenanceDao, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<java.util.List<com.hotelops.domain.model.MaintenanceTicket>>> getTickets(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.MaintenanceTicket>> getTicketById(@org.jetbrains.annotations.NotNull()
    java.lang.String ticketId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.MaintenanceTicket>> createTicket(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    java.lang.String roomId, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.TicketCategory category, @org.jetbrains.annotations.NotNull()
    java.lang.String priority, @org.jetbrains.annotations.NotNull()
    java.lang.String reportedBy, @org.jetbrains.annotations.Nullable()
    java.lang.String imageUrl) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<com.hotelops.domain.model.MaintenanceTicket>> updateTicketStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String ticketId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.TicketStatus status, @org.jetbrains.annotations.Nullable()
    java.lang.String assignedTo) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.hotelops.domain.util.Resource<kotlin.Unit>> deleteTicket(@org.jetbrains.annotations.NotNull()
    java.lang.String ticketId) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/hotelops/data/repository/MaintenanceRepositoryImpl$Companion;", "", "()V", "COL_TICKETS", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}