package com.hotelops.domain.model

data class User(
    val id: String,
    val hotelId: String,
    val name: String,
    val email: String,
    val role: UserRole,
    val department: String,
    val phone: String?,
    val employeeId: String,
    val createdAt: Long
)
