package com.example.gestionhotelera.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.gestionhotelera.domain.model.*
import com.example.gestionhotelera.ui.theme.PrimaryBlue

@Composable
fun MaintenanceTicketCard(
    ticket: MaintenanceTicket,
    onStatusChange: (TicketStatus) -> Unit,
    modifier: Modifier = Modifier,
    onImageClick: (String) -> Unit = {}
) {
    // Mapeo de etiquetas visibles para ahorrar espacio
    val categoryLabel = ticket.category.displayName.uppercase()

    // Determinar visibilidad de botones
    val showInProgress = ticket.status == TicketStatus.OPEN
    val showResolve = ticket.status != TicketStatus.RESOLVED && ticket.status != TicketStatus.CLOSED

    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // 1. Fila superior: Ícono, Habitación y Badge
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
            
            // 2. Descripción del problema
            Text(
                text = ticket.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            // Imagen adjunta si existe
            ticket.imageUrl?.let { uriString ->
                Spacer(modifier = Modifier.height(12.dp))
                AsyncImage(
                    model = uriString,
                    contentDescription = "Evidencia del problema",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .clickable { onImageClick(uriString) },
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // 3. Chip de categoría (En su propia línea para no competir con botones)
            AssistChip(
                onClick = { },
                label = { 
                    Text(
                        text = categoryLabel,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    ) 
                },
                enabled = false,
                modifier = Modifier.height(32.dp)
            )
            
            // 4. Fila inferior de acciones
            if (showInProgress || showResolve) {
                Spacer(modifier = Modifier.height(12.dp))
                
                BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                    // Si el ancho es insuficiente para ambos botones en una Row, los apilamos
                    // Usamos un umbral de 300dp para asegurar que entren holgadamente
                    val useColumn = maxWidth < 300.dp && showInProgress && showResolve

                    if (useColumn) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            if (showInProgress) {
                                FilledTonalButton(
                                    onClick = { onStatusChange(TicketStatus.IN_PROGRESS) },
                                    modifier = Modifier.fillMaxWidth(),
                                    contentPadding = PaddingValues(vertical = 8.dp)
                                ) {
                                    Text(
                                        text = "En Proceso", // Etiqueta corta
                                        maxLines = 1,
                                        softWrap = false,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                            if (showResolve) {
                                Button(
                                    onClick = { onStatusChange(TicketStatus.RESOLVED) },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                                    contentPadding = PaddingValues(vertical = 8.dp)
                                ) {
                                    Text(
                                        text = "Resolver",
                                        maxLines = 1,
                                        softWrap = false,
                                        overflow = TextOverflow.Clip
                                    )
                                }
                            }
                        }
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (showInProgress) {
                                FilledTonalButton(
                                    onClick = { onStatusChange(TicketStatus.IN_PROGRESS) },
                                    modifier = if (showResolve) Modifier.weight(1f) else Modifier.widthIn(min = 120.dp),
                                    contentPadding = PaddingValues(horizontal = 8.dp)
                                ) {
                                    Text(
                                        text = "En Proceso",
                                        maxLines = 1,
                                        softWrap = false,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                            if (showResolve) {
                                Button(
                                    onClick = { onStatusChange(TicketStatus.RESOLVED) },
                                    modifier = if (showInProgress) Modifier.weight(1f) else Modifier.widthIn(min = 120.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                                    contentPadding = PaddingValues(horizontal = 16.dp)
                                ) {
                                    Text(
                                        text = "Resolver",
                                        maxLines = 1,
                                        softWrap = false,
                                        overflow = TextOverflow.Clip
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
