package com.hotelops.app.domain.repositories

import com.hotelops.app.domain.models.User
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz de repositorio para Usuario
 */
interface UserRepository {
    /**
     * Registra un nuevo usuario
     */
    suspend fun register(email: String, password: String, firstName: String, lastName: String): Result<User>

    /**
     * Inicia sesión con email y contraseña
     */
    suspend fun login(email: String, password: String): Result<User>

    /**
     * Obtiene el usuario actual autenticado
     */
    suspend fun getCurrentUser(): Result<User>

    /**
     * Observa cambios del usuario actual
     */
    fun observeCurrentUser(): Flow<User?>

    /**
     * Obtiene usuario por ID
     */
    suspend fun getUserById(userId: String): Result<User>

    /**
     * Actualiza perfil del usuario
     */
    suspend fun updateUser(user: User): Result<User>

    /**
     * Cierra sesión
     */
    suspend fun logout(): Result<Unit>

    /**
     * Verifica si hay sesión activa
     */
    fun isAuthenticated(): Flow<Boolean>

    /**
     * Envía email de recuperación de contraseña
     */
    suspend fun sendPasswordReset(email: String): Result<Unit>
}

