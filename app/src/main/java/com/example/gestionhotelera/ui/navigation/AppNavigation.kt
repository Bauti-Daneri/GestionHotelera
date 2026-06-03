package com.example.gestionhotelera.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestionhotelera.domain.model.CURRENT_DEMO_ROLE
import com.example.gestionhotelera.domain.model.DemoRole
import com.example.gestionhotelera.ui.admin.AdminScreen
import com.example.gestionhotelera.ui.components.RoleBasedBottomNavigation
import com.example.gestionhotelera.ui.housekeeping.HousekeepingScreen
import com.example.gestionhotelera.ui.maintenance.MaintenanceScreen
import com.example.gestionhotelera.ui.roomservice.RoomServiceScreen
import com.example.gestionhotelera.ui.profile.ProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val role = CURRENT_DEMO_ROLE

    val startDestination = when (role) {
        DemoRole.ADMIN -> Screen.AdminHome.route
        DemoRole.HOUSEKEEPING -> Screen.HousekeepingHome.route
        DemoRole.MAINTENANCE -> Screen.MaintenanceHome.route
    }

    Scaffold(
        bottomBar = {
            RoleBasedBottomNavigation(navController = navController, role = role)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(padding)
        ) {
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
                ProfileScreen()
            }
        }
    }
}
