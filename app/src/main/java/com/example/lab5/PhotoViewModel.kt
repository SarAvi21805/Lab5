package com.example.lab5

import android.net.Uri
import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch

data class Photo(val uri: Uri, val title: String)

class PhotoViewModel : ViewModel() {
    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    val photos: StateFlow<List<Photo>> = _photos

    fun addPhoto(uri: Uri, title: String) {
        val newPhoto = Photo(uri, title)
        _photos.value = _photos.value + newPhoto
    }

/*    fun pickPhoto(launcher: ActivityResultLauncher<Uri>) {
        launcher.launch(null)
    }*/
}