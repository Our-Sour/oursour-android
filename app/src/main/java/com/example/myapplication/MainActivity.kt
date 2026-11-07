package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.view.AdminProductosScreen
import com.example.myapplication.view.AdminScreen
import com.example.myapplication.view.LoginScreen
import com.example.myapplication.view.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                var isLoggedIn by remember { mutableStateOf(false) }
                var userRole by remember { mutableStateOf("consumidor") }

                if (isLoggedIn) {
                    if (userRole == "admin") {

                        // 👇 CREAMOS UN NAVEGADOR EXCLUSIVO PARA EL FLUJO DEL ADMIN
                        val adminNavController = rememberNavController()

                        NavHost(
                            navController = adminNavController,
                            startDestination = "admin_home" // El panel principal es el inicio
                        ) {
                            // 1. Pantalla Principal del Admin
                            composable("admin_home") {
                                AdminScreen(
                                    onCerrarSesionClick = { isLoggedIn = false },
                                    // 👇 AQUÍ CONECTAMOS EL BOTÓN A LA NUEVA PANTALLA
                                    onNavigateToProductos = { adminNavController.navigate("admin_productos") }
                                )
                            }

                            // 2. Pantalla de Gestión de Productos
                            composable("admin_productos") {
                                AdminProductosScreen(
                                    // Al tocar "Volver", regresa al panel principal
                                    onVolverClick = { adminNavController.popBackStack() }
                                )
                            }
                        }

                    } else {
                        // Flujo normal del Consumidor
                        MainScreen(
                            onLogout = { isLoggedIn = false }
                        )
                    }
                } else {
                    // Pantalla de Login
                    LoginScreen(
                        onLoginSuccess = { rol ->
                            userRole = rol
                            isLoggedIn = true
                        }
                    )
                }
            }
        }
    }
}