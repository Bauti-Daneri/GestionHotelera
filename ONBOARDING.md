# 🚀 ONBOARDING - HotelOps Development

## 👋 Bienvenido al proyecto HotelOps

Este documento te guía paso a paso para comenzar a desarrollar en el proyecto, siguiendo Clean Architecture.

---

## 📋 Pre-requisitos

Asegúrate de tener instalado:

```bash
✅ Android Studio: Canary/Electric Eel o superior
✅ Kotlin: 1.9.20+
✅ Gradle: 8.0+
✅ JDK: 17+
✅ Git
```

---

## 🎯 Primeros Pasos

### 1. Clonar y configurar el proyecto

```bash
# Clonar
git clone <repository-url>
cd GestionHotelera

# Instalar gradle wrapper
./gradlew --version
```

### 2. Abrir en Android Studio

```bash
# Desde la línea de comandos
open -a "Android Studio" .

# O abrirlo manualmente: Android Studio → Open → Seleccionar carpeta
```

### 3. Sincronizar Gradle

```
File → Sync Now
# Esperar a que se descarguen todas las dependencias
```

### 4. Verificar estructura

```bash
# Verificar que todos los archivos están creados
ls -la app/src/main/java/com/hotelops/app/

# Contar archivos por capa
find app/src/main/java/com/hotelops/app -type d | grep -E "(domain|data|presentation|di)" | sort
```

---

## 📚 Entender la Arquitectura

### Lectura Recomendada (En Orden)

1. **ARCHITECTURE.md** - Conceptos generales
2. **TREE.md** - Estructura visual completa
3. **README.md** - Configuración y stack
4. **Este archivo** - Guía práctica

### Videos Recomendados

- Clean Architecture: https://youtu.be/7mBzl_yxPT4
- MVVM + Compose: https://youtu.be/e-sVvUgH2gU
- Room Database: https://youtu.be/7SsqnTkm0Qo

---

## 🛠️ Flujo Típico: Implementar una Feature

### Ejemplo: Crear feature "GetAvailableRooms"

#### Paso 1: Crear modelo en Domain

**Archivo:** `domain/models/Room.kt` (ya existe)

```kotlin
data class Room(
    val id: String,
    val hotelId: String,
    val roomNumber: String,
    // ... propiedades
)
```

#### Paso 2: Crear interfaz de repositorio en Domain

**Archivo:** `domain/repositories/RoomRepository.kt` (ya existe)

```kotlin
interface RoomRepository {
    suspend fun getAvailableRooms(
        hotelId: String,
        checkInDate: LocalDate,
        checkOutDate: LocalDate
    ): Result<List<Room>>
}
```

#### Paso 3: Crear UseCase en Domain

**Archivo:** `domain/usecases/GetAvailableRoomsUseCase.kt` (NUEVO)

```kotlin
class GetAvailableRoomsUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    suspend operator fun invoke(
        hotelId: String,
        checkInDate: LocalDate,
        checkOutDate: LocalDate
    ) = roomRepository.getAvailableRooms(hotelId, checkInDate, checkOutDate)
}
```

#### Paso 4: Crear entidad local en Data

**Archivo:** `data/local/entities/RoomEntity.kt` (ya existe)

#### Paso 5: Crear DTO en Data

**Archivo:** `data/remote/dto/RoomDto.kt` (ya existe)

#### Paso 6: Crear DAO en Data

**Archivo:** `data/local/dao/RoomDao.kt` (ya existe, agregar queries si es necesario)

```kotlin
@Query("SELECT * FROM rooms WHERE hotelId = :hotelId AND isAvailable = 1")
suspend fun getAvailableRoomsByHotel(hotelId: String): List<RoomEntity>
```

#### Paso 7: Crear Mapper en Data

**Archivo:** `data/mappers/RoomMapper.kt` (NUEVO)

```kotlin
object RoomMapper {
    fun entityToDomain(entity: RoomEntity): Room { ... }
    fun dtoToEntity(dto: RoomDto): RoomEntity { ... }
}
```

#### Paso 8: Implementar Repositorio en Data

**Archivo:** `data/repositories/RoomRepositoryImpl.kt` (NUEVO)

```kotlin
class RoomRepositoryImpl @Inject constructor(
    private val roomDao: RoomDao
) : RoomRepository {
    override suspend fun getAvailableRooms(...) = Result.success(...)
}
```

#### Paso 9: Inyectar en Hilt

**Archivo:** `di/modules/RepositoryModule.kt`

```kotlin
@Binds
abstract fun bindRoomRepository(impl: RoomRepositoryImpl): RoomRepository
```

#### Paso 10: Usar en ViewModel

**Archivo:** `presentation/viewmodels/RoomListViewModel.kt` (NUEVO)

```kotlin
class RoomListViewModel @Inject constructor(
    private val getAvailableRoomsUseCase: GetAvailableRoomsUseCase
) : ViewModel() {
    fun loadAvailableRooms(hotelId: String, dates: DateRange) {
        viewModelScope.launch {
            val result = getAvailableRoomsUseCase(hotelId, dates.start, dates.end)
            uiState.value = when {
                result.isSuccess -> UiState.Success(result.getOrNull() ?: emptyList())
                else -> UiState.Error(result.exceptionOrNull()?.message ?: "Error")
            }
        }
    }
}
```

#### Paso 11: Crear Composable Screen

**Archivo:** `presentation/screens/hotel/RoomListScreen.kt` (NUEVO)

```kotlin
@Composable
fun RoomListScreen(
    hotelId: String,
    viewModel: RoomListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    when (uiState) {
        is UiState.Success -> RoomList((uiState as UiState.Success).rooms)
        is UiState.Error -> ErrorDialog((uiState as UiState.Error).message)
        else -> LoadingSpinner()
    }
}
```

---

## 🧪 Testing: Verificar Implementación

### Test del UseCase (Domain - Unitario)

**Archivo:** `app/src/test/java/com/hotelops/app/domain/usecases/GetAvailableRoomsUseCaseTest.kt`

```kotlin
@Test
fun getAvailableRooms_withValidDates_returnsRooms() = runTest {
    val mockRepo = mockk<RoomRepository>()
    val rooms = listOf(mockRoom1, mockRoom2)
    
    coEvery { mockRepo.getAvailableRooms(...) } returns Result.success(rooms)
    
    val useCase = GetAvailableRoomsUseCase(mockRepo)
    val result = useCase("hotelId", date1, date2)
    
    assertTrue(result.isSuccess)
    assertEquals(2, result.getOrNull()?.size)
}
```

### Test del Repositorio (Data - Integración)

**Archivo:** `app/src/test/java/com/hotelops/app/data/repositories/RoomRepositoryImplTest.kt`

```kotlin
@Test
fun getAvailableRooms_queriesLocalDatabase() = runTest {
    val mockDao = mockk<RoomDao>()
    val rooms = listOf(roomEntity1, roomEntity2)
    
    coEvery { mockDao.getAvailableRoomsByHotel(...) } returns rooms
    
    val repo = RoomRepositoryImpl(mockDao)
    val result = repo.getAvailableRooms("hotelId", date1, date2)
    
    assertTrue(result.isSuccess)
    coVerify { mockDao.getAvailableRoomsByHotel(...) }
}
```

### Test del ViewModel (Presentation - UI)

**Archivo:** `app/src/test/java/com/hotelops/app/presentation/viewmodels/RoomListViewModelTest.kt`

```kotlin
@Test
fun loadAvailableRooms_updatesStateToSuccess() = runTest {
    val mockUseCase = mockk<GetAvailableRoomsUseCase>()
    coEvery { mockUseCase(...) } returns Result.success(rooms)
    
    val viewModel = RoomListViewModel(mockUseCase)
    viewModel.loadAvailableRooms("hotelId", dates)
    
    assertEquals(UiState.Success(rooms), viewModel.uiState.value)
}
```

---

## 🐛 Troubleshooting

### Problema: "Unresolved reference: @Inject"

**Solución:** Agregar Hilt a `build.gradle.kts`:

```kotlin
plugins {
    id("com.google.dagger.hilt.android") version "2.46"
}

dependencies {
    implementation("com.google.dagger:hilt-android:2.46")
    kapt("com.google.dagger:hilt-compiler:2.46")
}
```

### Problema: "Cannot find database class"

**Solución:** Asegurar que `HotelOpsDatabase` está en la ruta correcta y Room tiene dependencias:

```kotlin
dependencies {
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")
}
```

### Problema: Gradle build falla

**Solución:** Limpiar y reconstruir:

```bash
./gradlew clean
./gradlew build
```

---

## 📖 Convenciones de Código

### Nombres de Variables

```kotlin
// ✅ BUENO - Descriptivo
val availableRoomList: List<Room>
val userLoginEmail: String

// ❌ MALO - Ambiguo
val rooms: List<Room>
val email: String
```

### Comentarios

```kotlin
// ✅ BUENO - Explica el "por qué"
// Filtrar solo habitaciones disponibles para evitar sobrebookings
val availableRooms = rooms.filter { it.isAvailable }

// ❌ MALO - Explica lo obvio
// Filtrar habitaciones
val availableRooms = rooms.filter { it.isAvailable }
```

### Funciones Suspensas

```kotlin
// ✅ BUENO - Claro que es async
suspend fun getHotels(): Result<List<Hotel>>

// ❌ MALO - Confuso
fun getHotels(): Result<List<Hotel>>
```

---

## ✅ Checklist de Validación

Antes de hacer commit, verifica:

```
[ ] ¿El código está en la capa correcta (Domain/Data/Presentation)?
[ ] ¿Se respetan las dependencias (Domain → Data → Presentation)?
[ ] ¿Hay tests unitarios para lógica de negocio?
[ ] ¿Los DTOs/Entities se mapean correctamente?
[ ] ¿No hay imports de framework en Domain?
[ ] ¿Los ViewModels usan @Inject de Hilt?
[ ] ¿El código compila sin warnings?
[ ] ¿Se siguieron las convenciones de nombres?
```

---

## 🚀 Próximas Features Sugeridas

1. **Authentication** - Login/Register
2. **Hotel List** - Listar hoteles del tenant
3. **Room Availability** - Disponibilidad de habitaciones
4. **Booking Creation** - Crear reserva
5. **Sync Worker** - Sincronización periódica

---

## 📞 Contacto y Soporte

- **Tech Lead:** [Correo]
- **Documentación:** Ver `/ARCHITECTURE.md`
- **Issues:** GitHub Issues

---

**Última actualización:** 2026-06-10  
**Versión:** 1.0  
**¡Bienvenido al equipo HotelOps! 🚀**

