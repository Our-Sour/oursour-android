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
import com.example.myapplication.viewmodel.AdminPedidosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPedidosScreen(
    onVolverClick: () -> Unit,
    viewModel: AdminPedidosViewModel = viewModel()
) {
    val fondoOscuro = Color(0xFF1E1B18)
    val textoClaro = Color(0xFFF2ECFF)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Monitoreo de Pedidos", color = textoClaro) },
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
        // 👇 Estado de carga
        if (viewModel.isLoading) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = OurPeach40)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)
            ) {
                items(viewModel.listaPedidos) { pedido ->
                    Surface(
                        color = Color(0xFF2A2521),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Pedido ${pedido.id}",
                                    color = textoClaro,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                                Surface(
                                    color = when(pedido.estado) {
                                        "Entregado" -> Color(0xFF2E7D32)
                                        "Solicitado" -> Color(0xFF1565C0)
                                        else -> Color(0xFFEF6C00)
                                    },
                                    shape = RoundedCornerShape(20.dp)
                                ) {
                                    Text(
                                        text = pedido.estado,
                                        color = Color.White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Cliente: ${pedido.cliente}", color = Color.Gray, fontSize = 13.sp)
                            Text("Total: $${pedido.total}", color = OurPeach40, fontWeight = FontWeight.Bold, fontSize = 14.sp)

                            Spacer(modifier = Modifier.height(12.dp))

                            Button(
                                onClick = { viewModel.cambiarEstado(pedido.id) },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = OurPeach40),
                                shape = RoundedCornerShape(20.dp)
                            ) {
                                Text("Cambiar Estado", color = Color.White, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}