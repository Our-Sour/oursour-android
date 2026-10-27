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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
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
import com.example.myapplication.viewmodel.RecetaMock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    viewModel: FeedViewModel = viewModel(),
    onNavigateToCarrito: () -> Unit = {} // 👈 Callback para ir al carrito
) {
    // 💡 FUTURO API: viewModel.recetas vendrá de una llamada GET /api/recipes/
    val pagerState = rememberPagerState(pageCount = { viewModel.recetas.size })

    // Estado para controlar cuál receta abrió el modal
    var recetaSeleccionadaParaModal by remember { mutableStateOf<RecetaMock?>(null) }

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
            // Emoji / Imagen central
            Text(
                text = receta.emoji,
                fontSize = 180.sp,
                modifier = Modifier.align(Alignment.Center)
            )

            // Etiqueta superior (Tipo de trago)
            Surface(
                color = Color.White.copy(alpha = 0.8f),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(24.dp)
            ) {
                Text(
                    text = receta.tipo.uppercase(),
                    color = OurPeach40,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }

            // Botones laterales (Favorito y Carrito)
            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.White.copy(alpha = 0.9f), CircleShape)
                ) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorito", tint = OurPeach40)
                }
                IconButton(
                    onClick = onNavigateToCarrito, // 👈 Conectado al carrito
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.White.copy(alpha = 0.9f), CircleShape)
                ) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Comprar", tint = OurPeach40)
                }
            }

            // Panel inferior de la receta
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text(
                        text = receta.titulo,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3A332E)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = receta.ingredientes,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón para desplegar el modal de productos asociados
                    Button(
                        onClick = { recetaSeleccionadaParaModal = receta },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = OurPeach40)
                    ) {
                        Text("Añadir productos", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }

    // =========================================================
    // AQUÍ EMPIEZA EL MODAL (Dentro de FeedScreen, al final)
    // =========================================================
    recetaSeleccionadaParaModal?.let { recetaActual ->
        var personas by remember { mutableStateOf(2) }

        ModalBottomSheet(
            onDismissRequest = { recetaSeleccionadaParaModal = null },
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                // Título dinámico y botón de cierre
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = recetaActual.titulo,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3A332E)
                    )
                    IconButton(onClick = { recetaSeleccionadaParaModal = null }) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = Color.Gray)
                    }
                }

                Text(
                    text = "Añade los productos asociados",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tarjeta del Kit
                Surface(
                    color = Color(0xFFFDF8F4),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, Color(0xFFF6D4BB)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "🎁 Kit de Cócteles", fontWeight = FontWeight.Bold, fontSize = 16.sp)

                            // Botones de conteo de personas
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                OutlinedButton(
                                    onClick = { if (personas > 1) personas-- },
                                    modifier = Modifier.size(32.dp),
                                    contentPadding = PaddingValues(0.dp),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = OurPeach40),
                                    border = BorderStroke(1.dp, OurPeach40)
                                ) {
                                    Text("-", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                }

                                Text(
                                    text = "$personas",
                                    modifier = Modifier.padding(horizontal = 12.dp),
                                    fontWeight = FontWeight.Bold,
                                    color = OurPeach40,
                                    fontSize = 16.sp
                                )

                                OutlinedButton(
                                    onClick = { personas++ },
                                    modifier = Modifier.size(32.dp),
                                    contentPadding = PaddingValues(0.dp),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = OurPeach40),
                                    border = BorderStroke(1.dp, OurPeach40)
                                ) {
                                    Text("+", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        Text(
                            text = "Cantidad de personas",
                            fontSize = 12.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                        )

                        // Desglose dinámico
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("${personas}× Producto Base", fontSize = 12.sp, color = Color.Gray)
                            Text("$${personas * 1990}", fontSize = 12.sp, color = OurPeach40, fontWeight = FontWeight.Bold)
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("${personas}× Hielo Cristalino", fontSize = 12.sp, color = Color.Gray)
                            Text("$${personas * 2490}", fontSize = 12.sp, color = OurPeach40, fontWeight = FontWeight.Bold)
                        }

                        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.LightGray.copy(alpha = 0.5f))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Total Kit ($personas personas)", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text("$${personas * 4480}", fontWeight = FontWeight.Bold, color = OurPeach40)
                        }

                        Button(
                            onClick = { recetaSeleccionadaParaModal = null },
                            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = OurPeach40),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text(text = "Agregar Kit al carro", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Productos individuales
                Text(
                    text = "PRODUCTOS INDIVIDUALES",
                    color = OurPeach40,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color(0xFFF6D4BB)),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Producto Base Asociado", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text("$1.990", fontSize = 12.sp, color = Color.Gray)
                        }
                        OutlinedButton(
                            onClick = { },
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, OurPeach40),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = OurPeach40)
                        ) {
                            Text("+ Añadir", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}