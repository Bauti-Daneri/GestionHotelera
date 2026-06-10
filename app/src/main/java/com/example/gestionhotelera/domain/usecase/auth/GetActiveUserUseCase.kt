package com.example.gestionhotelera.domain.usecase.auth

import com.example.gestionhotelera.domain.model.CURRENT_DEMO_ROLE
import com.example.gestionhotelera.domain.model.DemoRole
import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.model.UserRole
import com.example.gestionhotelera.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetActiveUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<User?> {
        return authRepository.currentUser.map { user ->
            user ?: getDemoUser()
        }
    }

    private fun getDemoUser(): User? {
        return when (CURRENT_DEMO_ROLE) {
            DemoRole.ADMIN -> User(
                id = "admin-01",
                hotelId = "HOTEL-DEMO-001",
                name = "Administrador",
                email = "admin@hotel.com",
                role = UserRole.ADMIN,
                createdAt = 0,
                updatedAt = 0
            )

            DemoRole.HOUSEKEEPING -> User(
                id = "house-01",
                hotelId = "HOTEL-DEMO-001",
                name = "María González",
                email = "limpieza@hotel.com",
                role = UserRole.HOUSEKEEPING,
                createdAt = 0,
                updatedAt = 0
            )

            DemoRole.MAINTENANCE -> User(
                id = "maint-01",
                hotelId = "HOTEL-DEMO-001",
                name = "Carlos Ruiz",
                email = "mantenimiento@hotel.com",
                role = UserRole.MAINTENANCE,
                createdAt = 0,
                updatedAt = 0
            )

            null -> null
        }
    }
}