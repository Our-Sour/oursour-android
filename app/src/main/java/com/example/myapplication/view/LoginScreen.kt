package com.example.myapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background // 👈 NUEVO IMPORT
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.ui.theme.OurPeach40
import com.example.myapplication.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    loginViewModel: LoginViewModel = viewModel()
) {
    val passwordFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    // Definimos el color del fondo oscuro elegante (Gris Cósmico)
    val fondoOscuro = Color(0xFF1E1B18)
    // Color para los textos en modo oscuro (un blanco crema suave)
    val textoClaro = Color(0xFFF2ECFF)

    var ejecutarLogin = {
        val validador = loginViewModel.mail.isNotBlank() && loginViewModel.pass.isNotBlank()
        if (validador) {
            onLoginSuccess()
        } else {
            println("Error: Debes ingresar correo y contraseña")
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        // AQUÍ APLICAMOS EL FONDO OSCURO Y EL PADDING
        modifier = Modifier
            .fillMaxSize()
            .background(fondoOscuro)
            .padding(52.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.logooursour),
            contentDescription = "Logo de Our Sour",
            modifier = Modifier
                .size(500.dp )

        )

        Text(
            text = "Bienvenido",
            fontSize = 25.sp,
            color = textoClaro, // 👈 Texto en blanco/crema para que resalte
            modifier = Modifier.padding(bottom = 20.dp)

        )

        // Caja de texto: Correo
        OutlinedTextField(
            value = loginViewModel.mail,
            onValueChange = {
                loginViewModel.cambiarMail(it)
            },
            label = {
                Text("Correo", color = OurPeach40) // Etiqueta naranja
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            singleLine = true,
            // 👇 Hacemos que el texto que escribe el usuario sea claro
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OurPeach40,
                unfocusedBorderColor = OurPeach40.copy(alpha = 0.5f),
                focusedTextColor = textoClaro,
                unfocusedTextColor = textoClaro,
                cursorColor = OurPeach40
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { passwordFocusRequester.requestFocus() }
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Caja de texto: Contraseña
        OutlinedTextField(
            value = loginViewModel.pass,
            onValueChange = {
                loginViewModel.cambiarPass(it)
            },
            label = {
                Text("Contraseña", color = OurPeach40)
            },
            modifier = Modifier.fillMaxWidth().focusRequester(passwordFocusRequester),
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(30.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = OurPeach40,
                unfocusedBorderColor = OurPeach40.copy(alpha = 0.5f),
                focusedTextColor = textoClaro,
                unfocusedTextColor = textoClaro,
                cursorColor = OurPeach40
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    ejecutarLogin()
                }
            )
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            modifier = Modifier.fillMaxWidth().height(52.dp),
            onClick = { ejecutarLogin() },
            shape = RoundedCornerShape(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = OurPeach40,
                contentColor = Color.White
            )
        ) {
            Text("Iniciar Sesión", fontSize = 16.sp)
        }
    }
}