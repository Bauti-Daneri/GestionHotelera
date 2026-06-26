# 🏗️ DOCUMENTACIÓN DE ARQUITECTURA - HotelOps

## Resumen Ejecutivo

Este documento describe la arquitectura Clean Architecture implementada en el proyecto **HotelOps**, un SaaS multi-tenant para el sector hotelero desarrollado en Kotlin con Jetpack Compose.

---

## 📐 Principios de Clean Architecture

### 1. **Independencia de Framework**
La lógica de negocio (Domain Layer) está completamente desacoplada de:
- Android Framework
- Room Database
- Supabase Client
- Jetpack Compose

### 2. **Testabilidad**
Cada capa puede ser testeada independientemente:
- Domain: pruebas unitarias puras
- Data: pruebas con mocks
- Presentation: pruebas de ViewModel

### 3. **Mantenibilidad**
- Cambios en BD no afectan UI
- Cambios en API remota no afectan lógica
- Componentes reutilizables y coherentes

---

## 🎯 Flujo de Datos: Capas Interactuando

```
USER INTERACTION
       ↓
PRESENTATION LAYER (Jetpack Compose)
       ↓
ViewModel (StateFlow)
       ↓
USE CASE (Domain)
       ↓
REPOSITORY (Domain Interface)
       ↓
REPOSITORY IMPL (Data)
       ↓
DATA SOURCES (Local/Remote)
       ↓
Room DB / Supabase
       ↓
RESPONSE → MAPPER → DOMAIN MODEL
       ↓
ViewModel observes StateFlow
       ↓
UI Recomposes
```

---

## 🔀 Rutas de Comunicación (¿Quién habla con quién?)

### ✅ PERMITIDO:
```
Domain → Nada (Domain no conoce otras capas)
Data → Domain (implementa contratos)
Presentation → Domain (consume casos de uso)
DI → Todas (ensambla dependencias)
```

### ❌ PROHIBIDO:
```
Domain → Data (viola independencia)
Domain → Presentation (viola independencia)
Presentation → Data (viola abstracción)
Presentation → DTOs/Entities (son del dominio)
UI → Database directo (viola arquitectura)
```

---

## 📦 Contenido por Capa

### DOMAIN LAYER (`domain/`)
**Responsabilidad:** Reglas de negocio puras

| Carpeta | Contenido | Ejemplo |
|---------|----------|---------|
| `models/` | Clases de datos del negocio | `Booking.kt`, `Hotel.kt` |
| `repositories/` | Contratos/Interfaces | `HotelRepository` (interfaz) |
| `usecases/` | Acciones de negocio | `CreateBookingUseCase` |
| `exceptions/` | Errores de negocio | `BookingException` |

**Características:**
- Sin imports de Android
- Sin frameworks externos
- Lógica pura de Kotlin
- Completamente testeable

---

### DATA LAYER (`data/`)
**Responsabilidad:** Abstraer y proporcionar datos

| Carpeta | Contenido | Ejemplo |
|---------|----------|---------|
| `local/` | Room Database | `HotelDao`, `BookingEntity` |
| `remote/` | Supabase API | `RemoteDataSource`, `HotelDto` |
| `repositories/` | Implementaciones | `HotelRepositoryImpl` (implementa interfaz) |
| `mappers/` | Conversiones Entity↔Model↔DTO | `HotelMapper` |

**Características:**
- Implementa interfaces del Domain
- Orquesta local + remoto
- Transforma datos entre capas
- Maneja errores de BD/API

---

### PRESENTATION LAYER (`presentation/`)
**Responsabilidad:** Interfaz de usuario

| Carpeta | Contenido | Ejemplo |
|---------|----------|---------|
| `screens/` | Pantallas Compose | `HotelListScreen` |
| `viewmodels/` | ViewModel + StateFlow | `HotelListViewModel` |
| `components/` | Componentes reutilizables | `HotelCard`, `LoadingDialog` |
| `navigation/` | Rutas y navegación | `HotelOpsNavGraph` |
| `ui/theme/` | Estilos y colores | `HotelOpsTheme` |

**Características:**
- Solo consume Domain (casos de uso)
- Maneja UI state con StateFlow
- Reactiva a cambios de datos
- Sin lógica de negocio

---

### DEPENDENCY INJECTION (`di/`)
**Responsabilidad:** Ensamblar y proveer dependencias

| Módulo | Propósito |
|--------|----------|
| `DatabaseModule.kt` | Provee Room Database y DAOs |
| `RepositoryModule.kt` | Mapea interfaces → implementaciones |
| `AppModule.kt` | Dispatchers, contexto global |
| `SupabaseModule.kt` | Configuración Supabase (futuro) |

**Características:**
- Solo composición, sin lógica
- Centralizado y mantenible
- Facilita cambios de implementación

---

### WORKERS (`workers/`)
**Responsabilidad:** Tareas en segundo plano

| Worker | Propósito |
|--------|----------|
| `SyncWorker.kt` | Sincroniza datos periódicamente |
| `BookingSyncWorker.kt` | Sincroniza reservas |
| `TenantSyncWorker.kt` | Sincroniza tenant |

**Características:**
- Reutiliza repositorios
- Sin UI
- Reintentos automáticos
- Scheduling con WorkManager

---

### UTILITIES (`utils/`)
**Responsabilidad:** Funciones auxiliares

| Archivo | Propósito |
|---------|----------|
| `Constants.kt` | Valores globales |
| `DateFormatter.kt` | Formateo de fechas |
| `Logger.kt` | Logging centralizado |

---

## 🔄 Ejemplo Práctico: Crear Reserva

### 1. Usuario toca botón "Crear Reserva"
```kotlin
// HotelListScreen.kt (Presentation)
Button(onClick = { 
    viewModel.createBooking(roomId, checkInDate, checkOutDate) 
})
```

### 2. ViewModel dispara acción
```kotlin
// HotelListViewModel.kt
fun createBooking(roomId: String, checkIn: LocalDate, checkOut: LocalDate) {
    viewModelScope.launch(dispatcher.io) {
        try {
            createBookingUseCase(roomId, checkIn, checkOut)
            _uiState.value = UiState.Success
        } catch (e: Exception) {
            _uiState.value = UiState.Error(e.message)
        }
    }
}
```

### 3. UseCase ejecuta lógica de negocio
```kotlin
// CreateBookingUseCase.kt (Domain)
suspend operator fun invoke(roomId: String, checkIn: LocalDate, checkOut: LocalDate) {
    // Validación de dominio
    if (checkOut.isBefore(checkIn)) {
        throw ValidationException("Fecha de salida debe ser después de entrada")
    }
    
    // Delega al repositorio
    return bookingRepository.createBooking(roomId, checkIn, checkOut)
}
```

### 4. Repositorio orquesta datos
```kotlin
// BookingRepositoryImpl.kt (Data)
override suspend fun createBooking(roomId: String, checkIn: LocalDate, checkOut: LocalDate): Booking {
    // 1. Llamar API remota
    val dto = supabaseClient.createBooking(roomId, checkIn, checkOut)
    
    // 2. Mapear a Entity
    val entity = bookingMapper.toEntity(dto)
    
    // 3. Guardar localmente
    bookingDao.insertBooking(entity)
    
    // 4. Retornar Model al UseCase
    return bookingMapper.toDomain(entity)
}
```

### 5. ViewModel expone estado
```kotlin
// El StateFlow se actualiza automáticamente
uiState.value = UiState.Success(booking)
```

### 6. UI observa y recompone
```kotlin
// HotelListScreen.kt recompone con nuevo estado
val uiState by viewModel.uiState.collectAsState()
when (uiState) {
    is UiState.Success -> // Mostrar confirmación
    is UiState.Error -> // Mostrar error
    is UiState.Loading -> // Mostrar spinner
}
```

---

## 🧪 Testing por Capa

### Domain Testing (Unitarias)
```kotlin
@Test
fun createBooking_withInvalidDates_throwsValidationException() {
    val useCase = CreateBookingUseCase(mockRepository)
    
    assertThrows<ValidationException> {
        runBlocking {
            useCase("roomId", LocalDate.of(2024, 1, 10), LocalDate.of(2024, 1, 5))
        }
    }
}
```

### Data Testing (Con Mocks)
```kotlin
@Test
fun bookingRepository_createBooking_savesLocalAndRemote() = runTest {
    val mockDao = mockk<BookingDao>()
    val mockClient = mockk<SupabaseClient>()
    val repo = BookingRepositoryImpl(mockDao, mockClient)
    
    repo.createBooking(roomId, dates)
    
    verify { mockClient.createBooking(...) }
    verify { mockDao.insertBooking(...) }
}
```

### Presentation Testing (ViewModel)
```kotlin
@Test
fun viewModel_createBooking_updatesStateToSuccess() = runTest {
    val viewModel = BookingViewModel(mockUseCase)
    
    viewModel.createBooking(roomId, dates)
    
    assertEquals(UiState.Success, viewModel.uiState.value)
}
```

---

## 🔐 Seguridad y Buenas Prácticas

### Credenciales (Never commit!)
- `supabase.json` ← En `.gitignore`
- `local.properties` ← En `.gitignore`
- Usar `BuildConfig.DEBUG` para configuración

### Manejo de Errores
```kotlin
// Domain: Excepciones de negocio
throw BookingException("No hay habitaciones disponibles")

// Data: Envuelve en Result
return Result.failure(SyncException("API no disponible"))

// Presentation: Observa y muestra
when {
    is AuthException -> mostrarLoginScreen()
    is BookingException -> mostrarMensajeError()
}
```

### Sincronización
- WorkManager para tareas periódicas
- Repositorios coordinan local + remoto
- Conflictos resueltos con timestamp

---

## 📚 Referencias y Recursos

- **Clean Architecture:** https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html
- **MVVM + Compose:** https://developer.android.com/jetpack/compose/architecture
- **Room Database:** https://developer.android.com/training/data-storage/room
- **Hilt Dependency Injection:** https://developer.android.com/training/dependency-injection/hilt-android
- **Coroutines:** https://kotlinlang.org/docs/coroutines-overview.html

---

## 🚀 Próximos Pasos

1. **Implementar Repositorios** en `data/repositories/`
2. **Crear DTOs** en `data/remote/dto/` para Supabase
3. **Implementar Screen** de login en `presentation/screens/auth/`
4. **Configurar WorkManager** para sincronización
5. **Tests** para cada UseCase
6. **CI/CD** con GitHub Actions

---

**Última actualización:** 2026-06-10
**Versión:** 1.0
**Autor:** Tech Lead - HotelOps Team

