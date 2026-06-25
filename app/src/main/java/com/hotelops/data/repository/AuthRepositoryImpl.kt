package com.hotelops.data.repository

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hotelops.data.local.dao.HotelDao
import com.hotelops.data.local.dao.UserDao
import com.hotelops.data.local.entity.HotelEntity
import com.hotelops.data.local.entity.UserEntity
import com.hotelops.data.mapper.toDomain
import com.hotelops.domain.model.Hotel
import com.hotelops.domain.model.User
import com.hotelops.domain.model.UserRole
import com.hotelops.domain.repository.AuthRepository
import com.hotelops.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.security.MessageDigest
import java.util.UUID
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val userDao: UserDao,
    private val hotelDao: HotelDao,
    private val prefs: SharedPreferences
) : AuthRepository {

    companion object {
        private const val KEY_CURRENT_USER_ID = "current_user_id"
        private const val KEY_CURRENT_HOTEL_ID = "current_hotel_id"
        private const val COL_HOTELS = "hotels"
        private const val COL_USERS = "users"
    }

    override fun login(email: String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        try {
            // 1. Firebase Auth
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val firebaseUid = authResult.user?.uid
                ?: throw Exception("Error al obtener UID de Firebase")

            // 2. Obtener perfil de usuario desde Firestore
            val userDoc = firestore.collection(COL_USERS)
                .document(firebaseUid)
                .get().await()

            if (!userDoc.exists()) throw Exception("Perfil de usuario no encontrado")

            val userEntity = userDoc.toUserEntity(firebaseUid)

            // 3. Cachear en Room
            userDao.insertUser(userEntity)

            // 4. Persistir sesión
            prefs.edit()
                .putString(KEY_CURRENT_USER_ID, userEntity.id)
                .putString(KEY_CURRENT_HOTEL_ID, userEntity.hotelId)
                .apply()

            emit(Resource.Success(userEntity.toDomain()))
        } catch (e: Exception) {
            // Fallback offline: buscar en Room DB si no hay conexión
            try {
                val localUser = userDao.getUserByEmailGlobal(email)
                if (localUser != null && localUser.passwordHash == hashPassword(password)) {
                    prefs.edit()
                        .putString(KEY_CURRENT_USER_ID, localUser.id)
                        .putString(KEY_CURRENT_HOTEL_ID, localUser.hotelId)
                        .apply()
                    emit(Resource.Success(localUser.toDomain()))
                } else {
                    emit(Resource.Error(mapFirebaseError(e.message)))
                }
            } catch (localError: Exception) {
                emit(Resource.Error(mapFirebaseError(e.message)))
            }
        }
    }

    override fun registerHotel(
        hotelName: String,
        hotelAddress: String,
        hotelCity: String,
        hotelCountry: String,
        hotelPhone: String,
        hotelEmail: String,
        adminName: String,
        adminEmail: String,
        adminPassword: String
    ): Flow<Resource<Pair<Hotel, User>>> = flow {
        emit(Resource.Loading())
        try {
            val hotelId = UUID.randomUUID().toString()
            val now = System.currentTimeMillis()

            // 1. Crear usuario en Firebase Auth
            val authResult = firebaseAuth
                .createUserWithEmailAndPassword(adminEmail, adminPassword).await()
            val firebaseUid = authResult.user?.uid
                ?: throw Exception("Error al crear cuenta en Firebase")

            // 2. Guardar hotel en Firestore
            val hotelData = mapOf(
                "id" to hotelId,
                "name" to hotelName,
                "address" to hotelAddress,
                "city" to hotelCity,
                "country" to hotelCountry,
                "phone" to hotelPhone,
                "email" to hotelEmail,
                "createdAt" to now
            )
            firestore.collection(COL_HOTELS).document(hotelId).set(hotelData).await()

            // 3. Guardar perfil del admin en Firestore
            val adminData = mapOf(
                "id" to firebaseUid,
                "hotelId" to hotelId,
                "name" to adminName,
                "email" to adminEmail,
                "role" to UserRole.ADMIN.name,
                "department" to "Administración",
                "phone" to hotelPhone,
                "employeeId" to "ADMIN-001",
                "createdAt" to now
            )
            firestore.collection(COL_USERS).document(firebaseUid).set(adminData).await()

            // 4. Cachear localmente
            val hotelEntity = HotelEntity(hotelId, hotelName, hotelAddress, hotelCity,
                hotelCountry, hotelPhone, hotelEmail, now)
            val userEntity = UserEntity(
                id = firebaseUid,
                hotelId = hotelId,
                name = adminName,
                email = adminEmail,
                passwordHash = hashPassword(adminPassword),
                role = UserRole.ADMIN,
                department = "Administración",
                phone = hotelPhone,
                employeeId = "ADMIN-001",
                createdAt = now
            )
            hotelDao.insertHotel(hotelEntity)
            userDao.insertUser(userEntity)

            // 5. Persistir sesión
            prefs.edit()
                .putString(KEY_CURRENT_USER_ID, firebaseUid)
                .putString(KEY_CURRENT_HOTEL_ID, hotelId)
                .apply()

            emit(Resource.Success(Pair(hotelEntity.toDomain(), userEntity.toDomain())))
        } catch (e: Exception) {
            emit(Resource.Error(mapFirebaseError(e.message)))
        }
    }

    override fun logout(): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            firebaseAuth.signOut()
            prefs.edit()
                .remove(KEY_CURRENT_USER_ID)
                .remove(KEY_CURRENT_HOTEL_ID)
                .apply()
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error al cerrar sesión"))
        }
    }

    override fun getCurrentUser(): Flow<User?> = flow {
        // Verificar si Firebase Auth tiene sesión activa
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {
            prefs.edit().remove(KEY_CURRENT_USER_ID).remove(KEY_CURRENT_HOTEL_ID).apply()
            emit(null)
            return@flow
        }

        val userId = prefs.getString(KEY_CURRENT_USER_ID, firebaseUser.uid) ?: run {
            emit(null)
            return@flow
        }

        userDao.getUserById(userId).collect { entity ->
            if (entity != null) {
                emit(entity.toDomain())
            } else {
                // Intentar cargar desde Firestore si no está en caché
                try {
                    val doc = firestore.collection(COL_USERS).document(userId).get().await()
                    if (doc.exists()) {
                        val userEntity = doc.toUserEntity(userId)
                        userDao.insertUser(userEntity)
                        emit(userEntity.toDomain())
                    } else {
                        emit(null)
                    }
                } catch (e: Exception) {
                    emit(null)
                }
            }
        }
    }

    // --- helpers ---

    private fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        return digest.digest(password.toByteArray()).fold("") { s, b -> s + "%02x".format(b) }
    }

    private fun mapFirebaseError(message: String?): String = when {
        message == null -> "Error desconocido"
        message.contains("no user record") || message.contains("user-not-found") ->
            "Usuario no encontrado"
        message.contains("password is invalid") || message.contains("wrong-password") ->
            "Contraseña incorrecta"
        message.contains("email address is already in use") || message.contains("email-already-in-use") ->
            "Este email ya está registrado"
        message.contains("network") || message.contains("Unable to resolve host") ->
            "Sin conexión a internet"
        message.contains("too many requests") ->
            "Demasiados intentos. Espera unos minutos"
        else -> message
    }

    private fun com.google.firebase.firestore.DocumentSnapshot.toUserEntity(uid: String): UserEntity {
        return UserEntity(
            id = uid,
            hotelId = getString("hotelId") ?: "",
            name = getString("name") ?: "",
            email = getString("email") ?: "",
            passwordHash = "",
            role = runCatching { UserRole.valueOf(getString("role") ?: "") }
                .getOrDefault(UserRole.HOUSEKEEPING),
            department = getString("department") ?: "",
            phone = getString("phone"),
            employeeId = getString("employeeId") ?: "",
            createdAt = getLong("createdAt") ?: System.currentTimeMillis(),
            syncedAt = System.currentTimeMillis()
        )
    }
}
