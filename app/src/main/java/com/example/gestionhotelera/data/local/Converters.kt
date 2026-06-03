package com.example.gestionhotelera.data.local

import androidx.room.TypeConverter
import com.example.gestionhotelera.domain.model.*

class Converters {
    @TypeConverter
    fun fromUserRole(value: UserRole) = value.name

    @TypeConverter
    fun toUserRole(value: String) = UserRole.valueOf(value)

    @TypeConverter
    fun fromRoomStatus(value: RoomStatus) = value.name

    @TypeConverter
    fun toRoomStatus(value: String) = RoomStatus.valueOf(value)

    @TypeConverter
    fun fromTicketStatus(value: TicketStatus) = value.name

    @TypeConverter
    fun toTicketStatus(value: String) = TicketStatus.valueOf(value)

    @TypeConverter
    fun fromTicketCategory(value: TicketCategory) = value.name

    @TypeConverter
    fun toTicketCategory(value: String) = TicketCategory.valueOf(value)

    @TypeConverter
    fun fromOrderStatus(value: OrderStatus) = value.name

    @TypeConverter
    fun toOrderStatus(value: String) = OrderStatus.valueOf(value)

    @TypeConverter
    fun fromSyncStatus(value: SyncStatus) = value.name

    @TypeConverter
    fun toSyncStatus(value: String) = SyncStatus.valueOf(value)
}
