package com.hotelops.data.local.dao

import androidx.room.*
import com.hotelops.data.local.entity.UserEntity
import com.hotelops.domain.model.UserRole
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users WHERE hotelId = :hotelId")
    fun getUsersByHotel(hotelId: String): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: String): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE email = :email AND hotelId = :hotelId")
    suspend fun getUserByEmail(email: String, hotelId: String): UserEntity?

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmailGlobal(email: String): UserEntity?

    @Query("SELECT * FROM users WHERE hotelId = :hotelId AND role = :role")
    fun getUsersByRole(hotelId: String, role: UserRole): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUserById(userId: String)

    @Query("SELECT * FROM users WHERE isDirty = 1")
    suspend fun getDirtyUsers(): List<UserEntity>

    @Query("UPDATE users SET syncedAt = :syncedAt, isDirty = 0 WHERE id = :userId")
    suspend fun markAsSynced(userId: String, syncedAt: Long)

    @Query("DELETE FROM users WHERE hotelId = :hotelId")
    suspend fun deleteUsersByHotel(hotelId: String)
}
