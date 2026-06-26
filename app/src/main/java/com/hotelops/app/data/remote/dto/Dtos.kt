package com.hotelops.app.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * DTOs para Supabase - Representan datos recibidos de la API remota
 * Se convierten a Domain Models mediante Mappers
 */

@Serializable
data class HotelDto(
    val id: String,
    @SerialName("tenant_id")
    val tenantId: String,
    val name: String,
    val description: String? = null,
    val address: String,
    val city: String,
    val country: String,
    @SerialName("phone_number")
    val phoneNumber: String? = null,
    val email: String? = null,
    val website: String? = null,
    val rating: Double = 0.0,
    @SerialName("total_rooms")
    val totalRooms: Int = 0,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("is_active")
    val isActive: Boolean = true
)

@Serializable
data class RoomDto(
    val id: String,
    @SerialName("hotel_id")
    val hotelId: String,
    @SerialName("room_number")
    val roomNumber: String,
    @SerialName("room_type")
    val roomType: String,
    val capacity: Int,
    @SerialName("price_per_night")
    val pricePerNight: Double,
    val description: String? = null,
    val amenities: List<String> = emptyList(),
    @SerialName("is_available")
    val isAvailable: Boolean = true,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
)

@Serializable
data class BookingDto(
    val id: String,
    @SerialName("hotel_id")
    val hotelId: String,
    @SerialName("room_id")
    val roomId: String,
    @SerialName("user_id")
    val userId: String,
    @SerialName("tenant_id")
    val tenantId: String,
    @SerialName("check_in_date")
    val checkInDate: String,
    @SerialName("check_out_date")
    val checkOutDate: String,
    @SerialName("guest_name")
    val guestName: String,
    @SerialName("guest_email")
    val guestEmail: String,
    @SerialName("guest_phone")
    val guestPhone: String,
    @SerialName("total_price")
    val totalPrice: Double,
    val status: String,
    @SerialName("special_requests")
    val specialRequests: String? = null,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
)

@Serializable
data class TenantDto(
    val id: String,
    val name: String,
    val email: String,
    val phone: String? = null,
    val address: String? = null,
    val city: String? = null,
    val country: String? = null,
    val logo: String? = null,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("is_active")
    val isActive: Boolean = true
)

@Serializable
data class UserDto(
    val id: String,
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val role: String,
    @SerialName("tenant_id")
    val tenantId: String,
    @SerialName("photo_url")
    val photoUrl: String? = null,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("is_active")
    val isActive: Boolean = true
)

