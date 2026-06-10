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
import com.example.gestionhotelera.ui.camera.CameraScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AppNavigation(
    mainViewModel: com.example.gestionhotelera.MainViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    // State to trigger recomposition when role changes
    var role by remember { mutableStateOf(CURRENT_DEMO_ROLE) }
    val notificationCounts by mainViewModel.notificationCounts.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Login.route && role != null) {
                RoleBasedBottomNavigation(
                    navController = navController, 
                    role = role!!,
                    notificationCounts = notificationCounts
                )
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
                HousekeepingScreen(
                    onNavigateToImageViewer = { url ->
                        navController.navigate(Screen.ImageViewer.createRoute(url))
                    }
                )
            }
            composable(Screen.MaintenanceHome.route) {
                MaintenanceScreen(
                    onNavigateToImageViewer = { url ->
                        navController.navigate(Screen.ImageViewer.createRoute(url))
                    }
                )
            }
            composable(Screen.RoomService.route) {
                RoomServiceScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    onLogout = {
                        CURRENT_DEMO_ROLE = null
                        role = null
                        navController.navigate(Screen.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    onNavigateToCamera = {
                        navController.navigate(Screen.CameraTest.route)
                    }
                )
            }
            composable(Screen.CameraTest.route) {
                CameraScreen(
                    onImageCaptured = { uri -> 
                        // Por ahora solo se muestra en el CameraScreen
                    },
                    onError = { exc ->
                        // Manejar error
                    }
                )
            }
            composable(
                route = Screen.ImageViewer.route,
                arguments = listOf(
                    androidx.navigation.navArgument("imageUrl") { type = androidx.navigation.NavType.StringType }
                )
            ) { backStackEntry ->
                val imageUrl = backStackEntry.arguments?.getString("imageUrl") ?: ""
                com.example.gestionhotelera.ui.components.ImageViewerScreen(
                    imageUrl = imageUrl,
                    onClose = { navController.popBackStack() }
                )
            }
        }
    }
}
