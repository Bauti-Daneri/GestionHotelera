package com.example.gestionhotelera.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun ImageViewerScreen(
    imageUrl: String,
    onClose: () -> Unit
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    val animatedScale by animateFloatAsState(targetValue = scale, label = "scale")
    val animatedOffset by animateOffsetAsState(targetValue = offset, label = "offset")

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale = (scale * zoom).coerceIn(1f, 5f)
                        
                        if (scale > 1f) {
                            val maxOffsetX = (width * (scale - 1f) / 2f)
                            val maxOffsetY = (height * (scale - 1f) / 2f)
                            
                            val newOffsetX = offset.x + pan.x * scale
                            val newOffsetY = offset.y + pan.y * scale
                            
                            offset = Offset(
                                x = newOffsetX.coerceIn(-maxOffsetX, maxOffsetX),
                                y = newOffsetY.coerceIn(-maxOffsetY, maxOffsetY)
                            )
                        } else {
                            offset = Offset.Zero
                        }
                    }
                }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            if (scale > 1f) {
                                scale = 1f
                                offset = Offset.Zero
                            } else {
                                scale = 2f
                            }
                        }
                    )
                }
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Full Screen Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .graphicsLayer {
                        scaleX = animatedScale
                        scaleY = animatedScale
                        translationX = animatedOffset.x
                        translationY = animatedOffset.y
                    }
            )
        }

        IconButton(
            onClick = onClose,
            modifier = Modifier
                .statusBarsPadding()
                .padding(16.dp)
                .align(Alignment.TopEnd)
                .background(Color.Black.copy(alpha = 0.5f), shape = MaterialTheme.shapes.small)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Cerrar",
                tint = Color.White
            )
        }
    }
}
