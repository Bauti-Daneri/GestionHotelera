package com.hotelops.data.local.database;

import androidx.room.TypeConverter;
import com.hotelops.domain.model.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0007J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\rH\u0007J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000fH\u0007J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0011H\u0007J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0013H\u0007J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u0004H\u0007\u00a8\u0006\u001b"}, d2 = {"Lcom/hotelops/data/local/database/Converters;", "", "()V", "fromOrderItemList", "", "value", "", "Lcom/hotelops/domain/model/OrderItem;", "fromOrderStatus", "Lcom/hotelops/domain/model/OrderStatus;", "fromRoomStatus", "Lcom/hotelops/domain/model/RoomStatus;", "fromRoomType", "Lcom/hotelops/domain/model/RoomType;", "fromTicketCategory", "Lcom/hotelops/domain/model/TicketCategory;", "fromTicketStatus", "Lcom/hotelops/domain/model/TicketStatus;", "fromUserRole", "Lcom/hotelops/domain/model/UserRole;", "toOrderItemList", "toOrderStatus", "toRoomStatus", "toRoomType", "toTicketCategory", "toTicketStatus", "toUserRole", "app_debug"})
public final class Converters {
    
    public Converters() {
        super();
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromUserRole(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.UserRole value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.domain.model.UserRole toUserRole(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromRoomStatus(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.RoomStatus value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.domain.model.RoomStatus toRoomStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromRoomType(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.RoomType value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.domain.model.RoomType toRoomType(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromTicketCategory(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.TicketCategory value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.domain.model.TicketCategory toTicketCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromTicketStatus(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.TicketStatus value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.domain.model.TicketStatus toTicketStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromOrderStatus(@org.jetbrains.annotations.NotNull()
    com.hotelops.domain.model.OrderStatus value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.hotelops.domain.model.OrderStatus toOrderStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromOrderItemList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hotelops.domain.model.OrderItem> value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hotelops.domain.model.OrderItem> toOrderItemList(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
}