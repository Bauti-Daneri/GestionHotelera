package com.hotelops.data.local.database

import androidx.room.TypeConverter
import com.hotelops.domain.model.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun fromUserRole(value: UserRole): String = value.name

    @TypeConverter
    fun toUserRole(value: String): UserRole = UserRole.valueOf(value)

    @TypeConverter
    fun fromRoomStatus(value: RoomStatus): String = value.name

    @TypeConverter
    fun toRoomStatus(value: String): RoomStatus = RoomStatus.valueOf(value)

    @TypeConverter
    fun fromRoomType(value: RoomType): String = value.name

    @TypeConverter
    fun toRoomType(value: String): RoomType = RoomType.valueOf(value)

    @TypeConverter
    fun fromTicketCategory(value: TicketCategory): String = value.name

    @TypeConverter
    fun toTicketCategory(value: String): TicketCategory = TicketCategory.valueOf(value)

    @TypeConverter
    fun fromTicketStatus(value: TicketStatus): String = value.name

    @TypeConverter
    fun toTicketStatus(value: String): TicketStatus = TicketStatus.valueOf(value)

    @TypeConverter
    fun fromOrderStatus(value: OrderStatus): String = value.name

    @TypeConverter
    fun toOrderStatus(value: String): OrderStatus = OrderStatus.valueOf(value)

    @TypeConverter
    fun fromOrderItemList(value: List<OrderItem>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toOrderItemList(value: String): List<OrderItem> {
        return Json.decodeFromString(value)
    }
}
