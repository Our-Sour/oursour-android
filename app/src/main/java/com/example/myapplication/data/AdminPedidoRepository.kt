package com.example.myapplication.data

import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.delay

data class PedidoAdmin(
    val id: String,
    val cliente: String,
    val total: Int,
    var estado: String
)

class AdminPedidoRepository {
    // Simulamos la tabla de la base de datos
    private val _pedidos = mutableStateListOf(
        PedidoAdmin("#1024", "Juan Pérez", 15000, "En preparación"),
        PedidoAdmin("#1025", "María López", 8990, "Solicitado"),
        PedidoAdmin("#1026", "Carlos Ruiz", 12990, "Entregado")
    )

    // Simulamos GET /api/pedidos/
    suspend fun obtenerPedidos(): List<PedidoAdmin> {
        delay(800) // Simula el tiempo de carga de internet
        return _pedidos
    }

    // Simulamos PUT /api/pedidos/{id}/estado/
    suspend fun cambiarEstado(id: String) {
        delay(300)
        val pedido = _pedidos.find { it.id == id }
        if (pedido != null) {
            pedido.estado = when (pedido.estado) {
                "En preparación" -> "Solicitado"
                "Solicitado" -> "Entregado"
                else -> "En preparación"
            }
        }
    }
}