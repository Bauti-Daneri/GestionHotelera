package com.example.gestionhotelera.ui.admin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gestionhotelera.domain.model.Room
import com.example.gestionhotelera.domain.model.User
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignmentBottomSheet(
    room: Room,
    allUsers: List<User>,
    assignedUsersFlow: Flow<List<User>>,
    onDismiss: () -> Unit,
    onAssign: (String) -> Unit,
    onRemove: (String) -> Unit
) {
    val assignedUsers by assignedUsersFlow.collectAsState(initial = emptyList())

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Asignar Personal - Habitación ${room.number}", style = MaterialTheme.typography.titleLarge)
            
            Text("Personal Asignado (${assignedUsers.size}/3)", style = MaterialTheme.typography.labelLarge)
            
            assignedUsers.forEach { user ->
                ListItem(
                    headlineContent = { Text(user.name) },
                    supportingContent = { Text(user.email) },
                    trailingContent = {
                        IconButton(onClick = { onRemove(user.id) }) {
                            Icon(Icons.Rounded.Delete, contentDescription = "Quitar", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                )
            }

            if (assignedUsers.size < 3) {
                HorizontalDivider()
                Text("Asignar Empleado", style = MaterialTheme.typography.labelLarge)
                val availableUsers = allUsers.filter { user -> assignedUsers.none { it.id == user.id } }
                
                if (availableUsers.isEmpty()) {
                    Text("No hay más personal de limpieza disponible", style = MaterialTheme.typography.bodyMedium)
                } else {
                    LazyColumn(modifier = Modifier.heightIn(max = 200.dp)) {
                        items(availableUsers) { user ->
                            ListItem(
                                headlineContent = { Text(user.name) },
                                modifier = Modifier.clickable { onAssign(user.id) },
                                trailingContent = { Icon(Icons.Rounded.Add, contentDescription = null) }
                            )
                        }
                    }
                }
            } else {
                Text("Límite de asignación alcanzado", color = MaterialTheme.colorScheme.error)
            }

            Button(
                onClick = onDismiss,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
