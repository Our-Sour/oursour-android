package com.example.myapplication.data

import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.delay

// Modelo específico para la vista del Admin (con más campos)
data class ProductoAdmin(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Int,
    val stock: Int,
    val fechaCreacion: String
)

class AdminProductoRepository {
    // Simulamos la base de datos
    private val _productos = mutableStateListOf(
        ProductoAdmin(1, "Our Sour Limón", "Mix cítrico para cócteles", 5990, 50, "01/11/2026"),
        ProductoAdmin(2, "Our Sour Merkén", "Toque ahumado y picante", 5990, 30, "02/11/2026"),
        ProductoAdmin(3, "Our Sour Maqui", "Mix frutal premium", 5990, 40, "03/11/2026")
    )
    val productos: List<ProductoAdmin> get() = _productos

    // Simulamos GET /api/productos/
    suspend fun obtenerProductos(): List<ProductoAdmin> {
        delay(800)
        return _productos
    }

    // Simulamos POST /api/productos/
    suspend fun agregarProducto(nombre: String, descripcion: String, precio: Int) {
        delay(500)
        val nuevoId = if (_productos.isEmpty()) 1 else _productos.maxOf { it.id } + 1
        _productos.add(ProductoAdmin(nuevoId, nombre, descripcion, precio, 10, "07/11/2026"))
    }

    // Simulamos DELETE /api/productos/{id}/
    suspend fun eliminarProducto(id: Int) {
        delay(500)
        _productos.removeAll { it.id == id }
    }
}