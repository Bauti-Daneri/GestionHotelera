package com.hotelops.presentation.camera;

import android.content.Context;
import android.net.Uri;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.core.content.ContextCompat;
import timber.log.Timber;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executor;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0007\u001a4\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003H\u0002\u00a8\u0006\u000e"}, d2 = {"CameraScreen", "", "onImageCaptured", "Lkotlin/Function1;", "Landroid/net/Uri;", "onDismiss", "Lkotlin/Function0;", "takePhoto", "context", "Landroid/content/Context;", "imageCapture", "Landroidx/camera/core/ImageCapture;", "executor", "Ljava/util/concurrent/Executor;", "app_debug"})
public final class CameraScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void CameraScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super android.net.Uri, kotlin.Unit> onImageCaptured, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    private static final void takePhoto(android.content.Context context, androidx.camera.core.ImageCapture imageCapture, java.util.concurrent.Executor executor, kotlin.jvm.functions.Function1<? super android.net.Uri, kotlin.Unit> onImageCaptured) {
    }
}