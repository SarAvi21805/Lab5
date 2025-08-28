package com.example.lab5

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PhotoItem(photo: Photo) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = photo.uri,
            // Accediendo al título de la foto
            contentDescription = photo.title,
            modifier = Modifier
                .fillMaxWidth()
                // Para cuadrícula uniforme
                .aspectRatio(1f),
            // Escalado (Imagen ocupa el espacio establecido)
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_image_placeholder) // Imagen predeterminada en caso una imagen pese mucho
        )

        // Título debajo de la foto
        Text(
            text = photo.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
    }
}