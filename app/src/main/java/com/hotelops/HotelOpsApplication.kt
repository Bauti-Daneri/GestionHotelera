package com.hotelops

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.work.Configuration
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.concurrent.TimeUnit

import androidx.hilt.work.HiltWorkerFactory
import com.hotelops.sync.SyncWorker
import javax.inject.Inject

@HiltAndroidApp
class HotelOpsApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()

        // Initialize Timber for logging
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Create notification channels
        createNotificationChannels()

        // Schedule background sync
        scheduleSyncWork()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channels = listOf(
                NotificationChannel(
                    CHANNEL_MAINTENANCE,
                    "Mantenimiento",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "Notificaciones de tickets de mantenimiento"
                },
                NotificationChannel(
                    CHANNEL_ROOM_SERVICE,
                    "Room Service",
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "Notificaciones de pedidos de room service"
                },
                NotificationChannel(
                    CHANNEL_HOUSEKEEPING,
                    "Limpieza",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "Notificaciones de limpieza de habitaciones"
                }
            )

            val notificationManager = getSystemService(NotificationManager::class.java)
            channels.forEach { notificationManager.createNotificationChannel(it) }
        }
    }

    private fun scheduleSyncWork() {
        val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(this).enqueue(syncRequest)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()

    companion object {
        const val CHANNEL_MAINTENANCE = "maintenance_channel"
        const val CHANNEL_ROOM_SERVICE = "room_service_channel"
        const val CHANNEL_HOUSEKEEPING = "housekeeping_channel"
    }
}
