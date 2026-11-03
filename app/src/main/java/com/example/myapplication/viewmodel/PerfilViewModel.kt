package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel

data class UsuarioPerfil(
    val nombre: String,
    val correo: String,
    val rol: String
)

class PerfilViewModel : ViewModel() {
    // Simulamos los datos del usuario logueado
    val usuarioActual = UsuarioPerfil(
        nombre = "Arnoldo Ramos",
        correo = "arnoldo@oursour.cl",
        rol = "Consumidor"
    )
}