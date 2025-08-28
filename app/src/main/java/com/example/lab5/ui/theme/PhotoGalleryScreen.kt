package com.example.lab5.ui.theme

import android.Manifest
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab5.PhotoItem
import com.example.lab5.PhotoViewModel
import java.io.File

/* Crear de URI seguro (guardado de la foto tomado) */
fun Context.createImageUri(): Uri {
    val image = File(filesDir, "camera-photos-app/${System.currentTimeMillis()}.png")
    image.parentFile?.mkdirs()
    return FileProvider.getUriForFile(
        this,
        "${applicationContext.packageName}.provider",
        image
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoGalleryScreen(viewModel: PhotoViewModel = viewModel()) {
    val photos by viewModel.photos.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // Logística para la Cámara
    var tempImageUri by remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                tempImageUri?.let {
                    viewModel.addPhoto(it, "Foto de cámara ${photos.size + 1}") // ID de la foto
                }
            }
        }
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                // Si el permiso es concedido, creación del URI y lanzamiento de la cámara
                val newImageUri = context.createImageUri()
                tempImageUri = newImageUri
                cameraLauncher.launch(newImageUri)
            }
        }
    )

    // Logística para el Photo Picker
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? ->
            uri?.let {
                viewModel.addPhoto(it, "Foto de galería ${photos.size + 1}") // ID de la foto
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fotos") },
                actions = {
                    // Botón para la cámara
                    IconButton(onClick = { permissionLauncher.launch(Manifest.permission.CAMERA) }) {
                        Icon(Icons.Default.CameraAlt, contentDescription = "Tomar foto")
                    }
                }
            )
        },
        floatingActionButton = {
            // Botón para añadir foto desde la galería del teléfono
            FloatingActionButton(onClick = {
                photoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Añadir foto desde galería")
            }
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            modifier = Modifier.padding(innerPadding),
            columns = GridCells.Fixed(2)
        ) {
            items(photos) { photo ->
                PhotoItem(photo = photo)
            }
        }
    }
}