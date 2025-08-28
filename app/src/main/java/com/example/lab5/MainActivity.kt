/*  Alejandra Avilés - 24722
*   Curso: Programación de Plataformas Móviles
*   Fecha de entrega: 27 de agosto
*   Descripción: App que permite añadir fotos desde el teléfono a una gradilla (temporalmente) en modo oscuro/claro*/

package com.example.lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lab5.ui.theme.Lab5Theme
import com.example.lab5.ui.theme.PhotoGalleryScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5Theme {
                PhotoGalleryScreen()
            }
        }
    }
}