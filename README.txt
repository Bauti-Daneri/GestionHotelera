================================================================================
                              HOTELOPS v1.0.0
                    Sistema de Gestión Operativa Hotelera
================================================================================

DESCRIPCIÓN
-----------
HotelOps es una plataforma SaaS multi-tenant diseñada para la gestión
operativa de establecimientos hoteleros. Permite a diferentes hoteles
registrarse de forma independiente y gestionar sus operaciones diarias con
total aislamiento de datos.

El sistema está optimizado para dispositivos móviles y proporciona módulos
específicos para cada rol operativo del hotel.


CARACTERÍSTICAS PRINCIPALES
----------------------------
✓ Sistema Multi-Tenant (múltiples hoteles en la misma plataforma)
✓ Onboarding automático para nuevos hoteles
✓ 3 roles diferenciados con permisos específicos
✓ 4 módulos operativos principales
✓ Aislamiento completo de datos por hotel
✓ Interfaz optimizada para móvil (Material Design 3)
✓ Gestión de usuarios por administrador
✓ Modo claro/oscuro


TECNOLOGÍAS UTILIZADAS
----------------------
- Tailwind CSS v4
- Radix UI (componentes accesibles)
- Next Themes (tema claro/oscuro)
- Vite (bundler)
- Material Design 3


ARQUITECTURA MULTI-TENANT
--------------------------
Cada hotel que se registra obtiene un identificador único (hotel_id) que
aísla completamente sus datos:

- Usuarios/Empleados filtrados por hotel_id
- Habitaciones filtradas por hotel_id
- Tickets de mantenimiento filtrados por hotel_id
- Pedidos de room service filtrados por hotel_id

Un hotel NO puede ver ni acceder a los datos de otros hoteles.


================================================================================
                            ACCESO AL SISTEMA
================================================================================

REGISTRO DE NUEVO HOTEL
-----------------------
1. Abrir la aplicación
2. En la pantalla de login, hacer click en "¿Nuevo hotel? Regístrate aquí"
3. Completar formulario:
   - Nombre del Hotel (obligatorio)
   - Ciudad, País, Dirección (opcional)
   - Nombre del Administrador (obligatorio)
   - Email Corporativo (obligatorio y único)
   - Contraseña (mínimo 6 caracteres)
   - Confirmar Contraseña
4. Click en "Crear Cuenta"
5. El sistema genera automáticamente un hotel_id único
6. Acceso inmediato al panel de administración


CREDENCIALES DE PRUEBA
----------------------
El sistema incluye datos de demostración para 2 hoteles:

HOTEL 1: Hotel Plaza Central (HOTEL-DEMO-001)
----------------------------------------------
Administrador:
  Email:    admin@hotel.com
  Password: admin123

Limpieza:
  Email:    limpieza@hotel.com
  Password: limpieza123

Mantenimiento:
  Email:    mantenimiento@hotel.com
  Password: mant123


HOTEL 2: Hotel Beach Resort (HOTEL-DEMO-002)
---------------------------------------------
Administrador:
  Email:    admin@hotelbeach.com
  Password: admin123

Limpieza:
  Email:    limpieza@hotelbeach.com
  Password: limpieza123


ACCESOS RÁPIDOS
---------------
En la pantalla de login hay 3 botones de acceso rápido para el Hotel 1:
- [Admin] → Acceso como administrador
- [Limpieza] → Acceso como personal de limpieza
- [Mantenimiento] → Acceso como personal de mantenimiento


================================================================================
                          ROLES Y PERMISOS
================================================================================

1. ADMINISTRADOR
----------------
Acceso completo al sistema. Puede:
✓ Ver y gestionar todos los módulos
✓ Crear, editar y eliminar usuarios
✓ Asignar roles a empleados
✓ Agregar y eliminar habitaciones
✓ Ver estadísticas generales
✓ Acceder al panel de administración

Navegación:
- 🛡️ Admin (Panel de administración)
- 🏠 Limpieza (Vista de habitaciones)
- 🔧 Mantenimiento (Vista de tickets)
- 🍽️ Room Service (Vista de pedidos)
- 👤 Perfil


2. LIMPIEZA (Housekeeping)
---------------------------
Enfocado en gestión de habitaciones. Puede:
✓ Ver todas las habitaciones
✓ Cambiar estados de habitaciones (Sucia/En Proceso/Limpia)
✓ Crear tickets de mantenimiento si encuentra problemas

NO puede:
✗ Agregar o eliminar habitaciones
✗ Ver lista de tickets de mantenimiento
✗ Ver Room Service
✗ Acceder al panel de administración

Navegación:
- 🏠 Limpieza (Vista de habitaciones)
- 👤 Perfil


3. MANTENIMIENTO (Maintenance)
-------------------------------
Enfocado en reparaciones y tickets. Puede:
✓ Ver todos los tickets de mantenimiento
✓ Cambiar estados de tickets (Abierto/En Progreso/Resuelto)
✓ Crear nuevos tickets
✓ Tomar fotos de reparaciones

NO puede:
✗ Ver módulo de limpieza de habitaciones
✗ Ver Room Service
✗ Acceder al panel de administración

Navegación:
- 🔧 Mantenimiento (Vista de tickets)
- 👤 Perfil


================================================================================
                            MÓDULOS DEL SISTEMA
================================================================================

1. MÓDULO DE ADMINISTRACIÓN
----------------------------
Acceso: Solo Administradores
Ubicación: Tab "Admin" en navegación inferior

FUNCIONALIDADES:

A) Pestaña "Usuarios"
   - Ver lista completa de empleados del hotel
   - Crear nuevo usuario:
     * Nombre completo
     * Email corporativo (único)
     * Contraseña inicial
     * Asignar rol (Admin/Limpieza/Mantenimiento)
     * Teléfono (opcional)
     * ID de empleado (generado automáticamente)

   - Editar usuario existente:
     * Cambiar nombre
     * Cambiar email
     * Cambiar rol (con advertencia)
     * Actualizar contraseña
     * Modificar teléfono

   - Eliminar usuario (con confirmación)
   - Validación de emails duplicados

B) Pestaña "Habitaciones"
   - Ver estadísticas generales:
     * Total de habitaciones
     * Total de empleados
     * Tickets abiertos
     * % de ocupación

   - Agregar nueva habitación:
     * Número de habitación
     * Piso
     * Tipo (Single/Double/Suite)

   - Eliminar habitación (con confirmación)
   - Ver lista completa de habitaciones

IMPORTANTE: Todos los usuarios creados se asocian automáticamente al
hotel_id del administrador que los crea.


2. MÓDULO DE LIMPIEZA
----------------------
Acceso: Administradores y Personal de Limpieza
Ubicación: Tab "Limpieza" en navegación inferior

FUNCIONALIDADES:

- Dashboard de habitaciones:
  * Cards con estadísticas (Sucias/En Proceso/Limpias)
  * Lista de todas las habitaciones
  * Estado visual con badges de color:
    - 🔴 Roja: Sucia / Necesita Limpieza
    - 🟡 Amarilla: En Proceso de Limpieza
    - 🟢 Verde: Limpia / Disponible

- Gestión de estado por habitación:
  1. Tocar una habitación
  2. Se abre Bottom Sheet con opciones
  3. Seleccionar nuevo estado con radio buttons
  4. Cambio se aplica inmediatamente

- Reportar problemas de mantenimiento:
  1. En el Bottom Sheet de la habitación
  2. Click en "⚠️ Reportar Problema de Mantenimiento"
  3. Seleccionar categoría:
     - Plomería
     - Electricidad
     - Sistemas
     - Mobiliario
     - Otro
  4. Describir el problema
  5. Crear ticket (se asigna automáticamente a Mantenimiento)

FLUJO TÍPICO:
1. Ver habitación 201 marcada como "Sucia" (roja)
2. Entrar a limpiar → Marcar "En Proceso" (amarilla)
3. Al limpiar, notar que el aire acondicionado no funciona
4. Crear ticket de mantenimiento desde el mismo Bottom Sheet
5. Terminar limpieza → Marcar "Limpia" (verde)


3. MÓDULO DE MANTENIMIENTO
---------------------------
Acceso: Administradores y Personal de Mantenimiento
Ubicación: Tab "Mantenimiento" en navegación inferior

FUNCIONALIDADES:

- Filtros por categoría:
  * Todos
  * 🔵 Plomería
  * 🟡 Electricidad
  * 🟣 Sistemas
  * 🟢 Mobiliario
  * ⚪ Otro

- Lista de tickets con información:
  * Número de habitación
  * Categoría con código de color
  * Descripción del problema
  * Estado actual (Abierto/En Progreso/Resuelto)
  * Indicador de foto adjunta
  * Indicador de sincronización (online/offline)

- Acciones por ticket según estado:

  Si está "Abierto" (amarillo):
  - [Marcar En Progreso] → Botón primario
  - [Marcar Resuelto] → Botón outline

  Si está "En Progreso" (azul):
  - [Marcar Resuelto] → Botón primario único

  Si está "Resuelto" (verde):
  - Sin botones (ticket cerrado)

- Crear nuevo ticket:
  1. Click en botón FAB (+) flotante
  2. Completar formulario:
     - Número de habitación
     - Categoría del problema
     - Descripción detallada
     - 📷 Tomar foto (opcional)
  3. Crear ticket

FLUJO TÍPICO:
1. Recibir ticket creado por Limpieza (habitación 203)
2. Ver detalles: "Aire acondicionado no funciona"
3. Ir a la habitación con herramientas
4. Marcar ticket como "En Progreso"
5. Tomar foto de la reparación (evidencia)
6. Completar la reparación
7. Marcar ticket como "Resuelto"


4. MÓDULO DE ROOM SERVICE
--------------------------
Acceso: Solo Administradores
Ubicación: Tab "Room Service" en navegación inferior

FUNCIONALIDADES:

- Gestión de pedidos por habitación
- Búsqueda por:
  * Número de habitación
  * Nombre de huésped
  * Plato específico

- Filtros por estado:
  * Todos
  * Pendientes
  * En Proceso
  * Entregados

- Tarjetas de pedidos muestran:
  * Número de habitación (título)
  * Nombre del huésped
  * Lista de items con cantidades y precios
  * Hora de creación
  * Total del pedido
  * Estado con badge de color

- Estados de pedido:

  🟡 Pendiente (recién creado):
  - [Marcar En Proceso] → Enviar a cocina
  - [Entregado] → Atajo si ya está listo

  🔵 En Proceso (en preparación):
  - [Marcar Entregado] → Completar pedido

  🟢 Entregado (finalizado):
  - Sin acciones

- Crear nuevo pedido:
  1. Click en botón FAB (+) flotante
  2. Modal "Cargar Nuevo Pedido"
  3. Ingresar número de habitación
  4. Seleccionar items del menú:
     - Desayuno Continental ($15)
     - Desayuno Americano ($20)
     - Café Espresso ($5)
     - Jugo Natural ($6)
     - Hamburguesa Gourmet ($25)
     - Ensalada César ($18)
     - Salmón a la Plancha ($32)
     - Pasta Carbonara ($22)
     - Vino Tinto ($28)
     - Agua Mineral ($3)
     - Club Sandwich ($16)
     - Pizza Margarita ($24)
  5. Ajustar cantidades con botones +/-
  6. Ver total calculado
  7. Crear pedido

FLUJO TÍPICO:
1. Huésped de habitación 105 llama solicitando desayuno
2. Recepcionista crea pedido:
   - Habitación: 105
   - Items: 2x Desayuno Continental, 2x Café Espresso
   - Total: $40
3. Pedido aparece como [Pendiente] (amarillo)
4. Cocina ve pedido y marca [En Proceso] (azul)
5. Cocina prepara el pedido
6. Camarero entrega y marca [Entregado] (verde)


5. MÓDULO DE PERFIL
-------------------
Acceso: Todos los roles
Ubicación: Tab "Perfil" en navegación inferior

FUNCIONALIDADES:

- Información del hotel:
  * Nombre del establecimiento
  * ID del hotel

- Información personal:
  * Nombre completo
  * Email corporativo
  * Teléfono
  * Departamento
  * Horario de trabajo
  * ID de empleado

- Badge de rol con color específico:
  * 🟣 Morado: Administrador
  * 🔵 Azul: Limpieza
  * 🟢 Verde: Mantenimiento

- Preferencias:
  * Alternar modo claro/oscuro
  * (Más opciones en futuras versiones)

- Estadísticas del día:
  * Habitaciones/Tickets completados
  * (Según el rol del usuario)

- Cerrar sesión


================================================================================
                        GESTIÓN DE USUARIOS
================================================================================

CREAR NUEVO USUARIO (Solo Administradores)
-------------------------------------------
1. Ir al tab "Admin"
2. Seleccionar pestaña "Usuarios"
3. Click en botón "➕ Nuevo Usuario"
4. Completar formulario:
   - Nombre completo (obligatorio)
   - Email corporativo (obligatorio y único)
   - Contraseña inicial (obligatorio)
   - Rol: Administrador / Limpieza / Mantenimiento
   - Teléfono (opcional)
5. Click en "Crear Usuario"
6. Sistema genera automáticamente:
   - ID de empleado (EMP-2024-XXX)
   - Departamento según rol
   - Fecha de creación
7. El usuario puede hacer login inmediatamente con sus credenciales


EDITAR USUARIO EXISTENTE (Solo Administradores)
------------------------------------------------
1. En la lista de usuarios, click en botón de edición (lápiz)
2. Modal de edición se abre con datos actuales
3. Modificar campos deseados:
   - Nombre
   - Email (validación de duplicados)
   - Contraseña
   - Rol (aparece advertencia de cambio)
   - Teléfono
4. Click en "Guardar Cambios"
5. Si se cambia el rol, el departamento se actualiza automáticamente


ELIMINAR USUARIO (Solo Administradores)
----------------------------------------
1. En la lista de usuarios, click en botón de eliminación (basura)
2. Aparece diálogo de confirmación
3. Confirmar eliminación
4. Usuario es removido del sistema


VALIDACIONES
------------
- Email debe ser único en todo el sistema
- Al editar, el email actual del usuario no cuenta como duplicado
- Formato de email válido obligatorio
- Cambio de rol muestra advertencia antes de aplicar
- No se puede eliminar el último administrador del hotel


================================================================================
                        FLUJOS DE TRABAJO TÍPICOS
================================================================================

CASO 1: PERSONAL DE LIMPIEZA ENCUENTRA PROBLEMA
------------------------------------------------
Contexto: María (Limpieza) entra a limpiar habitación 203

1. Ve que habitación 203 está "Sucia" (roja)
2. Toca la tarjeta de habitación 203
3. Bottom Sheet se abre
4. Marca estado como "En Proceso" (amarilla)
5. Al limpiar el baño, nota que el inodoro no descarga bien
6. Sin cerrar el Bottom Sheet, toca "⚠️ Reportar Problema de Mantenimiento"
7. Selecciona categoría: "Plomería"
8. Describe: "Inodoro no descarga correctamente, necesita revisión"
9. Crea el ticket
10. Termina limpieza y marca "Limpia" (verde)

Resultado: Ticket creado y asignado a Mantenimiento sin interrumpir el flujo


CASO 2: MANTENIMIENTO RESUELVE PROBLEMA
----------------------------------------
Contexto: Carlos (Mantenimiento) revisa tickets pendientes

1. En su módulo de Mantenimiento, ve ticket creado por María
2. Filtra por categoría "Plomería" para priorizar
3. Ve ticket: "Hab. #203 - Inodoro no descarga correctamente"
4. Toca "Marcar En Progreso" antes de ir a la habitación
5. Va a habitación 203 con herramientas
6. Repara el inodoro (válvula de descarga defectuosa)
7. Toma foto del inodoro reparado
8. Regresa a la app y toca "Marcar Resuelto"

Resultado: Ticket cerrado, evidencia fotográfica guardada


CASO 3: ADMINISTRADOR CREA EMPLEADO
------------------------------------
Contexto: Hotel contrata nueva empleada de limpieza

1. Ana (Admin) accede al panel "Admin"
2. Selecciona pestaña "Usuarios"
3. Toca botón "➕ Nuevo Usuario"
4. Completa formulario:
   - Nombre: "Laura Martínez"
   - Email: "laura.martinez@hotel.com"
   - Contraseña: "limpieza2024"
   - Rol: "Limpieza"
   - Teléfono: "+34 620 000 000"
5. Sistema valida que el email no exista
6. Genera automáticamente:
   - ID: EMP-2024-004
   - Departamento: Limpieza
   - Fecha de creación: Hoy
7. Confirma creación
8. Laura ahora puede hacer login
9. Ana comparte credenciales con Laura

Resultado: Nueva empleada registrada con acceso inmediato


CASO 4: RECEPCIÓN GESTIONA ROOM SERVICE
----------------------------------------
Contexto: Huésped llama solicitando desayuno

1. Recepcionista abre módulo Room Service
2. Click en botón FAB (+)
3. Modal "Cargar Nuevo Pedido" se abre
4. Ingresa:
   - Habitación: "105"
   - Items:
     * 2x Desayuno Continental
     * 2x Café Espresso
5. Ve total: $40
6. Click en "Crear Pedido"
7. Pedido aparece con estado [Pendiente]
8. Cocina ve el pedido
9. Cocina marca [En Proceso]
10. Cocina prepara y entrega
11. Marca [Entregado]

Resultado: Pedido gestionado de principio a fin con seguimiento


CASO 5: VERIFICACIÓN DE AISLAMIENTO MULTI-TENANT
-------------------------------------------------
Contexto: Dos hoteles operando simultáneamente

Hotel Plaza Central:
1. Admin loguea: admin@hotel.com
2. Ve 3 empleados en su lista
3. Crea nuevo usuario: pedro@hotel.com
4. Ve 4 empleados en total
5. Solo ve habitaciones de Plaza Central

Hotel Beach Resort:
1. Admin loguea: admin@hotelbeach.com
2. Ve 2 empleados en su lista (NO ve los de Plaza Central)
3. Crea nuevo usuario: sofia@hotelbeach.com
4. Ve 3 empleados en total (solo de Beach Resort)
5. Solo ve habitaciones de Beach Resort

Resultado: Aislamiento perfecto entre hoteles


================================================================================
                        ESTRUCTURA DE NAVEGACIÓN
================================================================================

Bottom Navigation (Adaptada por Rol)
-------------------------------------

ADMINISTRADOR:
┌─────────────────────────────────────────────────────────┐
│ [Admin] [Limpieza] [Mantenimiento] [Room Service] [👤] │
└─────────────────────────────────────────────────────────┘

LIMPIEZA:
┌─────────────────────────────────────────────────────────┐
│              [Limpieza]              [👤]               │
└─────────────────────────────────────────────────────────┘

MANTENIMIENTO:
┌─────────────────────────────────────────────────────────┐
│           [Mantenimiento]            [👤]               │
└─────────────────────────────────────────────────────────┘

La navegación se adapta automáticamente según los permisos del rol.


================================================================================
                        TEMAS Y PERSONALIZACIÓN
================================================================================

MODO CLARO/OSCURO
-----------------
El sistema incluye soporte completo para tema claro y oscuro:

1. Ir a módulo "Perfil"
2. Sección "Preferencias"
3. Alternar switch "Modo Oscuro"
4. El tema cambia inmediatamente en toda la aplicación

Colores principales:
- Modo Claro: Fondo blanco, texto oscuro
- Modo Oscuro: Fondo azul oscuro, texto claro

Los colores de estado se mantienen consistentes en ambos temas.


ESQUEMA DE COLORES
------------------
Primary (Azul Oscuro):   #1A365D
Secondary (Verde):       #047857
Destructive (Rojo):      #DC2626
Warning (Amarillo):      #F59E0B
Success (Verde):         #10B981

Estos colores se usan consistentemente en toda la aplicación para:
- Estados de habitaciones
- Estados de tickets
- Estados de pedidos
- Badges de roles
- Botones de acción


================================================================================
                        PREGUNTAS FRECUENTES
================================================================================

P: ¿Cómo puedo resetear la contraseña de un usuario?
R: Como administrador, edita el usuario y cambia el campo "Contraseña".

P: ¿Puedo eliminar habitaciones?
R: Solo los administradores pueden eliminar habitaciones desde el panel Admin.

P: ¿Los tickets de mantenimiento se pueden eliminar?
R: Actualmente no. Se marcan como "Resuelto" para mantener historial.

P: ¿Qué pasa si dos empleados intentan cambiar el mismo estado?
R: El último cambio prevalece. En producción con Supabase, se implementará
   sincronización en tiempo real.

P: ¿Puedo ver habitaciones de otros hoteles?
R: No. El sistema garantiza aislamiento total. Solo ves datos de tu hotel.

P: ¿Cómo sé qué hotel es el mío?
R: En el módulo "Perfil" se muestra el nombre y ID de tu hotel.

P: ¿Puedo tener múltiples administradores?
R: Sí. Puedes crear todos los administradores que necesites.

P: ¿Se pueden agregar más platos al menú de Room Service?
R: En la versión actual, el menú es fijo. Se agregará gestión de menú en
   futuras versiones.

P: ¿Cómo funciona la cámara en tickets de mantenimiento?
R: Click en el botón de cámara abre la cámara del dispositivo. La foto se
   adjunta al ticket.

P: ¿Los datos persisten al cerrar sesión?
R: Actualmente los datos son de demostración y se resetean. En producción con
   Supabase, los datos persisten permanentemente.


================================================================================
                        PRÓXIMAS FUNCIONALIDADES
================================================================================

EN DESARROLLO:
- Migración a Supabase para persistencia real de datos
- Sincronización en tiempo real entre dispositivos
- Notificaciones push para nuevos tickets/pedidos
- Sistema de reportes y estadísticas avanzadas
- Gestión de menú personalizable por hotel
- Check-in/Check-out de huéspedes
- Facturación y pagos
- Dashboard de métricas para administradores

PLANIFICADAS:
- App móvil nativa (iOS/Android)
- Integración con sistemas PMS
- Multi-propiedad (cadenas hoteleras)
- API pública para integraciones
- Módulo de inventario y compras
- Gestión de eventos y reservas
- Sistema de calificaciones y reviews
- Inteligencia artificial para predicciones


================================================================================
                        SOPORTE Y CONTACTO
================================================================================

Documentación completa:
- SISTEMA_DE_ROLES.md - Descripción detallada de roles y permisos
- GESTION_DE_USUARIOS.md - Guía completa de gestión de usuarios
- CREDENCIALES_ACCESO.md - Información sobre autenticación
- SISTEMA_MULTITENANT.md - Arquitectura multi-tenant
- ROOM_SERVICE_ACTUALIZADO.md - Guía del módulo Room Service
- DESIGN_SYSTEM.md - Sistema de diseño y componentes
- PANTALLAS_Y_FLUJOS_FIGMA.md - Diseños de pantallas

Reportar problemas:
- GitHub Issues: https://github.com/anthropics/claude-code/issues

Versión: 1.0.0
Última actualización: Mayo 2026
Material Design 3 | React 18.3 | TypeScript


================================================================================
                        INICIO RÁPIDO
================================================================================

Para comenzar a usar HotelOps:

1. REGISTRO (Hotel nuevo):
   → Click en "¿Nuevo hotel? Regístrate aquí"
   → Completa el formulario
   → ¡Listo! Ya puedes empezar a crear usuarios

2. LOGIN (Hotel existente):
   → Ingresa email y contraseña
   → O usa los botones de acceso rápido

3. CREAR TU PRIMER EMPLEADO:
   → Tab "Admin" → "Usuarios" → "Nuevo Usuario"
   → Completa datos y asigna rol
   → Comparte credenciales con el empleado

4. AGREGAR HABITACIONES:
   → Tab "Admin" → "Habitaciones" → "Agregar"
   → Define número, piso y tipo
   → ¡Ya puedes gestionar limpieza!

5. EMPEZAR A OPERAR:
   → Personal de limpieza cambia estados
   → Mantenimiento resuelve tickets
   → Admin supervisa todo desde el panel


¡Bienvenido a HotelOps! 🏨

================================================================================
