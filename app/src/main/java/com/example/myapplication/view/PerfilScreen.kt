package com.example.myapplication.view
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Person
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
import com.example.myapplication.viewmodel.PerfilViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    onCerrarSesionClick: () -> Unit,
    viewModel: PerfilViewModel = viewModel()
) {
    val fondoOscuro = Color(0xFF1E1B18)
    val textoClaro = Color(0xFFF2ECFF)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil", fontWeight = FontWeight.Bold, color = textoClaro) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = fondoOscuro)
            )
        },
        containerColor = fondoOscuro
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar
            Box(
                modifier = Modifier.size(100.dp).background(OurPeach40, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = "Avatar", tint = Color.White, modifier = Modifier.size(60.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Datos del usuario
            Text(viewModel.usuarioActual.nombre, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = textoClaro)
            Text(viewModel.usuarioActual.correo, fontSize = 14.sp, color = Color.Gray)
            Text("Rol: ${viewModel.usuarioActual.rol}", fontSize = 14.sp, color = OurPeach40)

            Spacer(modifier = Modifier.height(48.dp))

            // Botón de Cerrar Sesión
            Button(
                onClick = onCerrarSesionClick,
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A332E)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "Salir", tint = OurPeach40)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cerrar Sesión", color = OurPeach40, fontWeight = FontWeight.Bold)
            }
        }
    }
}