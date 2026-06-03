package com.example.gestionhotelera.data.repository

import com.example.gestionhotelera.data.local.UserDao
import com.example.gestionhotelera.data.mapper.toDomain
import com.example.gestionhotelera.data.mapper.toEntity
import com.example.gestionhotelera.domain.model.SyncStatus
import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override fun getUsers(): Flow<List<User>> = userDao.getAllUsers().map { entities ->
        entities.map { it.toDomain() }
    }

    override suspend fun getUserById(id: String): User? = userDao.getUserById(id)?.toDomain()

    override suspend fun saveUser(user: User) {
        userDao.insertUser(user.toEntity(isDirty = true).copy(syncStatus = SyncStatus.PENDING))
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user.toEntity())
    }
}
