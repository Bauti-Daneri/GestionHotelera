package com.example.gestionhotelera.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import com.example.gestionhotelera.domain.model.CURRENT_DEMO_ROLE
import com.example.gestionhotelera.domain.model.DemoRole
import com.example.gestionhotelera.ui.admin.AdminScreen
import com.example.gestionhotelera.ui.components.RoleBasedBottomNavigation
import com.example.gestionhotelera.ui.housekeeping.HousekeepingScreen
import com.example.gestionhotelera.ui.maintenance.MaintenanceScreen
import com.example.gestionhotelera.ui.roomservice.RoomServiceScreen
import com.example.gestionhotelera.ui.profile.ProfileScreen
import com.example.gestionhotelera.ui.login.LoginScreen
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    // State to trigger recomposition when role changes
    var role by remember { mutableStateOf(CURRENT_DEMO_ROLE) }

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Login.route && role != null) {
                RoleBasedBottomNavigation(navController = navController, role = role!!)
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Login.route) {
                LoginScreen(
                    onNavigateToAdmin = {
                        CURRENT_DEMO_ROLE = DemoRole.ADMIN
                        role = DemoRole.ADMIN
                        navController.navigate(Screen.AdminHome.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    onNavigateToHousekeeping = {
                        CURRENT_DEMO_ROLE = DemoRole.HOUSEKEEPING
                        role = DemoRole.HOUSEKEEPING
                        navController.navigate(Screen.HousekeepingHome.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    onNavigateToMaintenance = {
                        CURRENT_DEMO_ROLE = DemoRole.MAINTENANCE
                        role = DemoRole.MAINTENANCE
                        navController.navigate(Screen.MaintenanceHome.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.AdminHome.route) {
                AdminScreen()
            }
            composable(Screen.HousekeepingHome.route) {
                HousekeepingScreen()
            }
            composable(Screen.MaintenanceHome.route) {
                MaintenanceScreen()
            }
            composable(Screen.RoomService.route) {
                RoomServiceScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen(onLogout = {
                    CURRENT_DEMO_ROLE = null
                    role = null
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                })
            }
        }
    }
}
