# Guía de Arquitectura — HotelOps

Este documento resume la estructura técnica del proyecto. La aplicación está construida siguiendo los principios de **Clean Architecture** y los estándares modernos de desarrollo Android (MVVM + Jetpack Compose).

---

## 1. Capas del Proyecto

El código está dividido en tres capas principales para separar la lógica de negocio de la interfaz de usuario y los datos.

### 📁 `domain/` (El Corazón)
Es la capa más interna. No depende de ninguna librería externa (salvo corrutinas y la lógica básica de Kotlin).
- **`model/`**: Clases de datos puras que representan el negocio (`Room`, `User`, `MaintenanceTicket`, `RoomServiceOrder`).
- **`repository/`**: Interfaces que definen qué datos necesita la app, sin decir de dónde vienen.
- **`usecase/`**: La lógica de negocio específica. Ejemplo: `UpdateRoomStatusUseCase` coordina lo que pasa cuando se limpia una habitación. **Es lo que usan los ViewModels.**

### 📁 `data/` (La Infraestructura)
Implementa las interfaces definidas en la capa de dominio.
- **`local/`**: Configuración de **Room Database** (SQLite). Aquí están los `Dao` y las `Entity`.
- **`remote/`**: Lógica para conectar con **Firebase Firestore** y **Auth**.
- **`repository/`**: Implementaciones reales (ej. `RoomRepositoryImpl`). Aquí es donde ocurre la magia del **Offline-First**: primero se guarda en Room y luego se intenta subir a Firebase.
- **`mapper/`**: Convierte datos entre formatos (ej. de `UserEntity` de base de datos a `User` de dominio).

### 📁 `presentation/` (La Interfaz)
Todo lo que el usuario ve y toca.
- **MVVM Pattern**: Cada pantalla tiene su propio `ViewModel` que gestiona el estado (`State`) y responde a eventos.
- **Jetpack Compose**: Interfaz declarativa moderna.
- **`theme/`**: Configuración de **Material Design 3** (colores, tipografía, formas).
- **`navigation/`**: Gestión de rutas y pantallas (Login -> Register -> Main).

---

## 2. Tecnologías Clave (Stack)

- **Inyección de Dependencias (Hilt):** Gestiona la creación de objetos automáticamente. Localizado en la carpeta `di/`.
- **Base de Datos Local (Room):** Permite que la app funcione sin internet.
- **Backend (Firebase):** Gestiona usuarios (Auth) y base de datos en la nube (Firestore).
- **Sincronización (WorkManager):** Localizado en la carpeta `sync/`. El `SyncWorker` se encarga de subir datos pendientes cuando el teléfono recupera conexión.
- **Cámara (CameraX):** Implementación personalizada en `presentation/camera/` para evidencias de mantenimiento.
- **Carga de Imágenes (Coil):** Librería eficiente para mostrar las fotos capturadas.

---

## 3. Flujo de Datos Típico

1. **Usuario** pulsa un botón (ej: "Marcar como Limpia").
2. El **ViewModel** llama al **UseCase** correspondiente.
3. El **UseCase** pide al **Repository** que actualice el dato.
4. El **Repository**:
   - Guarda el cambio en la base de datos **local (Room)** marcándolo como "sucio" (`isDirty = true`).
   - Envía el cambio a **Firebase**.
   - Si tiene éxito, marca el dato como sincronizado.
5. La **UI** se actualiza automáticamente gracias a los `Flow` de Kotlin que observan la base de datos.

---

## 4. Archivos de Configuración Críticos

- **`build.gradle.kts`**: Donde están todas las versiones de las librerías.
- **`AndroidManifest.xml`**: Define permisos (Cámara, Internet) y servicios de fondo.
- **`google-services.json`**: El puente de conexión con tu consola de Firebase.

---

> [!TIP]
> Si alguien te pregunta por qué elegiste esta estructura, la respuesta ganadora es: **"Para garantizar que la aplicación sea escalable, fácil de testear y, sobre todo, que pueda funcionar perfectamente en entornos con mala conexión a internet (Offline-First)."**
