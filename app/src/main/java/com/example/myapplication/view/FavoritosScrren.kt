package com.example.myapplication.view
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
fun FavoritosScreen() {
    val fondoOscuro = Color(0xFF3A332E)
    val textoClaro = Color.White

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Favoritos", fontWeight = FontWeight.Bold, color = textoClaro) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = fondoOscuro)
            )
        },
        containerColor = fondoOscuro
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)
        ) {
            // Simulamos las recetas guardadas
            LazyColumn {
                item { TarjetaFavorito(titulo = "Pisco Sour Clásico", emoji = "") }
                item { TarjetaFavorito(titulo = "Mojito Cubano", emoji = "") }
            }
        }
    }
}

@Composable
fun TarjetaFavorito(titulo: String, emoji: String) {
    Surface(
        color = Color(0xFF2A2521),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(emoji, fontSize = 32.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = titulo,
                color = Color(0xFFF2ECFF),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Icon(Icons.Default.Favorite, contentDescription = "Favorito", tint = OurPeach40)
        }
    }
}