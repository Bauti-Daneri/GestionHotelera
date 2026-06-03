package com.example.gestionhotelera.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector? = null) {
    // Auth
    object Login : Screen("login", "Login")

    // Admin Flow
    object AdminHome : Screen("admin_home", "Administración", Icons.Rounded.Dashboard)
    object EmployeeManagement : Screen("employee_mgmt", "Empleados", Icons.Rounded.People)
    
    // Housekeeping Flow
    object HousekeepingHome : Screen("housekeeping_home", "Housekeeping", Icons.Rounded.CleaningServices)
    
    // Maintenance Flow
    object MaintenanceHome : Screen("maintenance_home", "Mantenimiento", Icons.Rounded.Engineering)
    
    // Common
    object RoomService : Screen("room_service", "Room Service", Icons.Rounded.Restaurant)
    object Profile : Screen("profile", "Perfil", Icons.Rounded.Person)
    
    // Details
    object RoomDetail : Screen("room_detail/{roomId}", "Detalle de Habitación") {
        fun createRoute(roomId: String) = "room_detail/$roomId"
    }
}
