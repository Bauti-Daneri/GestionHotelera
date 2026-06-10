package com.example.gestionhotelera.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gestionhotelera.domain.model.DemoRole
import com.example.gestionhotelera.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoleBasedBottomNavigation(
    navController: NavController,
    role: DemoRole,
    notificationCounts: NotificationCounts = NotificationCounts(),
    modifier: Modifier = Modifier
) {
    val items = when (role) {
        DemoRole.ADMIN -> listOf(
            Screen.HousekeepingHome,
            Screen.MaintenanceHome,
            Screen.AdminHome,
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
            val badgeCount = when (screen) {
                Screen.MaintenanceHome -> notificationCounts.openTickets
                Screen.HousekeepingHome -> notificationCounts.dirtyRooms
                Screen.RoomService -> notificationCounts.pendingOrders
                else -> 0
            }

            NavigationBarItem(
                icon = { 
                    BadgedBox(
                        badge = {
                            if (badgeCount > 0) {
                                Badge {
                                    Text(text = if (badgeCount > 99) "99+" else badgeCount.toString())
                                }
                            }
                        }
                    ) {
                        screen.icon?.let { Icon(it, contentDescription = screen.title) }
                    }
                },
                label = { 
                    val shortTitle = when (screen) {
                        Screen.HousekeepingHome -> "Limpieza"
                        Screen.MaintenanceHome -> "Mant."
                        Screen.RoomService -> "Room"
                        Screen.AdminHome -> "Admin"
                        else -> screen.title
                    }
                    Text(
                        text = shortTitle,
                        fontSize = 11.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    ) 
                },
                selected = currentRoute == screen.route,
                alwaysShowLabel = true,
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
