package com.example.myapplication.data

import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.viewmodel.Producto

class ProductoRepository {
    // Simulamos la respuesta de la API
    val listaProductos = mutableStateListOf(
        Producto(1, "Our Sour Limón", 5990, "🍋"),
        Producto(2, "Our Sour Merkén", 5990, "🌶️"),
        Producto(3, "Our Sour Maqui", 5990, "🫐"),
        Producto(4, "Jarabe de Goma", 3490, "🍯"),
        Producto(5, "Hielo Cristalino", 2490, "🧊")
    )
}