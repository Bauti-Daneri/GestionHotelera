package com.hotelops.app.domain.repositories

import com.hotelops.app.domain.models.Tenant
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz de repositorio para Tenant
 * Define los contratos sin conocimiento de implementación (Room, Supabase, etc.)
 */
interface TenantRepository {
    /**
     * Obtiene el tenant actual del usuario
     */
    suspend fun getCurrentTenant(): Result<Tenant>

    /**
     * Obtiene un tenant por ID
     */
    suspend fun getTenantById(tenantId: String): Result<Tenant>

    /**
     * Observa cambios en el tenant actual en tiempo real
     */
    fun observeCurrentTenant(): Flow<Tenant?>

    /**
     * Actualiza la información del tenant
     */
    suspend fun updateTenant(tenant: Tenant): Result<Tenant>

    /**
     * Sincroniza datos del tenant con el backend
     */
    suspend fun syncTenantData(): Result<Unit>

    /**
     * Limpia datos locales del tenant (logout)
     */
    suspend fun clearTenantData(): Result<Unit>
}

