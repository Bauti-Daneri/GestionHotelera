package com.example.gestionhotelera.ui.components

import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.camera.core.Preview
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    scaleType: PreviewView.ScaleType = PreviewView.ScaleType.FILL_CENTER,
    onUseCase: (Preview) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val previewView = PreviewView(context).apply {
                this.scaleType = scaleType
            }
            
            val preview = Preview.Builder().build()
            preview.surfaceProvider = previewView.surfaceProvider
            onUseCase(preview)
            
            previewView
        }
    )
}
