package com.hotelops.app.data.local.dao

import androidx.room.*
import com.hotelops.app.data.local.entities.BookingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {
    @Query("SELECT * FROM bookings WHERE id = :bookingId")
    suspend fun getBookingById(bookingId: String): BookingEntity?

    @Query("SELECT * FROM bookings WHERE hotelId = :hotelId")
    suspend fun getBookingsByHotel(hotelId: String): List<BookingEntity>

    @Query("SELECT * FROM bookings WHERE userId = :userId")
    suspend fun getBookingsByUser(userId: String): List<BookingEntity>

    @Query("SELECT * FROM bookings WHERE hotelId = :hotelId")
    fun observeBookingsByHotel(hotelId: String): Flow<List<BookingEntity>>

    @Query("SELECT * FROM bookings WHERE hotelId = :hotelId AND checkInDate >= :startDate AND checkOutDate <= :endDate")
    suspend fun getBookingsByDateRange(hotelId: String, startDate: Long, endDate: Long): List<BookingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooking(booking: BookingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookings(bookings: List<BookingEntity>)

    @Update
    suspend fun updateBooking(booking: BookingEntity)

    @Delete
    suspend fun deleteBooking(booking: BookingEntity)

    @Query("DELETE FROM bookings WHERE hotelId = :hotelId")
    suspend fun deleteBookingsByHotel(hotelId: String)
}

