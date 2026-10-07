package com.example.myapplication.view

import android.media.Image
import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.OurPeach40
import com.example.myapplication.viewmodel.LoginViewModel
import kotlin.math.log


import androidx.compose.foundation.Image // Importa el componente Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager

import androidx.compose.ui.res.painterResource // Importa para cargar recursos
import androidx.compose.ui.text.input.ImeAction
import com.example.myapplication.R // Importa R desde tu paquete

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    loginViewModel: LoginViewModel = viewModel()
) {

    // 1. Creamos un controlador para mover el foco entre los inputs
    val passwordFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var ejecutarLogin = {
        println("FSR: " + loginViewModel.mail)
        val validador = loginViewModel.mail.isNotBlank() && loginViewModel.pass.isNotBlank()
        val validacion = false
        if (validador) {
            // Si la validación es exitosa, navegamos a la siguiente pantalla
            onLoginSuccess()
        } else {
            println("Error: Debes ingresar correo y contraseña")
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(32.dp)
    ) {

        // Usamos el componente Image
        Image(
            // painterResource carga la imagen de la carpeta drawable
            // R.drawable.nombre_de_tu_imagen_sin_extension
            painter = painterResource(id = R.drawable.logo),

            // Contenido descriptivo para accesibilidad (obligatorio)
            contentDescription = "Logo de Our Sour"

            // Opcional: Puedes agregar un Modifier para tamaño, alineación, etc.
            // modifier = Modifier.size(100.dp)
        )

        // Text : obligatorio el parámetro text (minúscula)
        Text(
            text = "Our Sour",

            fontSize = 35.sp,
            modifier = Modifier.padding(32.dp)


        )

        // Caja de texto
        OutlinedTextField(
            value = loginViewModel.mail,
            onValueChange = {
                loginViewModel.cambiarMail(it)
            },
            label = {
                Text("Correo")
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            singleLine = true, // Evita que se hagan saltos de línea
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next // Cambia el botón del teclado a "Siguiente"
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    // Al presionar Siguiente, el foco salta a la contraseña
                    passwordFocusRequester.requestFocus()
                }
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = loginViewModel.pass,
            onValueChange = {
                loginViewModel.cambiarPass(it)
            },
            label = {
                Text("Contraseña")
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(30.dp),
            singleLine = true, // Evita que se hagan saltos de línea
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done // Cambia el botón del teclado a "Siguiente"
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus() // Oculta el teclado virtual
                    ejecutarLogin()          // Dispara el inicio de sesión automáticamente
                }
            )
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            modifier = Modifier.fillMaxWidth().height(52.dp),
            onClick = {
              ejecutarLogin()
            },
            shape = RoundedCornerShape(60.dp) ,
            colors = ButtonDefaults.buttonColors(containerColor = OurPeach40, contentColor = Color.White )

        ) {
            Text("Iniciar Sección")

        }
    }
}