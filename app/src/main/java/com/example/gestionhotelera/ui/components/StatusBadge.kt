package com.example.gestionhotelera.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestionhotelera.domain.model.RoomStatus
import com.example.gestionhotelera.domain.model.TicketStatus
import com.example.gestionhotelera.domain.model.OrderStatus
import com.example.gestionhotelera.ui.theme.DestructiveRed
import com.example.gestionhotelera.ui.theme.PrimaryBlue
import com.example.gestionhotelera.ui.theme.SuccessGreen
import com.example.gestionhotelera.ui.theme.WarningOrange

@Composable
fun StatusBadge(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = color.copy(alpha = 0.1f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text.uppercase(),
            color = color,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp
        )
    }
}

@Composable
fun RoomStatusBadge(status: RoomStatus) {
    val (text, color) = when (status) {
        RoomStatus.CLEAN -> "Limpia" to SuccessGreen
        RoomStatus.DIRTY -> "Sucia" to DestructiveRed
        RoomStatus.IN_PROGRESS -> "En Proceso" to PrimaryBlue
        RoomStatus.MAINTENANCE -> "Mantenimiento" to WarningOrange
        RoomStatus.OCCUPIED -> "Ocupada" to Color.Gray
    }
    StatusBadge(text = text, color = color)
}

@Composable
fun TicketStatusBadge(status: TicketStatus) {
    val (text, color) = when (status) {
        TicketStatus.OPEN -> "Abierto" to DestructiveRed
        TicketStatus.IN_PROGRESS -> "En Curso" to PrimaryBlue
        TicketStatus.RESOLVED -> "Resuelto" to SuccessGreen
        TicketStatus.CLOSED -> "Cerrado" to Color.Gray
    }
    StatusBadge(text = text, color = color)
}

@Composable
fun OrderStatusBadge(status: OrderStatus) {
    val (text, color) = when (status) {
        OrderStatus.PENDING -> "Pendiente" to WarningOrange
        OrderStatus.PREPARING -> "Preparando" to PrimaryBlue
        OrderStatus.DELIVERED -> "Entregado" to SuccessGreen
    }
    StatusBadge(text = text, color = color)
}
