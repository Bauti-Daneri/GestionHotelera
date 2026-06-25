package com.hotelops.presentation.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Main : Screen("main")
    
    // Bottom Nav Screens
    object Admin : Screen("admin")
    object Housekeeping : Screen("housekeeping")
    object Maintenance : Screen("maintenance")
    object RoomService : Screen("room_service")
    object Profile : Screen("profile")
}
