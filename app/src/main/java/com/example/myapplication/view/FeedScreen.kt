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
    viewModel: FeedViewModel = viewModel()
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

}