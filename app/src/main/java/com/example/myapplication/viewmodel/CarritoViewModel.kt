package com.example.myapplication.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

// Modelo de datos para cada producto en el carro
data class ItemCarrito(
    val id: Int,
    val nombre: String,
    val precio: Int,
    var cantidad: Int
)

class CarritoViewModel : ViewModel() {

    // Lista observable: Si agregamos o quitamos algo, la pantalla se actualiza sola
    val itemsCarrito = mutableStateListOf<ItemCarrito>()

    init {
        // Agregamos un par de productos de prueba para que el carro no esté vacío en la demo
        itemsCarrito.add(ItemCarrito(1, "Our Sour Limón", 4990, 1))
        itemsCarrito.add(ItemCarrito(2, "Hielo Cristalino", 2490, 1))
    }

    // Función para eliminar un producto de la lista
    fun eliminarProducto(id: Int) {
        itemsCarrito.removeAll { it.id == id }
    }

    // Calcula el total a pagar multiplicando precio por cantidad
    fun calcularTotal(): Int {
        return itemsCarrito.sumOf { it.precio * it.cantidad }
    }
}