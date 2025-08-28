package com.example.lab5

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Photo(val uri: Uri, val title: String)

/* Para los estados de las fotos */

class PhotoViewModel : ViewModel() {
    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    val photos: StateFlow<List<Photo>> = _photos

    fun addPhoto(uri: Uri, title: String) {
        val newPhoto = Photo(uri, title)
        _photos.value = _photos.value + newPhoto
    }
}