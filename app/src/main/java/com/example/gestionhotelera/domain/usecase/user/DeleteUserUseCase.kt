package com.example.gestionhotelera.domain.usecase.user

import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.model.UserRole
import com.example.gestionhotelera.domain.repository.RoomRepository
import com.example.gestionhotelera.domain.repository.UserRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User): Result<Unit> {
        return try {
            repository.deleteUser(user)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Error al eliminar el usuario."))
        }
    }
}
