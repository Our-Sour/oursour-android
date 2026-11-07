package com.example.myapplication.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.AdminProductoRepository
import com.example.myapplication.data.ProductoAdmin
import kotlinx.coroutines.launch

class AdminProductosViewModel : ViewModel() {
    private val repository = AdminProductoRepository()

    var isLoading by mutableStateOf(false)
        private set
    var listaProductos by mutableStateOf<List<ProductoAdmin>>(emptyList())
        private set

    init {
        cargarProductos()
    }

    fun cargarProductos() {
        viewModelScope.launch {
            isLoading = true
            listaProductos = repository.obtenerProductos()
            isLoading = false
        }
    }

    fun crearProducto(nombre: String, descripcion: String, precio: Int) {
        viewModelScope.launch {
            repository.agregarProducto(nombre, descripcion, precio)
            cargarProductos()
        }
    }

    fun eliminarProducto(id: Int) {
        viewModelScope.launch {
            repository.eliminarProducto(id)
            cargarProductos()
        }
    }
}