package com.example.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.AdminRecetaRepository
import com.example.myapplication.data.RecetaAdmin
import kotlinx.coroutines.launch

class AdminRecetasViewModel : ViewModel() {
    private val repository = AdminRecetaRepository()

    var isLoading by mutableStateOf(false)
        private set
    var listaRecetas by mutableStateOf<List<RecetaAdmin>>(emptyList())
        private set

    init {
        cargarRecetas()
    }

    fun cargarRecetas() {
        viewModelScope.launch {
            isLoading = true
            listaRecetas = repository.obtenerRecetas()
            isLoading = false
        }
    }

    fun crearReceta(titulo: String, tiempo: String, dificultad: String, videoUrl: String?) {
        viewModelScope.launch {
            repository.agregarReceta(titulo, tiempo, dificultad, videoUrl)
            cargarRecetas()
        }
    }

    fun eliminarReceta(id: Int) {
        viewModelScope.launch {
            repository.eliminarReceta(id)
            cargarRecetas()
        }
    }
}