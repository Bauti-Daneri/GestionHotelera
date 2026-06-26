package com.hotelops.app.data.repositories

import com.hotelops.app.data.local.dao.HotelDao
import com.hotelops.app.data.mappers.HotelMapper
import com.hotelops.app.domain.models.Hotel
import com.hotelops.app.domain.repositories.HotelRepository
import com.hotelops.app.domain.exceptions.HotelNotFoundException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * EJEMPLO DE IMPLEMENTACIÓN DE REPOSITORIO
 *
 * Este archivo muestra cómo implementar un repositorio siguiendo Clean Architecture.
 * Orquesta entre Data Local (Room) y Data Remota (Supabase).
 *
 * NOTA: RemoteDataSource aún no está implementado. Cuando esté disponible,
 * descomentar las líneas marcadas con "// REMOTO:"
 */
class HotelRepositoryImpl @Inject constructor(
    private val hotelDao: HotelDao,
    // private val remoteDataSource: RemoteDataSource,  // REMOTO: Agregar cuando esté disponible
) : HotelRepository {

    override suspend fun getHotelsByTenant(tenantId: String): Result<List<Hotel>> {
        return try {
            // REMOTO: Opcional - sincronizar desde API primero
            // val remoteHotels = remoteDataSource.getHotelsByTenant(tenantId)
            // remoteHotels.forEach { hotelDao.insertHotel(HotelMapper.dtoToEntity(it)) }

            val localHotels = hotelDao.getHotelsByTenant(tenantId)
            val domainHotels = localHotels.map { HotelMapper.entityToDomain(it) }
            Result.success(domainHotels)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getHotelById(hotelId: String): Result<Hotel> {
        return try {
            val entity = hotelDao.getHotelById(hotelId)
                ?: throw HotelNotFoundException("Hotel con ID $hotelId no encontrado")
            
            val domain = HotelMapper.entityToDomain(entity)
            Result.success(domain)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun observeHotels(tenantId: String): Flow<List<Hotel>> {
        return hotelDao.observeHotelsByTenant(tenantId)
            .map { entities -> entities.map { HotelMapper.entityToDomain(it) } }
    }

    override suspend fun createHotel(hotel: Hotel): Result<Hotel> {
        return try {
            // REMOTO: Crear en API primero
            // val remoteHotel = remoteDataSource.createHotel(hotel)
            // val entity = HotelMapper.dtoToEntity(remoteHotel)

            val entity = HotelMapper.domainToEntity(hotel)
            hotelDao.insertHotel(entity)
            Result.success(hotel)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateHotel(hotel: Hotel): Result<Hotel> {
        return try {
            // REMOTO: Actualizar en API primero
            // val remoteHotel = remoteDataSource.updateHotel(hotel)
            // val entity = HotelMapper.dtoToEntity(remoteHotel)

            val entity = HotelMapper.domainToEntity(hotel)
            hotelDao.updateHotel(entity)
            Result.success(hotel)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteHotel(hotelId: String): Result<Unit> {
        return try {
            // REMOTO: Eliminar en API primero
            // remoteDataSource.deleteHotel(hotelId)

            val hotel = hotelDao.getHotelById(hotelId)
                ?: throw HotelNotFoundException("Hotel no encontrado")
            
            hotelDao.deleteHotel(hotel)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun syncHotels(tenantId: String): Result<Unit> {
        return try {
            // REMOTO: Descomentar cuando RemoteDataSource esté disponible
            // val remoteHotels = remoteDataSource.getHotelsByTenant(tenantId)
            // remoteHotels.forEach { dto ->
            //     hotelDao.insertHotel(HotelMapper.dtoToEntity(dto))
            // }
            
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

