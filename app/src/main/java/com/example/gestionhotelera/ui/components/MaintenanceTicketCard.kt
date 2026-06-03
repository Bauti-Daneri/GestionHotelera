package com.example.gestionhotelera.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gestionhotelera.domain.model.MaintenanceTicket
import com.example.gestionhotelera.domain.model.TicketStatus
import com.example.gestionhotelera.ui.theme.PrimaryBlue

@Composable
fun MaintenanceTicketCard(
    ticket: MaintenanceTicket,
    onStatusChange: (TicketStatus) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.Build,
                        contentDescription = null,
                        tint = PrimaryBlue,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Hab. #${ticket.roomId.substringAfter("-")}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
                TicketStatusBadge(status = ticket.status)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = ticket.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AssistChip(
                    onClick = { },
                    label = { Text(ticket.category.name) },
                    enabled = false
                )
                
                Row {
                    if (ticket.status == TicketStatus.OPEN) {
                        TextButton(onClick = { onStatusChange(TicketStatus.IN_PROGRESS) }) {
                            Text("En Proceso")
                        }
                    }
                    if (ticket.status != TicketStatus.RESOLVED && ticket.status != TicketStatus.CLOSED) {
                        Button(
                            onClick = { onStatusChange(TicketStatus.RESOLVED) },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
                        ) {
                            Text("Resolver")
                        }
                    }
                }
            }
        }
    }
}
