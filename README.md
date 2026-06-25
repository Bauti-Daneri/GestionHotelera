# HotelOps Android

Sistema de gestión operativa hotelera multi-tenant — App nativa Kotlin + Jetpack Compose con **Material Design 3**.

---

## 🚀 Estado Actual: v2.5.0 — 100% Funcional y Conectado

La aplicación ha sido actualizada y optimizada para cumplir con los estándares de **Android 15 (API 35)** y utiliza un backend real con **Firebase**.

### Características Principales
- **Autenticación Real:** Login y Registro de hoteles conectados a Firebase Auth.
- **Base de Datos en la Nube:** Sincronización en tiempo real con Firebase Firestore.
- **Offline-First Avanzado:** La app funciona sin internet gracias a Room Database. Los cambios se guardan localmente y se suben solos mediante un `SyncWorker` (WorkManager) al recuperar la conexión.
- **Interfaz Moderna:** Migración completa a **Material Design 3** para una experiencia visual coherente y adaptable.
- **Notificaciones Push:** Integración con Firebase Cloud Messaging (FCM) para alertas operativas.
- **Arquitectura Robusta:** Implementación basada en **Clean Architecture** (Domain, Data, Presentation) con uso intensivo de **UseCases**.

---

## 🛠️ Stack Tecnológico

| Tecnología | Uso |
|------------|-----|
| **Kotlin 1.9.24** | Lenguaje de desarrollo principal |
| **Jetpack Compose** | UI declarativa con Material 3 |
| **Hilt** | Inyección de dependencias |
| **Room** | Caché local SQLite (Soporte offline) |
| **Firebase** | Auth, Firestore, Messaging, Analytics (BoM 34.15.0) |
| **WorkManager** | Sincronización de datos en segundo plano |
| **Coroutines + Flow** | Gestión de asincronía y flujos de datos |
| **Timber** | Sistema de logging profesional |

---

## 📦 Instalación y Configuración (Para Colaboradores)

Si acabas de descargar el proyecto, sigue estos pasos para que funcione:

### 1. Requisitos
- Android Studio **Ladybug** (2024.2.1) o superior.
- JDK 17 configurado en el proyecto.

### 2. Configurar Firebase (PASO CRÍTICO)
Por seguridad, el archivo de claves no se sube a Git.
1. Crea un proyecto en [Firebase Console](https://console.firebase.google.com).
2. Añade una app Android con el paquete: `com.hotelops`.
3. Descarga el archivo `google-services.json`.
4. Colócalo en la carpeta: `HotelOps/app/`.
5. En la consola de Firebase, activa **Email/Password Auth** y **Cloud Firestore**.

### 3. Sincronizar y Ejecutar
1. Abre el proyecto en Android Studio.
2. Espera a que **Gradle** termine la sincronización inicial.
3. Conecta un dispositivo físico o emulador (API 24+).
4. Pulsa **Run ▶**.

---

## 🏗️ Arquitectura del Proyecto

```
app/src/main/java/com/hotelops/
├── domain/         # Reglas de negocio (Modelos, Repositorios, UseCases)
├── data/           # Implementación de datos (Local con Room, Remote con Firestore)
├── presentation/   # Capa de UI (Screens, ViewModels, Theme M3)
├── sync/           # Lógica de sincronización en segundo plano (WorkManager)
├── di/             # Módulos de Inyección de Dependencias (Hilt)
└── HotelOpsApplication.kt # Configuración global (Canales de notificación, Sync)
```

---

## 🛡️ Compatibilidad
- **Android 15 Ready:** El proyecto soluciona la advertencia de alineación de **16 KB**, siendo compatible con dispositivos de próxima generación.
- **Mínimo:** API 24 (Android 7.0).
- **Target:** API 35 (Android 15).

---

> [!IMPORTANT]  
> Recuerda configurar las **Reglas de Seguridad** en Firestore para que la lectura/escritura solo esté permitida a usuarios autenticados (`request.auth != null`).
