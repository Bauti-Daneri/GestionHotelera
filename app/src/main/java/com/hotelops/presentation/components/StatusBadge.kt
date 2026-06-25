package com.hotelops.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hotelops.domain.model.OrderStatus
import com.hotelops.domain.model.RoomStatus
import com.hotelops.domain.model.TicketStatus
import com.hotelops.presentation.theme.*

@Composable
fun RoomStatusBadge(status: RoomStatus, modifier: Modifier = Modifier) {
    val (color, textColor) = when (status) {
        RoomStatus.DIRTY -> ColorDirty to Color.White
        RoomStatus.CLEANING -> ColorInProgress to Color.White
        RoomStatus.CLEAN -> ColorClean to Color.White
        RoomStatus.AVAILABLE -> ColorAvailable to Color.White
        else -> MaterialTheme.colorScheme.outline to MaterialTheme.colorScheme.onSurfaceVariant
    }
    StatusBadge(text = status.toDisplayName(), containerColor = color, contentColor = textColor, modifier = modifier)
}

@Composable
fun TicketStatusBadge(status: TicketStatus, modifier: Modifier = Modifier) {
    val (color, textColor) = when (status) {
        TicketStatus.PENDING -> ColorDirty to Color.White
        TicketStatus.IN_PROGRESS -> ColorInProgress to Color.White
        TicketStatus.COMPLETED -> ColorClean to Color.White
    }
    StatusBadge(text = status.toDisplayName(), containerColor = color, contentColor = textColor, modifier = modifier)
}

@Composable
fun OrderStatusBadge(status: OrderStatus, modifier: Modifier = Modifier) {
    val (color, textColor) = when (status) {
        OrderStatus.PENDING -> ColorInProgress to Color.White
        OrderStatus.PREPARING -> ColorAvailable to Color.White
        OrderStatus.READY -> ColorAvailable to Color.White
        OrderStatus.DELIVERED -> ColorClean to Color.White
    }
    StatusBadge(text = status.toDisplayName(), containerColor = color, contentColor = textColor, modifier = modifier)
}

@Composable
fun StatusBadge(
    text: String,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        color = containerColor,
        contentColor = contentColor,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
        )
    }
}

fun RoomStatus.toDisplayName() = when (this) {
    RoomStatus.DIRTY -> "Sucia"
    RoomStatus.CLEANING -> "En Proceso"
    RoomStatus.CLEAN -> "Limpia"
    RoomStatus.INSPECTING -> "Inspeccionando"
    RoomStatus.OUT_OF_SERVICE -> "F/S"
    RoomStatus.AVAILABLE -> "Disponible"
}

fun TicketStatus.toDisplayName() = when (this) {
    TicketStatus.PENDING -> "Abierto"
    TicketStatus.IN_PROGRESS -> "En Progreso"
    TicketStatus.COMPLETED -> "Cerrado"
}

fun OrderStatus.toDisplayName() = when (this) {
    OrderStatus.PENDING -> "Pendiente"
    OrderStatus.PREPARING -> "Preparando"
    OrderStatus.READY -> "Listo"
    OrderStatus.DELIVERED -> "Entregado"
}
