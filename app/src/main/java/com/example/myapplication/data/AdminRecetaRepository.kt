package com.example.myapplication.data

import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.delay

data class RecetaAdmin(
    val id: Int,
    val titulo: String,
    val tiempo: String,
    val dificultad: String,
    val videoUrl: String? // Null si no tiene video aún
)

class AdminRecetaRepository {
    private val _recetas = mutableStateListOf(
        RecetaAdmin(1, "Pisco Sour Clásico", "5 min", "Fácil", "https://youtu.be/ejemplo1"),
        RecetaAdmin(2, "Mojito Cubano", "7 min", "Media", "https://youtu.be/ejemplo2"),
        RecetaAdmin(3, "Maracuyá Sunrise", "4 min", "Fácil", null)
    )

    // Simula GET /api/recetas/
    suspend fun obtenerRecetas(): List<RecetaAdmin> {
        delay(800)
        return _recetas
    }

    // Simula POST /api/recetas/
    suspend fun agregarReceta(titulo: String, tiempo: String, dificultad: String, videoUrl: String?) {
        delay(500)
        val nuevoId = if (_recetas.isEmpty()) 1 else _recetas.maxOf { it.id } + 1
        _recetas.add(RecetaAdmin(nuevoId, titulo, tiempo, dificultad, videoUrl))
    }

    // Simula DELETE /api/recetas/{id}/
    suspend fun eliminarReceta(id: Int) {
        delay(500)
        _recetas.removeAll { it.id == id }
    }
}