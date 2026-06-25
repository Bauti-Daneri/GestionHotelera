package com.hotelops.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hotelops.domain.model.User
import com.hotelops.presentation.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    currentUser: User?,
    onLogout: () -> Unit
) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Perfil", 
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Primary
                        )
                    ) 
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Background)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Header Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            currentUser?.name?.take(2)?.uppercase() ?: "??",
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        currentUser?.name ?: "Usuario",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Surface(
                        color = ColorAdmin,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(
                            currentUser?.role?.name ?: "ROL",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Hotel Info Card
            InfoSectionCard(title = "Hotel") {
                ProfileRow(Icons.Default.Apartment, "Establecimiento", "Hotel Plaza Central")
                Divider(color = SurfaceVariant, modifier = Modifier.padding(vertical = 12.dp))
                ProfileRow(Icons.Default.Badge, "ID de Hotel", "HOTEL-DEMO-001")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Personal Info Card
            InfoSectionCard(title = "Información Personal") {
                ProfileRow(Icons.Default.Email, "Email", currentUser?.email ?: "")
                Divider(color = SurfaceVariant, modifier = Modifier.padding(vertical = 12.dp))
                ProfileRow(Icons.Default.Phone, "Teléfono", "+34 612 345 678")
                Divider(color = SurfaceVariant, modifier = Modifier.padding(vertical = 12.dp))
                ProfileRow(Icons.Default.Business, "Departamento", currentUser?.department ?: "")
                Divider(color = SurfaceVariant, modifier = Modifier.padding(vertical = 12.dp))
                ProfileRow(Icons.Default.AccessTime, "Horario", "Turno Mañana (6:00 - 14:00)")
                Divider(color = SurfaceVariant, modifier = Modifier.padding(vertical = 12.dp))
                ProfileRow(Icons.Default.Person, "ID Empleado", currentUser?.employeeId ?: "")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { showLogoutDialog = true },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Error)
            ) {
                Icon(Icons.Default.Logout, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cerrar Sesión")
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Confirmar") },
            text = { Text("¿Estás seguro de que deseas cerrar sesión?") },
            confirmButton = {
                TextButton(onClick = onLogout) { Text("Cerrar Sesión", color = Error) }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) { Text("Cancelar") }
            }
        )
    }
}

@Composable
fun InfoSectionCard(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(title, fontWeight = FontWeight.Bold, color = Primary, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(20.dp))
            content()
        }
    }
}

@Composable
private fun ProfileRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
            color = SurfaceVariant,
            shape = RoundedCornerShape(10.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp), tint = Primary)
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(label, fontSize = 12.sp, color = OnSurfaceVariant)
            Text(value, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = OnSurface)
        }
    }
}
