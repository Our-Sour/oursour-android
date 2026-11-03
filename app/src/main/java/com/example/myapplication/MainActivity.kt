package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.myapplication.ui.theme.MyApplicationTheme
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
                        AdminScreen(
                            onCerrarSesionClick = { isLoggedIn = false }
                        )
                    } else {
                        MainScreen(
                            onLogout = { isLoggedIn = false }
                        )
                    }
                } else {
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