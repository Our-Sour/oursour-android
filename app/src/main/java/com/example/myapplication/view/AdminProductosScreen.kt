package com.example.myapplication.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.myapplication.viewmodel.AdminProductosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminProductosScreen(
    onVolverClick: () -> Unit,
    viewModel: AdminProductosViewModel = viewModel()
) {
    val fondoOscuro = Color(0xFF1E1B18)
    val textoClaro = Color(0xFFF2ECFF)
    var mostrarModal by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Productos", color = textoClaro) },
                navigationIcon = {
                    // 👇 Cambiamos el ícono por un texto simple
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
                onClick = { mostrarModal = true },
                containerColor = OurPeach40,
                contentColor = Color.White
            ) {
                //  El texto va aquí adentro, sin el parámetro "text ="
                Text("+ Añadir Producto", fontWeight = FontWeight.Bold)
            }
        }
    ) { padding ->
        if (viewModel.isLoading) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = OurPeach40)
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
                items(viewModel.listaProductos) { prod ->
                    Surface(color = Color(0xFF2A2521), shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(prod.nombre, color = textoClaro, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                Text(prod.descripcion, color = Color.Gray, fontSize = 12.sp)
                                Text("$${prod.precio} | Stock: ${prod.stock} | ${prod.fechaCreacion}", color = OurPeach40, fontSize = 12.sp)
                            }
                            // 👇 Cambiamos el ícono de eliminar por un botón con texto
                            Button(
                                onClick = { viewModel.eliminarProducto(prod.id) },
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

    // MODAL PARA AGREGAR PRODUCTO
    if (mostrarModal) {
        var nombre by remember { mutableStateOf("") }
        var descripcion by remember { mutableStateOf("") }
        var precioStr by remember { mutableStateOf("") }

        ModalBottomSheet(
            onDismissRequest = { mostrarModal = false },
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                Text("Nuevo Producto", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = fondoOscuro)
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre (Ej: Our Sour Limón)") }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = descripcion, onValueChange = { descripcion = it }, label = { Text("Descripción corta") }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = precioStr, onValueChange = { precioStr = it }, label = { Text("Precio (CLP)") }, modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val precio = precioStr.toIntOrNull() ?: 0
                        if (nombre.isNotBlank() && precio > 0) {
                            viewModel.crearProducto(nombre, descripcion, precio)
                            mostrarModal = false
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OurPeach40),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text("Guardar Producto", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}