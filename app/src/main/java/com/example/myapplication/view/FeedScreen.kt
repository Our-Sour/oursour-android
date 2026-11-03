package com.example.myapplication.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
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
import com.example.myapplication.viewmodel.FeedViewModel
import com.example.myapplication.viewmodel.Receta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    viewModel: FeedViewModel = viewModel(),
    onNavigateToCarrito: () -> Unit = {},
    onVerRecetaClick: () -> Unit = {}
) {
    val pagerState = rememberPagerState(pageCount = { viewModel.recetas.size })

    // Estados para el modal
    var mostrarModal by remember { mutableStateOf(false) }
    var recetaSeleccionada by remember { mutableStateOf<Receta?>(null) }

    VerticalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) { page ->
        val receta = viewModel.recetas[page]

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(receta.colorFondo))
        ) {
            Text(
                text = receta.emoji,
                fontSize = 180.sp,
                modifier = Modifier.align(Alignment.Center)
            )

            Surface(
                color = Color.White.copy(alpha = 0.8f),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.align(Alignment.TopStart).padding(24.dp)
            ) {
                Text(
                    text = receta.tipo.uppercase(),
                    color = OurPeach40,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }

            Column(
                modifier = Modifier.align(Alignment.CenterEnd).padding(end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                IconButton(
                    onClick = { },
                    modifier = Modifier.size(50.dp).background(Color.White.copy(alpha = 0.9f), CircleShape)
                ) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorito", tint = OurPeach40)
                }
                IconButton(
                    onClick = onNavigateToCarrito,
                    modifier = Modifier.size(50.dp).background(Color.White.copy(alpha = 0.9f), CircleShape)
                ) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Comprar", tint = OurPeach40)
                }
            }

            Surface(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(text = receta.titulo, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3A332E))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = receta.ingredientes, fontSize = 14.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(16.dp))

                    // 👇 AQUÍ ESTÁN LOS DOS BOTONES
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        // Botón 1: Ver Receta
                        Button(
                            onClick = onVerRecetaClick,
                            modifier = Modifier.weight(1f).height(50.dp),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = OurPeach40)
                        ) {
                            Text("Ver Receta", color = Color.White, fontWeight = FontWeight.Bold)
                        }

                        // Botón 2: Añadir Productos (Abre el Modal)
                        OutlinedButton(
                            onClick = {
                                recetaSeleccionada = receta
                                mostrarModal = true
                            },
                            modifier = Modifier.weight(1f).height(50.dp),
                            shape = RoundedCornerShape(30.dp),
                            border = BorderStroke(1.dp, OurPeach40)
                        ) {
                            Text("Añadir", color = OurPeach40, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }

    // =========================================================
    // AQUÍ EMPIEZA EL MODAL DE VALORACIONES Y COMPRA
    // =========================================================
    /*
    if (mostrarModal && recetaSeleccionada != null) {
        ModalBottomSheet(
            onDismissRequest = { mostrarModal = false },
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                // Título y Botón Cerrar
                Row(
                    modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                    {
                        Text(text = "Productos Our Sour", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3A332E))
                        IconButton(onClick = { mostrarModal = false }) {
                            Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = Color.Gray)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                //targenta producto our sour

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ){}

                Text(text = recetaSeleccionada!!.titulo, fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(16.dp))


                Spacer(modifier = Modifier.height(16.dp))


                // Botón para agregar al carro
                Button(
                    onClick = { mostrarModal = false },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OurPeach40),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Text("Añadir al carro", color = Color.White, fontWeight = FontWeight.Bold)
                }
            } // FIN COLUMNA
        }// FIN BOTTON MODAL
    }

     */


    // =========================================================
    // AQUÍ EMPIEZA EL MODAL
    // =========================================================
    if (mostrarModal && recetaSeleccionada != null) {
        // Estado para el contador de cantidad
        var cantidadProducto by remember { mutableStateOf(1) }

        ModalBottomSheet(
            onDismissRequest = { mostrarModal = false },
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {

                // Título y Botón Cerrar
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Producto Our Sour", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3A332E))
                    IconButton(onClick = { mostrarModal = false }) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 👇 TARJETA DEL PRODUCTO (Estilo App Líder)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Foto simulada del producto
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color(0xFFFDEADE), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("", fontSize = 40.sp) // Emoji como foto
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Info y Precio
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Our Sour Limón",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3A332E)
                        )
                        Text(
                            text = "Para tu ${recetaSeleccionada!!.titulo}", // Muestra la receta
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "$${4990 * cantidadProducto}", // Precio dinámico
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = OurPeach40
                        )
                    }

                    // Contador (+ y -)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        OutlinedButton(
                            onClick = { if (cantidadProducto > 1) cantidadProducto-- },
                            modifier = Modifier.size(32.dp),
                            contentPadding = PaddingValues(0.dp),
                            border = BorderStroke(1.dp, Color.LightGray),
                            shape = RoundedCornerShape(8.dp)
                        ) { Text("-", color = Color.Black, fontSize = 16.sp) }

                        Text(
                            text = "$cantidadProducto",
                            modifier = Modifier.padding(horizontal = 12.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )

                        OutlinedButton(
                            onClick = { cantidadProducto++ },
                            modifier = Modifier.size(32.dp),
                            contentPadding = PaddingValues(0.dp),
                            border = BorderStroke(1.dp, Color.LightGray),
                            shape = RoundedCornerShape(8.dp)
                        ) { Text("+", color = Color.Black, fontSize = 16.sp) }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Botón para agregar al carro
                Button(
                    onClick = {
                        // Aquí a futuro puedes enviar 'cantidadProducto' a tu ViewModel del carrito
                        mostrarModal = false
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OurPeach40),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Text("Añadir al carro", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
