package com.hotelops.data.local.dao;

import androidx.room.*;
import com.hotelops.data.local.entity.MaintenanceTicketEntity;
import com.hotelops.domain.model.TicketCategory;
import com.hotelops.domain.model.TicketStatus;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00112\u0006\u0010\b\u001a\u00020\tH\'J$\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\u00112\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tH\'J$\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\u00112\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H\'J\u001c\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\u00112\u0006\u0010\f\u001a\u00020\tH\'J$\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\u00112\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\tH\'J$\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000e0\u00112\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001cH\'J\u0016\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u001e\u001a\u00020\u00032\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u00a7@\u00a2\u0006\u0002\u0010 J\u001e\u0010!\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\"\u001a\u00020#H\u00a7@\u00a2\u0006\u0002\u0010$J\u0016\u0010%\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J0\u0010&\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\'\u001a\u00020#2\b\u0010(\u001a\u0004\u0018\u00010#H\u00a7@\u00a2\u0006\u0002\u0010)\u00a8\u0006*"}, d2 = {"Lcom/hotelops/data/local/dao/MaintenanceDao;", "", "deleteTicket", "", "ticket", "Lcom/hotelops/data/local/entity/MaintenanceTicketEntity;", "(Lcom/hotelops/data/local/entity/MaintenanceTicketEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTicketById", "ticketId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTicketsByHotel", "hotelId", "getDirtyTickets", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTicketById", "Lkotlinx/coroutines/flow/Flow;", "getTicketsByAssignedUser", "userId", "getTicketsByCategory", "category", "Lcom/hotelops/domain/model/TicketCategory;", "getTicketsByHotel", "getTicketsByRoom", "roomId", "getTicketsByStatus", "status", "Lcom/hotelops/domain/model/TicketStatus;", "insertTicket", "insertTickets", "tickets", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markAsSynced", "syncedAt", "", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateTicket", "updateTicketStatus", "updatedAt", "completedAt", "(Ljava/lang/String;Lcom/hotelops/domain/model/TicketStatus;JLjava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface MaintenanceDao {
    
    @androidx.room.Query(value = "SELECT * FROM maintenance_tickets WHERE hotelId = :hotelId ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hotelops.data.local.entity.MaintenanceTicketEntity>> getTicketsByHotel(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId);
    
    @androidx.room.Query(value = "SELECT * FROM maintenance_tickets WHERE id = :ticketId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.hotelops.data.local.entity.MaintenanceTicketEntity> getTicketById(@org.jetbrains.annotations.NotNull()
    java.lang.String ticketId);
    
    @androidx.room.Query(value = "SELECT * FROM maintenance_tickets WHERE hotelId = :hotelId AND status = :status ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hotelops.data.local.entity.MaintenanceTicketEntity>> getTicketsByStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.TicketStatus status);
    
    @androidx.room.Query(value = "SELECT * FROM maintenance_tickets WHERE hotelId = :hotelId AND category = :category ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hotelops.data.local.entity.MaintenanceTicketEntity>> getTicketsByCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.TicketCategory category);
    
    @androidx.room.Query(value = "SELECT * FROM maintenance_tickets WHERE hotelId = :hotelId AND assignedTo = :userId ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hotelops.data.local.entity.MaintenanceTicketEntity>> getTicketsByAssignedUser(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    java.lang.String userId);
    
    @androidx.room.Query(value = "SELECT * FROM maintenance_tickets WHERE hotelId = :hotelId AND roomId = :roomId ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hotelops.data.local.entity.MaintenanceTicketEntity>> getTicketsByRoom(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    java.lang.String roomId);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTicket(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.entity.MaintenanceTicketEntity ticket, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTickets(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.data.local.entity.MaintenanceTicketEntity> tickets, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTicket(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.entity.MaintenanceTicketEntity ticket, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTicket(@org.jetbrains.annotations.NotNull()
    com.hotelops.data.local.entity.MaintenanceTicketEntity ticket, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM maintenance_tickets WHERE id = :ticketId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTicketById(@org.jetbrains.annotations.NotNull()
    java.lang.String ticketId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM maintenance_tickets WHERE isDirty = 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDirtyTickets(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hotelops.data.local.entity.MaintenanceTicketEntity>> $completion);
    
    @androidx.room.Query(value = "UPDATE maintenance_tickets SET syncedAt = :syncedAt, isDirty = 0 WHERE id = :ticketId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markAsSynced(@org.jetbrains.annotations.NotNull()
    java.lang.String ticketId, long syncedAt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE maintenance_tickets SET status = :status, updatedAt = :updatedAt, completedAt = :completedAt, isDirty = 1 WHERE id = :ticketId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTicketStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String ticketId, @org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.TicketStatus status, long updatedAt, @org.jetbrains.annotations.Nullable()
    java.lang.Long completedAt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM maintenance_tickets WHERE hotelId = :hotelId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTicketsByHotel(@org.jetbrains.annotations.NotNull()
    java.lang.String hotelId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}