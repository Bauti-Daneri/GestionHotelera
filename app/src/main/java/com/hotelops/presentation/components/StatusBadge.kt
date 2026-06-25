package com.hotelops.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hotelops.domain.model.*
import com.hotelops.presentation.theme.*

@Composable
fun RoomStatusBadge(
    status: RoomStatus,
    modifier: Modifier = Modifier
) {
    val (backgroundColor, textColor) = when (status) {
        RoomStatus.DIRTY -> StatusDirty to Color.White
        RoomStatus.CLEANING -> StatusInProgress to Color.White
        RoomStatus.CLEAN -> StatusClean to Color.White
        RoomStatus.INSPECTING -> MaterialTheme.colorScheme.secondary to MaterialTheme.colorScheme.onSecondary
        RoomStatus.OUT_OF_SERVICE -> MaterialTheme.colorScheme.error to MaterialTheme.colorScheme.onError
    }

    StatusBadge(
        text = status.toDisplayName(),
        backgroundColor = backgroundColor,
        textColor = textColor,
        modifier = modifier
    )
}

@Composable
fun TicketStatusBadge(
    status: TicketStatus,
    modifier: Modifier = Modifier
) {
    val (backgroundColor, textColor) = when (status) {
        TicketStatus.PENDING -> MaterialTheme.colorScheme.errorContainer to MaterialTheme.colorScheme.onErrorContainer
        TicketStatus.IN_PROGRESS -> StatusInProgress to Color.White
        TicketStatus.COMPLETED -> StatusClean to Color.White
    }

    StatusBadge(
        text = status.toDisplayName(),
        backgroundColor = backgroundColor,
        textColor = textColor,
        modifier = modifier
    )
}

@Composable
fun OrderStatusBadge(
    status: OrderStatus,
    modifier: Modifier = Modifier
) {
    val (backgroundColor, textColor) = when (status) {
        OrderStatus.PENDING -> MaterialTheme.colorScheme.errorContainer to MaterialTheme.colorScheme.onErrorContainer
        OrderStatus.PREPARING -> StatusInProgress to Color.White
        OrderStatus.READY -> MaterialTheme.colorScheme.secondaryContainer to MaterialTheme.colorScheme.onSecondaryContainer
        OrderStatus.DELIVERED -> StatusClean to Color.White
    }

    StatusBadge(
        text = status.toDisplayName(),
        backgroundColor = backgroundColor,
        textColor = textColor,
        modifier = modifier
    )
}

@Composable
private fun StatusBadge(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = textColor,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

fun RoomStatus.toDisplayName(): String = when (this) {
    RoomStatus.DIRTY -> "Sucia"
    RoomStatus.CLEANING -> "Limpiando"
    RoomStatus.CLEAN -> "Limpia"
    RoomStatus.INSPECTING -> "Inspeccionando"
    RoomStatus.OUT_OF_SERVICE -> "Fuera de Servicio"
}

fun TicketStatus.toDisplayName(): String = when (this) {
    TicketStatus.PENDING -> "Pendiente"
    TicketStatus.IN_PROGRESS -> "En Progreso"
    TicketStatus.COMPLETED -> "Completado"
}

fun OrderStatus.toDisplayName(): String = when (this) {
    OrderStatus.PENDING -> "Pendiente"
    OrderStatus.PREPARING -> "Preparando"
    OrderStatus.READY -> "Listo"
    OrderStatus.DELIVERED -> "Entregado"
}
