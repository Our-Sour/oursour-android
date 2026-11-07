package com.example.myapplication.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.AdminPedidoRepository
import com.example.myapplication.data.PedidoAdmin
import kotlinx.coroutines.launch

class AdminPedidosViewModel : ViewModel() {
    private val repository = AdminPedidoRepository()

    var isLoading by mutableStateOf(false)
        private set
    var listaPedidos by mutableStateOf<List<PedidoAdmin>>(emptyList())
        private set

    init {
        cargarPedidos()
    }

    fun cargarPedidos() {
        viewModelScope.launch {
            isLoading = true
            listaPedidos = repository.obtenerPedidos()
            isLoading = false
        }
    }

    fun cambiarEstado(id: String) {
        viewModelScope.launch {
            repository.cambiarEstado(id)
            cargarPedidos() // Recargamos la lista para ver el cambio de estado
        }
    }
}