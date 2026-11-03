package com.example.myapplication.view
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
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
import com.example.myapplication.viewmodel.CarritoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(
    viewModel: CarritoViewModel = viewModel()
) {
    val fondoOscuro = Color(0xFF1E1B18)
    val textoClaro = Color(0xFFF2ECFF)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Carro", fontWeight = FontWeight.Bold, color = textoClaro) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = fondoOscuro)
            )
        },
        containerColor = fondoOscuro
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)
        ) {
            // Si el carrito está vacío
            if (viewModel.itemsCarrito.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Tu carro está vacío", color = textoClaro, fontSize = 18.sp)
                }
            } else {
                // Lista de productos
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(viewModel.itemsCarrito) { item ->
                        Surface(
                            color = Color(0xFF2A2521),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(item.nombre, color = textoClaro, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                    Text("Cantidad: ${item.cantidad}", color = Color.Gray, fontSize = 14.sp)
                                }
                                Text("$${item.precio * item.cantidad}", color = OurPeach40, fontWeight = FontWeight.Bold, fontSize = 16.sp)

                                // Botón para eliminar
                                IconButton(onClick = { viewModel.eliminarProducto(item.id) }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                                }
                            }
                        }
                    }
                }

                // Panel de Totales
                HorizontalDivider(color = Color.Gray, modifier = Modifier.padding(vertical = 16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total:", color = textoClaro, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text("$${viewModel.calcularTotal()}", color = OurPeach40, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botón de Confirmar Pedido
                Button(
                    onClick = { /* Lógica para confirmar compra */ },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OurPeach40),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Text("Confirmar Pedido", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }
    }
}