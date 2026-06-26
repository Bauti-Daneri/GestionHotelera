package com.hotelops.app.data.local.dao

import androidx.room.*
import com.hotelops.app.data.local.entities.TenantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TenantDao {
    @Query("SELECT * FROM tenants WHERE id = :tenantId")
    suspend fun getTenantById(tenantId: String): TenantEntity?

    @Query("SELECT * FROM tenants WHERE id = :tenantId")
    fun observeTenant(tenantId: String): Flow<TenantEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTenant(tenant: TenantEntity)

    @Update
    suspend fun updateTenant(tenant: TenantEntity)

    @Delete
    suspend fun deleteTenant(tenant: TenantEntity)

    @Query("DELETE FROM tenants")
    suspend fun deleteAll()
}

