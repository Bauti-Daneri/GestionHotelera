package com.hotelops.domain.model

data class Hotel(
    val id: String,
    val name: String,
    val address: String,
    val city: String,
    val country: String,
    val phone: String,
    val email: String,
    val createdAt: Long
)
