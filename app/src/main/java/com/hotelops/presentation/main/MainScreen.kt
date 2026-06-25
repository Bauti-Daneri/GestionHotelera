package com.hotelops.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hotelops.domain.model.UserRole
import com.hotelops.presentation.admin.AdminScreen
import com.hotelops.presentation.housekeeping.HousekeepingScreen
import com.hotelops.presentation.maintenance.MaintenanceScreen
import com.hotelops.presentation.profile.ProfileScreen
import com.hotelops.presentation.roomservice.RoomServiceScreen

private sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val roles: List<UserRole>
) {
    object Admin : BottomNavItem("main_admin", "Admin", Icons.Default.Settings,
        listOf(UserRole.ADMIN))
    object Housekeeping : BottomNavItem("main_housekeeping", "Limpieza", Icons.Default.Star,
        listOf(UserRole.ADMIN, UserRole.HOUSEKEEPING))
    object Maintenance : BottomNavItem("main_maintenance", "Mantenimiento", Icons.Default.Build,
        listOf(UserRole.ADMIN, UserRole.MAINTENANCE))
    object RoomService : BottomNavItem("main_roomservice", "Room Service", Icons.Default.Dashboard,
        listOf(UserRole.ADMIN, UserRole.HOUSEKEEPING, UserRole.MAINTENANCE))
    object Profile : BottomNavItem("main_profile", "Perfil", Icons.Default.Person,
        listOf(UserRole.ADMIN, UserRole.HOUSEKEEPING, UserRole.MAINTENANCE))
}

private val allItems = listOf(
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

    LaunchedEffect(state.isLoggedOut) {
        if (state.isLoggedOut) onLogout()
    }

    val currentUser = state.currentUser
    val userRole = currentUser?.role ?: UserRole.HOUSEKEEPING

    val visibleItems = allItems.filter { userRole in it.roles }
    val startRoute = when (userRole) {
        UserRole.ADMIN -> BottomNavItem.Admin.route
        UserRole.HOUSEKEEPING -> BottomNavItem.Housekeeping.route
        UserRole.MAINTENANCE -> BottomNavItem.Maintenance.route
    }

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                visibleItems.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(startRoute) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startRoute,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Admin.route) {
                AdminScreen(currentUser = currentUser)
            }
            composable(BottomNavItem.Housekeeping.route) {
                HousekeepingScreen(currentUser = currentUser)
            }
            composable(BottomNavItem.Maintenance.route) {
                MaintenanceScreen(currentUser = currentUser)
            }
            composable(BottomNavItem.RoomService.route) {
                RoomServiceScreen(currentUser = currentUser)
            }
            composable(BottomNavItem.Profile.route) {
                ProfileScreen(
                    currentUser = currentUser,
                    onLogout = { viewModel.logout() }
                )
            }
        }
    }
}
