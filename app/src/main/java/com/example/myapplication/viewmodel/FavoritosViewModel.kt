package com.example.myapplication.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

// Modelo de datos simple para las recetas favoritas
data class RecetaFavorita(
    val id: Int,
    val titulo: String,
    val emoji: String
)

class FavoritosViewModel : ViewModel() {
    // Lista observable que guarda las recetas
    val recetasFavoritas = mutableStateListOf(
        RecetaFavorita(1, "Pisco Sour Clásico", ""),
        RecetaFavorita(2, "Mojito Cubano", "")
    )

    // Función por si quieres quitar una receta en el futuro
    fun eliminarFavorito(id: Int) {
        recetasFavoritas.removeAll { it.id == id }
    }
}