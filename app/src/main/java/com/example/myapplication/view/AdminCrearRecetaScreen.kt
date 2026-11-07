package com.example.myapplication.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun AdminCrearRecetaScreen(
    onVolverClick: () -> Unit,
    viewModel: AdminRecetasViewModel = viewModel()
) {
    val fondoOscuro = Color(0xFF1E1B18)
    val textoClaro = Color(0xFFF2ECFF)
    val fondoCaja = Color(0xFF2A2521)

    var titulo by remember { mutableStateOf("") }
    var tiempo by remember { mutableStateOf("5 min") }
    var dificultad by remember { mutableStateOf("Fácil") }
    var videoUri by remember { mutableStateOf<Uri?>(null) }
    var videoStatus by remember { mutableStateOf("Sin video") }

    // 🎥 LANZADOR DE CÁMARA CORREGIDO (Usa StartActivityForResult)
    val camaraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data?.data
            if (uri != null) {
                videoUri = uri
                videoStatus = "✅ Video grabado"
            }
        }
    }

    // 📁 LANZADOR DE GALERÍA
    val galeriaLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            videoUri = uri
            videoStatus = "✅ Video de galería seleccionado"
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Receta", color = textoClaro) },
                navigationIcon = {
                    TextButton(onClick = onVolverClick) {
                        Text("← Volver", color = OurPeach40, fontSize = 16.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = fondoOscuro)
            )
        },
        containerColor = fondoOscuro
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp).verticalScroll(rememberScrollState())
        ) {
            Text("Video de preparación (Opcional)", color = OurPeach40, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier.fillMaxWidth().height(100.dp).background(fondoCaja, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(videoStatus, color = if (videoUri != null) Color.Green else Color.Gray)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {
                        // 👇 Lanzamos el Intent nativo de la cámara
                        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
                        camaraLauncher.launch(intent)
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = OurPeach40),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("🎥 Grabar", color = Color.White) }

                OutlinedButton(
                    onClick = { galeriaLauncher.launch("video/*") },
                    modifier = Modifier.weight(1f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, OurPeach40),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("📁 Galería", color = OurPeach40) }
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = titulo, onValueChange = { titulo = it }, label = { Text("Título (Ej: Pisco Sour)") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = OurPeach40, unfocusedBorderColor = Color.Gray, focusedTextColor = textoClaro, unfocusedTextColor = textoClaro)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = tiempo, onValueChange = { tiempo = it }, label = { Text("Tiempo") },
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = OurPeach40, unfocusedBorderColor = Color.Gray, focusedTextColor = textoClaro, unfocusedTextColor = textoClaro)
                )
                OutlinedTextField(
                    value = dificultad, onValueChange = { dificultad = it }, label = { Text("Dificultad") },
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = OurPeach40, unfocusedBorderColor = Color.Gray, focusedTextColor = textoClaro, unfocusedTextColor = textoClaro)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (titulo.isNotBlank()) {
                        viewModel.crearReceta(titulo, tiempo, dificultad, videoUri?.toString())
                        onVolverClick()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OurPeach40),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("Guardar Receta", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}