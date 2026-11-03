package com.example.myapplication.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Notificacion(
    val titulo: String,
    val mensaje: String,
    val fecha: String
)

class NotificacionesViewModel : ViewModel() {
    val listaNotificaciones = mutableStateListOf(
        Notificacion("Pedido Confirmado", "Tu pedido ha sido recibido.", "Hace 2 horas"),
        Notificacion("En Preparación", "Estamos alistando tus productos Our Sour.", "Hace 1 hora"),
        Notificacion("En Camino", "El repartidor va hacia tu dirección.", "Hace 10 min")
    )
}