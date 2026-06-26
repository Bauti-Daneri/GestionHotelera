package com.hotelops.app.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Utilidades para formateo de fechas
 */
object DateFormatter {
    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    fun formatDate(dateTime: LocalDateTime): String =
        dateTime.format(dateFormatter)

    fun formatDateTime(dateTime: LocalDateTime): String =
        dateTime.format(dateTimeFormatter)

    fun formatTime(dateTime: LocalDateTime): String =
        dateTime.format(timeFormatter)

    fun parseDate(dateString: String): LocalDateTime? =
        try {
            LocalDateTime.parse("$dateString 00:00", dateTimeFormatter)
        } catch (e: Exception) {
            null
        }
}

