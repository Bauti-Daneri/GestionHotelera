package com.example.gestionhotelera.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onNavigateToAdmin: () -> Unit,
    onNavigateToHousekeeping: () -> Unit,
    onNavigateToMaintenance: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "HotelOps Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(onClick = onNavigateToAdmin, modifier = Modifier.fillMaxWidth()) {
            Text("Login como Administrador")
        }
        Spacer(modifier = Modifier.height(8.dp))
        
        Button(onClick = onNavigateToHousekeeping, modifier = Modifier.fillMaxWidth()) {
            Text("Login como Housekeeping")
        }
        Spacer(modifier = Modifier.height(8.dp))
        
        Button(onClick = onNavigateToMaintenance, modifier = Modifier.fillMaxWidth()) {
            Text("Login como Mantenimiento")
        }
    }
}
