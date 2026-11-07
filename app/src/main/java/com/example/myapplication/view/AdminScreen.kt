package com.example.myapplication.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.OurPeach40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    onCerrarSesionClick: () -> Unit,
    onNavigateToProductos: () -> Unit,
    onNavigateToMetricas: () -> Unit, // 👈 NUEVO
    onNavigateToPedidos: () -> Unit   // 👈 NUEVO
) {
    val fondoOscuro = Color(0xFF1E1B18)
    val textoClaro = Color(0xFFF2ECFF)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Panel Admin", fontWeight = FontWeight.Bold, color = textoClaro) },
                actions = {
                    IconButton(onClick = onCerrarSesionClick) {
                        Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "Salir", tint = OurPeach40)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = fondoOscuro)
            )
        },
        containerColor = fondoOscuro
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)
        ) {
            Text("Bienvenido, Administrador", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = textoClaro)
            Spacer(modifier = Modifier.height(24.dp))

            // 👇 Conectadas
            AdminCard(titulo = "Dashboard de Métricas", icono = "📊", onClick = onNavigateToMetricas)
            AdminCard(titulo = "Gestión de Catálogo", icono = "📦", onClick = onNavigateToProductos)
            AdminCard(titulo = "Monitoreo de Pedidos", icono = "🧾", onClick = onNavigateToPedidos)
        }
    }
}

@Composable
fun AdminCard(titulo: String, icono: String, onClick: () -> Unit) {
    Surface(
        color = Color(0xFF2A2521),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(icono, fontSize = 32.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Text(titulo, color = Color(0xFFF2ECFF), fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}