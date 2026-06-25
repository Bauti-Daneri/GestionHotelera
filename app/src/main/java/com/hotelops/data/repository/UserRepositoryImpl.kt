package com.hotelops.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hotelops.data.local.dao.UserDao
import com.hotelops.data.local.entity.UserEntity
import com.hotelops.data.mapper.toDomain
import com.hotelops.domain.model.User
import com.hotelops.domain.model.UserRole
import com.hotelops.domain.repository.UserRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.security.MessageDigest
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : UserRepository {

    companion object {
        private const val COL_USERS = "users"
    }

    override fun getUsers(hotelId: String): Flow<Resource<List<User>>> {
        return userDao.getUsersByHotel(hotelId).map { entities ->
            Resource.Success(entities.map { it.toDomain() })
        }
    }

    override fun getUserById(userId: String): Flow<Resource<User>> {
        return userDao.getUserById(userId).map { entity ->
            if (entity != null) Resource.Success(entity.toDomain())
            else Resource.Error("Usuario no encontrado")
        }
    }

    override fun createUser(
        hotelId: String,
        name: String,
        email: String,
        password: String,
        role: UserRole,
        department: String,
        phone: String?,
        employeeId: String
    ): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val now = System.currentTimeMillis()

            // 1. Crear cuenta en Firebase Auth
            val authResult = firebaseAuth
                .createUserWithEmailAndPassword(email, password).await()
            val uid = authResult.user?.uid ?: throw Exception("Error al crear cuenta Firebase")

            // 2. Guardar perfil en Firestore
            val data = mapOf(
                "id" to uid,
                "hotelId" to hotelId,
                "name" to name,
                "email" to email,
                "role" to role.name,
                "department" to department,
                "phone" to (phone ?: ""),
                "employeeId" to employeeId,
                "createdAt" to now
            )
            firestore.collection(COL_USERS).document(uid).set(data).await()

            // 3. Cachear en Room
            val entity = UserEntity(
                id = uid,
                hotelId = hotelId,
                name = name,
                email = email,
                passwordHash = hashPassword(password),
                role = role,
                department = department,
                phone = phone,
                employeeId = employeeId,
                createdAt = now,
                syncedAt = now
            )
            userDao.insertUser(entity)

            emit(Resource.Success(entity.toDomain()))
        } catch (e: Exception) {
            emit(Resource.Error(mapError(e.message)))
        }
    }

    override fun updateUser(user: User): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            val update = mapOf(
                "name" to user.name,
                "department" to user.department,
                "phone" to (user.phone ?: ""),
                "role" to user.role.name
            )
            firestore.collection(COL_USERS).document(user.id).update(update).await()
            userDao.updateUser(user.toEntity())
            emit(Resource.Success(user))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al actualizar usuario"))
        }
    }

    override fun deleteUser(userId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            firestore.collection(COL_USERS).document(userId).delete().await()
            userDao.deleteUserById(userId)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al eliminar usuario"))
        }
    }

    private fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        return digest.digest(password.toByteArray()).fold("") { s, b -> s + "%02x".format(b) }
    }

    private fun mapError(message: String?): String = when {
        message == null -> "Error desconocido"
        message.contains("email-already-in-use") -> "Este email ya está registrado"
        message.contains("network") -> "Sin conexión a internet"
        else -> message
    }

    private fun User.toEntity(): UserEntity = UserEntity(
        id = id,
        hotelId = hotelId,
        name = name,
        email = email,
        passwordHash = "",
        role = role,
        department = department,
        phone = phone,
        employeeId = employeeId,
        createdAt = createdAt,
        isDirty = true
    )
}
