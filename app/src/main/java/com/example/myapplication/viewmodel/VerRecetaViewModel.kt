package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel

data class DetalleReceta(
    val titulo: String,
    val ingredientes: List<String>,
    val pasos: List<String>
)

class VerRecetaViewModel : ViewModel() {

    // Esta función genera los datos dinámicamente según el título que le pasemos
    fun obtenerReceta(titulo: String): DetalleReceta {
        return if (titulo.contains("Mojito", ignoreCase = true)) {
            DetalleReceta(
                titulo = "Mojito Cubano",
                ingredientes = listOf("2 oz Ron Blanco", "Menta fresca", "Lima", "Soda", "Jarabe de goma"),
                pasos = listOf("Macera la menta.", "Agrega ron e hielo.", "Completa con soda.")
            )
        } else {
            // Por defecto, devuelve el Pisco Sour
            DetalleReceta(
                titulo = "Pisco Sour Clásico",
                ingredientes = listOf("60 ml Pisco", "30 ml Jugo de Limón", "20 ml Jarabe de Goma", "Hielo"),
                pasos = listOf("Enfría la coctelera.", "Agrega los ingredientes.", "Agita y sirve.")
            )
        }
    }
}