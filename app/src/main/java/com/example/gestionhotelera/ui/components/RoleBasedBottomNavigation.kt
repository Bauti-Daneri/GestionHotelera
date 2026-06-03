package com.example.gestionhotelera.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gestionhotelera.domain.model.DemoRole
import com.example.gestionhotelera.ui.navigation.Screen

@Composable
fun RoleBasedBottomNavigation(
    navController: NavController,
    role: DemoRole,
    modifier: Modifier = Modifier
) {
    val items = when (role) {
        DemoRole.ADMIN -> listOf(
            Screen.AdminHome,
            Screen.HousekeepingHome,
            Screen.MaintenanceHome,
            Screen.RoomService,
            Screen.Profile
        )
        DemoRole.HOUSEKEEPING -> listOf(
            Screen.HousekeepingHome,
            Screen.Profile
        )
        DemoRole.MAINTENANCE -> listOf(
            Screen.MaintenanceHome,
            Screen.Profile
        )
    }

    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                icon = { 
                    screen.icon?.let { Icon(it, contentDescription = screen.title) }
                },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
