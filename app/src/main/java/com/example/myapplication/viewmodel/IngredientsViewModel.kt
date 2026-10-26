package com.example.myapplication.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class IngredientsViewModel: ViewModel() {

    // 1. Definimos la lista de ingredientes agrupados (simulando lo que sería la BD)
    // Usamos data class para tener el nombre y si está seleccionado

    data class IngredienteItem(
        val nombre: String,
        val categoria: String,
        val emoji: String,
        val seleccionado: Boolean = false
    )

    // 2. La lista completa observable.
    // Al usar mutableStateListOf, cualquier cambio en la lista (como marcar un checkbox)
    // redibujará la pantalla automáticamente.
    val listaIngredientes = mutableStateListOf(
        IngredienteItem("Pisco", "Destilados", "🥃"),
        IngredienteItem("Vodka", "Destilados", "🍶"),
        IngredienteItem("Ron Blanco", "Destilados", "🥃"),
        IngredienteItem("Gin", "Destilados", "🌿"),
        IngredienteItem("Tequila", "Destilados", "🌵"),
        IngredienteItem("Limón", "Frutas", "🍋"),
        IngredienteItem("Jarabe de Goma", "Jugos", "🍯"),
        IngredienteItem("Hielo", "Jugos", "🧊"),

    )
    // 3. Función que se ejecuta cuando el usuario toca un ingrediente
    fun toggleIngrediente(index: Int) {
        // Copiamos el item actual
        val item = listaIngredientes[index]
        // Lo reemplazamos por una copia con el estado "seleccionado" invertido (si era true, ahora false)
        listaIngredientes[index] = item.copy(seleccionado = !item.seleccionado)
    }

}