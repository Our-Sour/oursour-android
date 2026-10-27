package com.example.myapplication.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.OurPeach40
import com.example.myapplication.viewmodel.IngredientsViewModel

@Composable
fun IngredientsScreen(

    onVerRecetasClick: () -> Unit, // Función para navegar al feed
    viewModel: IngredientsViewModel = viewModel() // Inyectamos el ViewModel

){
    // Estado para el texto del buscador
    var textoBusqueda by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding() // ESTO EMPUJA EL CONTENIDO HACIA ABAJO DE LA HORA
            .padding(26.dp)
            .navigationBarsPadding()
    ){
        // 1. Título
        Text(
            text = "Ingredientes",
            fontSize = 24.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = Color(0xFF3A332E) // Un tono oscuro para el texto
        )


        Spacer(modifier = Modifier.height(8.dp))

        // 2. Subtítulo
        Text(
            text = "selecciona o busca\ningredientes que tengas en casa",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 3. Buscador
        OutlinedTextField(
            value = textoBusqueda,
            onValueChange = { textoBusqueda = it }, //'it' representa automáticamente el nuevo texto que el usuario acaba de escribir en el teclado
            placeholder = { Text("Buscar o filtrar ingrediente") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OurPeach40,
                unfocusedBorderColor = OurPeach40.copy(alpha = 0.5f)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 4. Lista de ingredientes (Usamos LazyColumn para que haga scroll)
        // Filtramos la lista según lo que el usuario escriba en el buscador

        val listaFiltrada = viewModel.listaIngredientes.filter {
            it.nombre.contains(textoBusqueda, ignoreCase = true)
        }

        LazyColumn(
            modifier = Modifier.weight(2f) // Toma todo el espacio restante
        ){
            itemsIndexed(listaFiltrada){
                index, ingrediente ->
                // row una fila  por ada ingreidente
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // Buscamos el índice real en la lista original para actualizarlo
                            val indiceReal = viewModel.listaIngredientes.indexOf(ingrediente)
                            viewModel.toggleIngrediente(indiceReal)
                        }
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Emoji
                    Text(text = ingrediente.emoji, fontSize = 20.sp)


                    Spacer(modifier = Modifier.width(12.dp))

                    // Nombre
                    Text(
                        text = ingrediente.nombre,
                        modifier = Modifier.weight(1f),
                        fontSize = 16.sp
                    )

                    Checkbox(
                        checked = ingrediente.seleccionado,
                        onCheckedChange = {
                            val indiceReal= viewModel.listaIngredientes.indexOf(ingrediente)
                        },
                        colors = CheckboxDefaults.colors(checkedColor = OurPeach40)
                    )
                }
                // Línea separadora
                //Divider(color = Color.LightGray.copy(alpha = 0.3f))

            }
        }
        // 5. Botón flotante de "Ver recetas"
        Button(
            onClick = onVerRecetasClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = OurPeach40),

        ) {
            Text("Ver recetas →", color = Color.White, fontSize = 16.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        }

    }

}