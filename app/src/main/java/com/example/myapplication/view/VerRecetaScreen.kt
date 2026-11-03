package com.example.myapplication.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
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
import com.example.myapplication.viewmodel.VerRecetaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerRecetaScreen(
    tituloReceta: String, // 👈 Recibimos el título desde la navegación
    onVolverClick: () -> Unit,
    viewModel: VerRecetaViewModel = viewModel()
) {
    val fondoOscuro = Color(0xFF1E1B18)
    val textoClaro = Color(0xFFF2ECFF)

    // 👈 Le pedimos al ViewModel la receta específica
    val receta = viewModel.obtenerReceta(tituloReceta)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(receta.titulo, fontWeight = FontWeight.Bold, color = textoClaro) },
                navigationIcon = {
                    IconButton(onClick = onVolverClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = OurPeach40)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = fondoOscuro)
            )
        },
        containerColor = fondoOscuro
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp).verticalScroll(rememberScrollState())
        ) {
            // VIDEO
            Box(
                modifier = Modifier.fillMaxWidth().height(220.dp).background(Color(0xFF2A2521), RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = "Video", tint = OurPeach40, modifier = Modifier.size(64.dp))
                Text("Video de preparación", color = Color.Gray, modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // INGREDIENTES
            Text("Ingredientes", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = OurPeach40)
            Spacer(modifier = Modifier.height(8.dp))
            Surface(color = Color(0xFF2A2521), shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    receta.ingredientes.forEach { ingrediente ->
                        Text("• $ingrediente", color = textoClaro, fontSize = 15.sp, modifier = Modifier.padding(bottom = 4.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // PREPARACIÓN
            Text("Preparación", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = OurPeach40)
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                receta.pasos.forEachIndexed { index, paso ->
                    Text("${index + 1}. $paso", color = textoClaro, fontSize = 15.sp, lineHeight = 22.sp, modifier = Modifier.padding(bottom = 12.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // PRODUCTO
            Text("Productos Our Sour", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = OurPeach40)
            Spacer(modifier = Modifier.height(8.dp))
            Surface(color = Color(0xFF2A2521), shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.size(80.dp).background(Color(0xFFFDEADE), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("", fontSize = 40.sp)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Our Sour Limón", fontWeight = FontWeight.Bold, color = textoClaro, fontSize = 16.sp)
                        Text("$4.990", color = OurPeach40, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}