package com.example.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Usuario

class LoginViewModel : ViewModel() {

    // Los dos usuarios permitidos
    private val usuarioAdmin = Usuario(mail = "admin", pass = "123")
    private val usuarioConsumidor = Usuario(mail = "user", pass = "123")

    var mail by mutableStateOf("")
        private set

    var pass by mutableStateOf("")
        private set

    fun cambiarMail(nuevoMail: String) {
        mail = nuevoMail
    }

    fun cambiarPass(nuevaPass: String) {
        pass = nuevaPass
    }

    // 👇 Esta función es la que el MainActivity está esperando
    fun obtenerRol(): String? {
        if (mail == usuarioAdmin.mail && pass == usuarioAdmin.pass) {
            return "admin"
        }
        if (mail == usuarioConsumidor.mail && pass == usuarioConsumidor.pass) {
            return "consumidor"
        }
        return null // Si no coincide, no entra
    }
}