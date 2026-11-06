package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.ProductoRepository

// Modelo
data class Producto(val id: Int, val nombre: String, val precio: Int, val emoji: String)

class ProductosViewModel : ViewModel() {
    // El ViewModel consulta al Repositorio
    private val repository = ProductoRepository()

    // Expone los productos a la vista
    val productos = repository.listaProductos
}