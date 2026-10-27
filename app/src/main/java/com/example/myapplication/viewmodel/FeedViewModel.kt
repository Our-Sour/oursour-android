package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel

// Creamos una estructura para nuestra receta
data class RecetaMock(
    val id: Int,
    val titulo: String,
    val tipo: String, // Cóctel o Mocktail
    val ingredientes: String,
    val emoji: String,
    val colorFondo: Long // Para simular el gradiente de la imagen
)

class FeedViewModel : ViewModel() {

    // Lista de recetas de prueba (Mock)
    val recetas = listOf(
        RecetaMock(1, "Pisco Sour Clásico", "Cóctel", "Pisco, Limón, Jarabe, Hielo", "", 0xFFFFF7F0),
        RecetaMock(2, "Mojito Cubano", "Cóctel", "Ron, Menta, Lima, Soda", "", 0xFFFFF7F0),
        RecetaMock(3, "Maracuyá Sunrise", "Mocktail", "Maracuyá, Naranja, Soda", "", 0xFFFFF7F0)
    )
}