package com.example.gestionhotelera.ui.admin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.gestionhotelera.domain.model.UserRole
import com.example.gestionhotelera.ui.components.HotelBottomBar

@Composable
fun AdminHomeScreen() {
    Scaffold(
        bottomBar = { HotelBottomBar(role = UserRole.ADMIN, onNavigate = {}) }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
            Text(text = "Panel de Administración")
        }
    }
}
