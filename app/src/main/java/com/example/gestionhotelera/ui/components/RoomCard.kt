package com.example.gestionhotelera.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.MeetingRoom
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gestionhotelera.domain.model.Room
import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.ui.theme.PrimaryBlue

@Composable
fun RoomCard(
    room: Room,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onDelete: (() -> Unit)? = null,
    assignedEmployees: List<User> = emptyList()
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = PrimaryBlue.copy(alpha = 0.1f),
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.MeetingRoom,
                        contentDescription = null,
                        modifier = Modifier.padding(12.dp),
                        tint = PrimaryBlue
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Habitación ${room.number}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Piso ${room.number.firstOrNull() ?: '?'}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RoomStatusBadge(status = room.status)
                    if (onDelete != null) {
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(onClick = onDelete) {
                            Icon(
                                imageVector = Icons.Rounded.Delete,
                                contentDescription = "Eliminar Habitación",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }

            if (assignedEmployees.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(modifier = Modifier.alpha(0.5f))
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Empleados asignados:",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = assignedEmployees.joinToString(", ") { it.name },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
