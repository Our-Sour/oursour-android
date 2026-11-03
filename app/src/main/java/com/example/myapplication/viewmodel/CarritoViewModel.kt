package com.example.myapplication.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class ItemCarrito(
    val id: Int,
    val nombre: String,
    val precio: Int,
    var cantidad: Int
)

class CarritoViewModel : ViewModel() {

    // Tus productos de prueba actualizados
    val itemsCarrito = mutableStateListOf(
        ItemCarrito(1, "Our Sour Limón", 5990, 1),
        ItemCarrito(2, "Our Sour Maqui", 5990, 1),
        ItemCarrito(3, "Our Sour Merkén", 5990, 1)
    )

    // 👇 NUEVAS FUNCIONES
    fun aumentarCantidad(id: Int) {
        val item = itemsCarrito.find { it.id == id }
        item?.let { it.cantidad++ }
    }

    fun disminuirCantidad(id: Int) {
        val item = itemsCarrito.find { it.id == id }
        if (item != null && item.cantidad > 1) {
            item.cantidad--
        }
    }

    fun eliminarProducto(id: Int) {
        itemsCarrito.removeAll { it.id == id }
    }

    // Cálculo del subtotal de productos
    fun calcularSubtotal(): Int {
        return itemsCarrito.sumOf { it.precio * it.cantidad }
    }

    // Envío fijo simulado
    fun calcularEnvio(): Int {
        return if (itemsCarrito.isEmpty()) 0 else 2990
    }

    // Total final (Subtotal + Envío)
    fun calcularTotal(): Int {
        return calcularSubtotal() + calcularEnvio()
    }
}