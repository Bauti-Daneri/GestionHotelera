package com.example.gestionhotelera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gestionhotelera.ui.theme.GestionHoteleraTheme
import com.example.gestionhotelera.ui.view.DetailScreen
import com.example.gestionhotelera.ui.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GestionHoteleraTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(onModuleClick = { moduleTitle ->
                navController.navigate("detail/$moduleTitle")
            })
        }
        composable(
            route = "detail/{moduleName}",
            arguments = listOf(navArgument("moduleName") { type = NavType.StringType })
        ) { backStackEntry ->
            val moduleName = backStackEntry.arguments?.getString("moduleName") ?: ""
            DetailScreen(
                moduleName = moduleName,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
