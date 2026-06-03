package com.example.gestionhotelera.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.gestionhotelera.domain.model.UserRole
import com.example.gestionhotelera.ui.navigation.Screen

@Composable
fun HotelBottomBar(
    role: UserRole,
    currentRoute: String? = null,
    onNavigate: (String) -> Unit
) {
    val items = when (role) {
        UserRole.ADMIN -> listOf(
            Screen.AdminHome,
            Screen.HousekeepingHome,
            Screen.MaintenanceHome,
            Screen.RoomService,
            Screen.Profile
        )
        UserRole.HOUSEKEEPING -> listOf(
            Screen.HousekeepingHome,
            Screen.Profile
        )
        UserRole.MAINTENANCE -> listOf(
            Screen.MaintenanceHome,
            Screen.Profile
        )
    }

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { 
                    screen.icon?.let { icon ->
                        Icon(imageVector = icon, contentDescription = screen.title)
                    }
                },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = { onNavigate(screen.route) }
            )
        }
    }
}
