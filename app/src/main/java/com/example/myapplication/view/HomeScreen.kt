package com.example.myapplication.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// COMPOSABLE: PERMITE MOSTRAR EN PANTALLA
// LO QUE LA FUNCIÓN DEFINE
@Composable
fun HomeScreen() {
    // () -> NUESTRAS PROPIEDADES
    // {} -> NUESTROS COMPONENTES
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Bienvenid@ a Android"
        )
    }
}
