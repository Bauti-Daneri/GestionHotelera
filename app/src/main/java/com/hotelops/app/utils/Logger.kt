package com.hotelops.app.utils

import android.util.Log

/**
 * Logger centralizado para la aplicación
 * Facilita cambiar a bibliotecas de logging en el futuro
 */
object Logger {
    private const val TAG = "HotelOps"

    fun d(tag: String = TAG, message: String, throwable: Throwable? = null) {
        Log.d(tag, message, throwable)
    }

    fun i(tag: String = TAG, message: String) {
        Log.i(tag, message)
    }

    fun w(tag: String = TAG, message: String, throwable: Throwable? = null) {
        Log.w(tag, message, throwable)
    }

    fun e(tag: String = TAG, message: String, throwable: Throwable? = null) {
        Log.e(tag, message, throwable)
    }
}

