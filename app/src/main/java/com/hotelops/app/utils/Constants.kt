package com.hotelops.app.utils

/**
 * Constantes globales de la aplicación
 */
object Constants {
    // Base de datos
    const val DATABASE_NAME = "hotelops_database"
    
    // URLs y endpoints (placeholder - reemplazar con valores reales)
    const val SUPABASE_URL = "https://your-project.supabase.co"
    const val SUPABASE_KEY = "your-anon-key"
    
    // Timeouts
    const val API_TIMEOUT_SECONDS = 30
    const val SYNC_TIMEOUT_SECONDS = 60
    
    // Cache
    const val CACHE_EXPIRY_MINUTES = 30
    
    // Preferences
    const val PREFERENCES_NAME = "hotelops_preferences"
    const val PREF_USER_ID = "user_id"
    const val PREF_TENANT_ID = "tenant_id"
    const val PREF_AUTH_TOKEN = "auth_token"
    
    // Trabajo en segundo plano
    const val SYNC_WORK_NAME = "hotel_sync_work"
    const val BOOKING_SYNC_WORK_NAME = "booking_sync_work"
}

