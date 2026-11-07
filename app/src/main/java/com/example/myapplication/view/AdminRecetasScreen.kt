package com.example.myapplication.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.OurPeach40
import com.example.myapplication.viewmodel.AdminRecetasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminRecetasScreen(
    onVolverClick: () -> Unit,
    onCrearRecetaClick: () -> Unit,
    viewModel: AdminRecetasViewModel = viewModel()
) {
    val fondoOscuro = Color(0xFF1E1B18)
    val textoClaro = Color(0xFFF2ECFF)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Recetas", color = textoClaro) },
                navigationIcon = {
                    TextButton(onClick = onVolverClick) {
                        Text("← Volver", color = OurPeach40, fontSize = 16.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = fondoOscuro)
            )
        },
        containerColor = fondoOscuro,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onCrearRecetaClick,
                containerColor = OurPeach40,
                contentColor = Color.White
            ) {
                Text("+ Nueva Receta", fontWeight = FontWeight.Bold)
            }
        }
    ) { padding ->
        if (viewModel.isLoading) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = OurPeach40)
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
                items(viewModel.listaRecetas) { receta ->
                    Surface(color = Color(0xFF2A2521), shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(receta.titulo, color = textoClaro, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text("⏱ ${receta.tiempo} | 🎚 ${receta.dificultad}", color = Color.Gray, fontSize = 12.sp)
                                Text(
                                    text = if (receta.videoUrl != null) "🎥 Video adjunto" else "❌ Sin video",
                                    color = if (receta.videoUrl != null) Color.Green else Color.Red,
                                    fontSize = 12.sp
                                )
                            }
                            Button(
                                onClick = { viewModel.eliminarReceta(receta.id) },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A332E)),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text("Eliminar", color = Color.Red, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}