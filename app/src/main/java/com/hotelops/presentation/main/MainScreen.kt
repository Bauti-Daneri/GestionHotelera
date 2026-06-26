package com.hotelops.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hotelops.domain.model.UserRole
import com.hotelops.presentation.admin.AdminScreen
import com.hotelops.presentation.housekeeping.HousekeepingScreen
import com.hotelops.presentation.maintenance.MaintenanceScreen
import com.hotelops.presentation.navigation.Screen
import com.hotelops.presentation.profile.ProfileScreen
import com.hotelops.presentation.roomservice.RoomServiceScreen

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val roles: List<UserRole>
) {
    object Admin : BottomNavItem("admin", "Admin", Icons.Filled.Shield, Icons.Outlined.Shield, listOf(UserRole.ADMIN))
    object Housekeeping : BottomNavItem("housekeeping", "Limpieza", Icons.Filled.Home, Icons.Outlined.Home, listOf(UserRole.ADMIN, UserRole.HOUSEKEEPING))
    object Maintenance : BottomNavItem("maintenance", "Mantenimiento", Icons.Filled.Build, Icons.Outlined.Build, listOf(UserRole.ADMIN, UserRole.MAINTENANCE))
    object RoomService : BottomNavItem("room_service", "Pedidos", Icons.Filled.Restaurant, Icons.Outlined.Restaurant, UserRole.entries)
    object Profile : BottomNavItem("profile", "Perfil", Icons.Filled.Person, Icons.Outlined.Person, UserRole.entries)
}

val allItems = listOf(
    BottomNavItem.Admin,
    BottomNavItem.Housekeeping,
    BottomNavItem.Maintenance,
    BottomNavItem.RoomService,
    BottomNavItem.Profile
)

@Composable
fun MainScreen(
    onLogout: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val colorScheme = MaterialTheme.colorScheme

    LaunchedEffect(state.isLoggedOut) {
        if (state.isLoggedOut) {
            onLogout()
        }
    }

    val visibleItems = remember(state.currentUser?.role) {
        allItems.filter { it.roles.contains(state.currentUser?.role) }
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                visibleItems.forEach { item ->
                    val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.label,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = { 
                            Text(
                                item.label, 
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            ) 
                        },
                        selected = isSelected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = colorScheme.primary,
                            selectedTextColor = colorScheme.primary,
                            unselectedIconColor = colorScheme.onSurfaceVariant,
                            unselectedTextColor = colorScheme.onSurfaceVariant,
                            indicatorColor = colorScheme.primaryContainer
                        )
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = if (state.currentUser?.role == UserRole.ADMIN) "admin" else "housekeeping",
            modifier = Modifier.padding(padding)
        ) {
            composable("admin") { AdminScreen(state.currentUser) }
            composable("housekeeping") { HousekeepingScreen(state.currentUser) }
            composable("maintenance") { MaintenanceScreen(state.currentUser) }
            composable("room_service") { RoomServiceScreen(state.currentUser) }
            composable("profile") { ProfileScreen(state.currentUser, viewModel::logout) }
        }
    }
}
