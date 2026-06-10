package com.example.gestionhotelera.ui.camera

import android.Manifest
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil3.compose.AsyncImage
import com.example.gestionhotelera.ui.components.CameraPreview
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Composable
fun CameraScreen(
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    val context = LocalContext.current
    
    var hasCameraPermission by remember { mutableStateOf(false) }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasCameraPermission = isGranted
        if (!isGranted) {
            Toast.makeText(context, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.CAMERA)
    }

    var capturedImageUri by remember { mutableStateOf<Uri?>(null) }
    val imageCapture = remember { ImageCapture.Builder().build() }
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }

    if (hasCameraPermission) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (capturedImageUri == null) {
                CameraPreviewContent(
                    imageCapture = imageCapture,
                    cameraExecutor = cameraExecutor,
                    onImageCaptured = { uri ->
                        capturedImageUri = uri
                        onImageCaptured(uri)
                    },
                    onError = onError
                )
            } else {
                ImagePreviewContent(
                    uri = capturedImageUri!!,
                    onClose = { capturedImageUri = null }
                )
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Se requiere permiso de cámara para esta función.")
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            cameraExecutor.shutdown()
        }
    }
}

@Composable
private fun CameraPreviewContent(
    imageCapture: ImageCapture,
    cameraExecutor: ExecutorService,
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    Box(modifier = Modifier.fillMaxSize()) {
        CameraPreview(
            modifier = Modifier.fillMaxSize(),
            onUseCase = { preview ->
                val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()
                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            CameraSelector.DEFAULT_BACK_CAMERA,
                            preview,
                            imageCapture
                        )
                    } catch (e: Exception) {
                        Log.e("CameraScreen", "Use case binding failed", e)
                    }
                }, ContextCompat.getMainExecutor(context))
            }
        )

        IconButton(
            onClick = {
                takePhoto(
                    imageCapture = imageCapture,
                    executor = cameraExecutor,
                    context = context,
                    onImageCaptured = onImageCaptured,
                    onError = onError
                )
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .size(72.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.White.copy(alpha = 0.5f)
            )
        ) {
            Icon(
                Icons.Filled.Camera,
                contentDescription = "Tomar Foto",
                modifier = Modifier.size(48.dp),
                tint = Color.Black
            )
        }
    }
}

@Composable
private fun ImagePreviewContent(
    uri: Uri,
    onClose: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = uri,
            contentDescription = "Captured Image",
            modifier = Modifier.fillMaxSize()
        )
        
        IconButton(
            onClick = onClose,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color.Black.copy(alpha = 0.5f)
            )
        ) {
            Icon(Icons.Filled.Close, contentDescription = "Cerrar", tint = Color.White)
        }
        
        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Foto capturada con éxito",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = uri.path ?: "",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

private fun takePhoto(
    imageCapture: ImageCapture,
    executor: ExecutorService,
    context: Context,
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    // Crear directorio "photos" en el almacenamiento interno si no existe
    val photosDir = File(context.filesDir, "photos").apply {
        if (!exists()) mkdirs()
    }

    // Generar un nombre único basado en la marca de tiempo
    val name = "IMG_" + SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date()) + ".jpg"
    val photoFile = File(photosDir, name)

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture.takePicture(
        outputOptions,
        executor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                val savedUri = Uri.fromFile(photoFile)
                onImageCaptured(savedUri)
                Log.d("CameraScreen", "Foto guardada en: ${photoFile.absolutePath}")
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e("CameraScreen", "Error al capturar foto", exception)
                onError(exception)
            }
        }
    )
}
