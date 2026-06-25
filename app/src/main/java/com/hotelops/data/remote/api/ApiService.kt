package com.hotelops.data.remote.api

import com.hotelops.data.remote.dto.ExampleResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // Ejemplo: GET /items
    @GET("items")
    suspend fun getItems(): Response<List<ExampleResponseDto>>

    // Ejemplo: GET /items/{id}
    @GET("items/{id}")
    suspend fun getItemById(@Path("id") id: String): Response<ExampleResponseDto>
}
